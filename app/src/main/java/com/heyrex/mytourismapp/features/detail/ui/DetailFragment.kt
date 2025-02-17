package com.heyrex.mytourismapp.features.detail.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.heyrex.mytourismapp.R
import com.heyrex.mytourismapp.core.extensions.loadImage
import com.heyrex.mytourismapp.core.presentation.BaseFragment
import com.heyrex.mytourismapp.core.presentation.BundleKeys
import com.heyrex.mytourismapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import kotlin.math.abs

@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailState>(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding

    private val detailViewModel: DetailViewModel by viewModels()

    override fun viewModel() = detailViewModel

    override fun onUpdateState(state: DetailState) {
        state.detail?.let {
            binding.toolbar.title = it.title
            binding.description.text = it.description
            binding.image.loadImage(it.image)


            val adapter = DetailAlbumAdapter(it.album)
            binding.viewPager.adapter = adapter

            binding.viewPager.apply {
                clipToPadding = false
                clipChildren = false
                offscreenPageLimit = 2
                (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

                val compositePageTransformer = CompositePageTransformer()
                compositePageTransformer.addTransformer(MarginPageTransformer(10))
                compositePageTransformer.addTransformer { page, position ->
                    val scaleFactor = 0.85f + (1 - abs(position)) * 0.15f
                    page.scaleY = scaleFactor
                }
                setPageTransformer(compositePageTransformer)
            }

            activity?.apply {
                Configuration.getInstance()
                    .load(requireContext(), getPreferences(Context.MODE_PRIVATE))
                binding.map.setMultiTouchControls(true)

                val mapController = binding.map.controller
                mapController.setZoom(15.0)
                val startPoint = GeoPoint(it.geo.lat.toDouble(), it.geo.lng.toDouble())
                mapController.setCenter(startPoint)

                addMarker(startPoint, it.title)
            }

        } ?: kotlin.run {
            findNavController().popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        arguments?.let {
            val placeId = it.getString(BundleKeys.USER_KEY, "")
            detailViewModel.getDetail(placeId)
        }
    }

    private fun addMarker(point: GeoPoint, title: String) {
        val marker = Marker(binding.map)
        marker.position = point
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.title = title
        binding.map.overlays.add(marker)
        binding.map.invalidate()
    }

    override fun onPause() {
        super.onPause()
        binding?.map?.onPause()
    }

    override fun onResume() {
        super.onResume()
        binding?.map?.onResume()
    }
}