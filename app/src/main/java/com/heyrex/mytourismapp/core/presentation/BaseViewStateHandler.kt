package com.heyrex.mytourismapp.core.presentation

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast

/**
 * Clase de utilidad para manejar estados de vista.
 *
 * @param S El tipo de estado que manejará.
 */
class BaseViewStateHandler<S>(
    private val context: Context,
    private val onUpdateState: (S) -> Unit,
    private val showLoading: () -> Unit,
    private val hideLoading: () -> Unit,
    private val navigate: (Int, Bundle?) -> Unit,
    private val goIntent: (Activity) -> Unit
) {

    fun manageViewState(viewModelState: ViewModelState<S>) {
        viewModelState.getContentIfNotHandled()?.let {
            hideLoading()
            when (viewModelState) {
                is ViewModelState.InProgress -> showLoading()
                is ViewModelState.Message -> showError(context.getString(viewModelState.idMessage))
                is ViewModelState.ApiError -> showError(viewModelState.message)
                is ViewModelState.Navigate -> navigate(viewModelState.navResId, viewModelState.bundle)
                is ViewModelState.GoIntent -> goIntent(viewModelState.goIntent)
                else -> onUpdateState((viewModelState as ViewModelState.Loaded).value)
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}