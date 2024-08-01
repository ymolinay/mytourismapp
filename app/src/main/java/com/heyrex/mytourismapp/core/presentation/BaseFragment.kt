package com.heyrex.mytourismapp.core.presentation

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.heyrex.mytourismapp.R
import com.heyrex.mytourismapp.core.extensions.gone
import com.heyrex.mytourismapp.core.extensions.visible
import com.heyrex.mytourismapp.databinding.LayoutProgressbarBinding

/**
 * Fragmento base para manejar estados de vista y navegación.
 *
 * @param S El tipo de estado que manejará el fragmento.
 * @param layout El recurso de diseño del fragmento.
 */
abstract class BaseFragment<S>(layout: Int) : Fragment(layout) {

    private val layoutProgressbarBinding: LayoutProgressbarBinding by lazy {
        val view = requireActivity().findViewById<View>(android.R.id.content) as ViewGroup
        val constraintLayout = layoutInflater.inflate(R.layout.layout_progressbar, view, false)
        view.addView(constraintLayout)
        LayoutProgressbarBinding.bind(constraintLayout)
    }

    protected abstract fun viewModel(): BaseViewModel<S>

    protected abstract fun onUpdateState(state: S)

    private val viewStateHandler: BaseViewStateHandler<S> by lazy {
        BaseViewStateHandler(
            context = requireContext(),
            onUpdateState = ::onUpdateState,
            showLoading = ::showLoading,
            hideLoading = ::hideLoading,
            navigate = { navResId, bundle -> findNavController().navigate(navResId, bundle) },
            goIntent = {}
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel().apply {
            viewState.observe(viewLifecycleOwner) { viewStateHandler.manageViewState(it) }
        }
    }

    open fun showLoading() {
        layoutProgressbarBinding.clProgressBar.visible()
    }

    open fun hideLoading() {
        layoutProgressbarBinding.clProgressBar.gone()
    }
}