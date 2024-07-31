package com.heyrex.mytourismapp.core.presentation

import android.os.Bundle
import androidx.annotation.StringRes

sealed class ViewModelState<out T>: AppEvent() {
    data class Loaded<out T>(val value: T): ViewModelState<T>()
    data class Message(@StringRes val idMessage: Int): ViewModelState<Nothing>()
    data class ApiError(val message: String): ViewModelState<Nothing>()
    data class Navigate(val navResId: Int, val bundle: Bundle?): ViewModelState<Nothing>()
    class InProgress : ViewModelState<Nothing>()
}