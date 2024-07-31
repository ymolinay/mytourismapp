package com.heyrex.mytourismapp.core.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heyrex.mytourismapp.R
import com.heyrex.mytourismapp.core.domain.RequestFailure
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow

abstract class BaseViewModel<S> : ViewModel() {

    val viewState = MutableLiveData<ViewModelState<S>>()

    protected abstract fun init(arguments: Bundle)

    fun initViewModel(arguments: Bundle?) {
        init(arguments ?: Bundle())
    }

    inline fun <S> async(flow: Flow<Result<S>>, crossinline block: (S) -> Unit) =
        viewModelScope.launch {
            flow.collect { result ->
                result.fold(
                    onSuccess = block,
                    onFailure = {
                        if (it is RequestFailure) {
                            errorManager(it)
                        } else {
                            errorManager(RequestFailure.UnknownError)
                        }
                    }
                )
            }
        }

    fun errorManager(requestFailure: RequestFailure) {
        when (requestFailure) {
            is RequestFailure.NoConnectionError -> showMessage(R.string.connection_error_message)
            is RequestFailure.ApiError -> showMessageApi(requestFailure.message ?: "")
            else -> showMessage(R.string.default_error_message)
        }
    }

    fun updateState(newState: S) {
        viewState.value = ViewModelState.Loaded(newState)
    }

    fun showProgress() {
        viewState.value = ViewModelState.InProgress()
    }

    fun showMessage(idMessage: Int) {
        viewState.value = ViewModelState.Message(idMessage = idMessage)
    }

    fun showMessageApi(message: String) {
        viewState.value = ViewModelState.ApiError(message = message)
    }

    fun navigate(navResId: Int, bundle: Bundle? = null) {
        viewState.value = ViewModelState.Navigate(navResId, bundle)
    }

    fun goIntent(intent: Activity) {
        viewState.value = ViewModelState.GoIntent(intent)
    }
}