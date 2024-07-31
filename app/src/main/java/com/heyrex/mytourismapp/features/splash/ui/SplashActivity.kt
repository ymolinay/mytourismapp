package com.heyrex.mytourismapp.features.splash.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.heyrex.mytourismapp.core.extensions.gone
import com.heyrex.mytourismapp.core.extensions.visible
import com.heyrex.mytourismapp.core.presentation.BaseActivity
import com.heyrex.mytourismapp.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashState>() {

    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)

    private val splashViewModel: SplashViewModel by viewModels()

    override fun viewModel() = splashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            splashViewModel.getAppData()
        }
    }


    override fun onUpdateState(state: SplashState) {
        println(state.urlSore)
    }

    override fun showLoading() {
        binding.loading.visible()
    }

    override fun hideLoading() {
        binding.loading.gone()
    }
}