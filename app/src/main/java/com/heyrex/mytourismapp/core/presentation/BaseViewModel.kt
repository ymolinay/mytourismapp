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

/**
 * ViewModel base abstracto para manejar el estado de la vista y la lógica de negocio.
 *
 * @param S El tipo de estado que manejará el ViewModel.
 */
abstract class BaseViewModel<S> : ViewModel() {

    // LiveData para el estado de la vista.
    val viewState = MutableLiveData<ViewModelState<S>>()

    // Método abstracto para inicializar el ViewModel con argumentos.
    protected abstract fun init(arguments: Bundle)

    // Inicializa el ViewModel con argumentos opcionales.
    fun initViewModel(arguments: Bundle?) {
        init(arguments ?: Bundle())
    }

    // Ejecuta una operación asíncrona y maneja el resultado.
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

    // Gestiona errores específicos.
    fun errorManager(requestFailure: RequestFailure) {
        when (requestFailure) {
            is RequestFailure.NoConnectionError -> showMessage(R.string.connection_error_message)
            is RequestFailure.ApiError -> showMessageApi(requestFailure.message ?: "")
            else -> showMessage(R.string.default_error_message)
        }
    }

    // Actualiza el estado de la vista.
    fun updateState(newState: S) {
        viewState.value = ViewModelState.Loaded(newState)
    }

    // Muestra un estado de progreso.
    fun showProgress() {
        viewState.value = ViewModelState.InProgress()
    }

    // Muestra un mensaje.
    fun showMessage(idMessage: Int) {
        viewState.value = ViewModelState.Message(idMessage = idMessage)
    }

    // Muestra un mensaje de error de API.
    fun showMessageApi(message: String) {
        viewState.value = ViewModelState.ApiError(message = message)
    }

    // Navega a un destino específico.
    fun navigate(navResId: Int, bundle: Bundle? = null) {
        viewState.value = ViewModelState.Navigate(navResId, bundle)
    }

    // Ejecuta una intención de actividad.
    fun goIntent(intent: Activity) {
        viewState.value = ViewModelState.GoIntent(intent)
    }
}