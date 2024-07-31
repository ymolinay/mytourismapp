package com.heyrex.mytourismapp.features.detail.ui

import android.os.Bundle
import com.heyrex.mytourismapp.core.presentation.BaseViewModel
import com.heyrex.mytourismapp.features.detail.domain.usecase.GetDetailPlaceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject constructor(
    private val getDetailPlaceUseCase: GetDetailPlaceUseCase
) : BaseViewModel<DetailState>() {

    override fun init(arguments: Bundle) = Unit

    fun getDetail(placeId: String) {
        showProgress()
        async(getDetailPlaceUseCase.execute(placeId)) { result ->
            updateState(DetailState(detail = result))
        }
    }
}