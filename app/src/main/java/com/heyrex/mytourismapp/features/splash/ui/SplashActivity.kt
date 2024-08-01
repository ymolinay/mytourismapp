package com.heyrex.mytourismapp.features.splash.ui

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.heyrex.mytourismapp.R
import com.heyrex.mytourismapp.core.extensions.gone
import com.heyrex.mytourismapp.core.extensions.visible
import com.heyrex.mytourismapp.core.presentation.BaseActivity
import com.heyrex.mytourismapp.databinding.ActivitySplashBinding
import com.heyrex.mytourismapp.databinding.DialogCustomBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashState>() {

    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)

    private val splashViewModel: SplashViewModel by viewModels()

    override fun viewModel() = splashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MyTourismApp)
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            splashViewModel.getAppData()
        }
    }


    override fun onUpdateState(state: SplashState) {
        showCustomDialog(state.urlSore)
    }

    override fun showLoading() {
        binding.loading.visible()
    }

    override fun hideLoading() {
        binding.loading.gone()
    }

    private fun showCustomDialog(url: String) {
        val dialogView = DialogCustomBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView.root)
            .setCancelable(false)
            .create()

        dialogView.dialogButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
                setPackage("com.android.vending")
            }
            startActivity(intent)
            dialog.dismiss()
        }

        dialog.show()
    }
}