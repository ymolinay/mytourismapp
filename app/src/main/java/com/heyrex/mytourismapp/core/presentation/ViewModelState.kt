package com.heyrex.mytourismapp.core.presentation

import android.app.Activity
import android.os.Bundle
import androidx.annotation.StringRes

/**
 * Estado del ViewModel que representa diferentes tipos de resultados.
 *
 * @param T El tipo de datos que se manejará en el estado cargado.
 */
sealed class ViewModelState<out T>: AppEvent() {
    data class Loaded<out T>(val value: T): ViewModelState<T>()
    data class Message(@StringRes val idMessage: Int): ViewModelState<Nothing>()
    data class ApiError(val message: String): ViewModelState<Nothing>()
    data class Navigate(val navResId: Int, val bundle: Bundle?): ViewModelState<Nothing>()
    data class GoIntent(val goIntent: Activity): ViewModelState<Nothing>()
    class InProgress : ViewModelState<Nothing>()
}