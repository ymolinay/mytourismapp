package com.heyrex.mytourismapp.core.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
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

        val viewStateHandler = BaseViewStateHandler(
            context = this,
            onUpdateState = ::onUpdateState,
            showLoading = ::showLoading,
            hideLoading = ::hideLoading,
            navigate = { _, _ -> },
            goIntent = ::goIntent
        )

        viewModel().apply {
            viewState.observe(this@BaseActivity) { viewStateHandler.manageViewState(it) }
        }
    }

    protected abstract fun viewModel(): BaseViewModel<S>

    protected abstract fun onUpdateState(state: S)

    fun goIntent(goIntent: Activity) {
        val intent = Intent(this, goIntent::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        result.launch(intent)
    }

    protected abstract fun showLoading()

    protected abstract fun hideLoading()
}