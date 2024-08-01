package com.heyrex.mytourismapp.core.presentation

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.heyrex.mytourismapp.R
import com.heyrex.mytourismapp.core.extensions.gone
import com.heyrex.mytourismapp.core.extensions.visible
import com.heyrex.mytourismapp.databinding.LayoutProgressbarBinding

/**
 * Fragmento para manejar estados de vista y navegación.
 *
 * @param S El tipo de estado que manejará el fragmento.
 * @param layout El recurso de diseño del fragmento.
 */
abstract class BaseFragment<S>(layout: Int) : Fragment(layout) {

    // Enlace a la vista del indicador de progreso.
    private val layoutProgressbarBinding: LayoutProgressbarBinding by lazy {
        val view = requireActivity().findViewById<View>(android.R.id.content) as ViewGroup
        val constraintLayout = layoutInflater.inflate(R.layout.layout_progressbar, view, false)
        view.addView(constraintLayout)
        LayoutProgressbarBinding.bind(constraintLayout)
    }

    // Método abstracto para obtener el ViewModel del fragmento.
    protected abstract fun viewModel(): BaseViewModel<S>

    // Método abstracto para actualizar el estado de la vista.
    protected abstract fun onUpdateState(state: S)

    // Configura el observador del estado de la vista.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel().apply {
            viewState.observe(viewLifecycleOwner) { manageViewState(it) }
        }
    }

    // Gestiona el estado de la vista.
    private fun manageViewState(viewModelState: ViewModelState<S>) {
        viewModelState.getContentIfNotHandled()?.let {
            hideLoading()
            when (viewModelState) {
                is ViewModelState.InProgress -> showLoading()
                is ViewModelState.Message -> showError(getString(viewModelState.idMessage))
                is ViewModelState.ApiError -> showError(viewModelState.message)
                is ViewModelState.Navigate -> navigate(viewModelState.navResId, viewModelState.bundle)
                is ViewModelState.GoIntent -> {}
                else -> onUpdateState((viewModelState as ViewModelState.Loaded).value)
            }
        }
    }

    // Navega a un destino específico.
    fun navigate(navResId: Int, bundle: Bundle?) {
        findNavController().navigate(navResId, bundle)
    }

    // Muestra el indicador de carga.
    open fun showLoading() {
        layoutProgressbarBinding.clProgressBar.visible()
    }

    // Oculta el indicador de carga.
    open fun hideLoading() {
        layoutProgressbarBinding.clProgressBar.gone()
    }

    // Muestra un mensaje de error.
    private fun showError(message: String) {
        activity?.let { Toast.makeText(it, message, Toast.LENGTH_LONG).show() }
    }

}