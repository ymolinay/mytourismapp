package com.heyrex.mytourismapp.core.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<V : ViewBinding, S> : AppCompatActivity() {

    private val result =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    lateinit var binding: V

    private fun setLayout(binding: V) {
        setContentView(binding.root)
    }

    protected abstract fun getViewBinding(): V

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        binding = getViewBinding()
        setLayout(binding)

        viewModel().apply {
            viewState.observe(this@BaseActivity) { manageViewState(it) }
        }
    }

    protected abstract fun viewModel(): BaseViewModel<S>

    protected abstract fun onUpdateState(state: S)

    private fun manageViewState(viewModelState: ViewModelState<S>) {
        viewModelState.getContentIfNotHandled()?.let {
            hideLoading()
            when (viewModelState) {
                is ViewModelState.InProgress -> showLoading()
                is ViewModelState.Message -> showError(getString(viewModelState.idMessage))
                is ViewModelState.ApiError -> showError(viewModelState.message)
                is ViewModelState.Navigate -> {}
                is ViewModelState.GoIntent -> goIntent(viewModelState.goIntent)

                else -> onUpdateState((viewModelState as ViewModelState.Loaded).value)
            }
        }
    }

    fun goIntent(goIntent: Activity) {
        val intent = Intent(this, goIntent::class.java)
        result.launch(intent)
    }

    protected abstract fun showLoading()

    protected abstract fun hideLoading()

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}