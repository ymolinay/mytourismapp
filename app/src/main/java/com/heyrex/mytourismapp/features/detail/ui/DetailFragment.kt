package com.heyrex.mytourismapp.features.detail.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.heyrex.mytourismapp.R
import com.heyrex.mytourismapp.core.presentation.BaseFragment
import com.heyrex.mytourismapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailState>(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding

    private val detailViewModel: DetailViewModel by viewModels()

    override fun viewModel() = detailViewModel

    override fun onUpdateState(state: DetailState) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        detailViewModel.getDetail("")
    }
}