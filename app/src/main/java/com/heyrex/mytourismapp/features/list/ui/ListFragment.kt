package com.heyrex.mytourismapp.features.list.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.heyrex.mytourismapp.R
import com.heyrex.mytourismapp.core.extensions.VerticalItemDecoration
import com.heyrex.mytourismapp.core.presentation.BaseFragment
import com.heyrex.mytourismapp.databinding.FragmentListBinding
import com.heyrex.mytourismapp.features.list.domain.model.PlaceSimple
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : BaseFragment<ListState>(R.layout.fragment_list) {

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: ListPlacesAdapter

    private val listViewModel: ListViewModel by viewModels()

    override fun viewModel() = listViewModel

    override fun onUpdateState(state: ListState) {
        adapter = ListPlacesAdapter(state.list.toMutableList(), ::onClickDetailPLace)
        binding.recyclerList.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)
        listViewModel.getList()

        binding.recyclerList.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.recyclerList.addItemDecoration(VerticalItemDecoration(12))
    }

    private fun onClickDetailPLace(item: PlaceSimple) {
        listViewModel.goDetail(item)
    }
}