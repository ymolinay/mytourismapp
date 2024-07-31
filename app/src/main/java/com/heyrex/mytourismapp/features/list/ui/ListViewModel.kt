package com.heyrex.mytourismapp.features.list.ui

import android.os.Bundle
import com.heyrex.mytourismapp.core.presentation.BaseViewModel
import com.heyrex.mytourismapp.features.list.domain.usecase.GetListPlacesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel
@Inject constructor(
    private val getListPlacesUseCase: GetListPlacesUseCase
) : BaseViewModel<ListState>() {

    override fun init(arguments: Bundle) = Unit

    fun getList() {
        showProgress()
        async(getListPlacesUseCase.execute()) { result ->
            updateState(ListState(list = result ?: emptyList()))
        }
    }
}