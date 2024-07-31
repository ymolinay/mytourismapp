package com.heyrex.mytourismapp.features.list.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.heyrex.mytourismapp.R
import com.heyrex.mytourismapp.core.presentation.BaseFragment
import com.heyrex.mytourismapp.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : BaseFragment<ListState>(R.layout.fragment_list) {

    private lateinit var binding: FragmentListBinding

    private val listViewModel: ListViewModel by viewModels()

    override fun viewModel() = listViewModel

    override fun onUpdateState(state: ListState) {
        println(state.list.toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)
        listViewModel.getList()
    }
}