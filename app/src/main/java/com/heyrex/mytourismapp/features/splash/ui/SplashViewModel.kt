package com.heyrex.mytourismapp.features.splash.ui

import android.os.Bundle
import com.heyrex.mytourismapp.MainActivity
import com.heyrex.mytourismapp.core.extensions.getAppVersion
import com.heyrex.mytourismapp.core.extensions.storeVersionIsGreaterThanAppVersion
import com.heyrex.mytourismapp.core.presentation.BaseViewModel
import com.heyrex.mytourismapp.features.splash.domain.model.AppData
import com.heyrex.mytourismapp.features.splash.domain.usecase.GetAppDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
@Inject constructor(
    private val getAppDataUseCase: GetAppDataUseCase
) : BaseViewModel<SplashState>() {

    override fun init(arguments: Bundle) = Unit

    suspend fun getAppData() {
        showProgress()
        delay(3000)
        async(getAppDataUseCase.execute()) { result ->
            result?.let { data ->
                if (storeVersionIsGreaterThanAppVersion(data.minVersion, getAppVersion())) {
                    showErrorVersion(result)
                } else {
                    goIntent(MainActivity())
                }
            }
        }
    }

    private fun showErrorVersion(appData: AppData) {
        appData.apply {
            updateState(
                SplashState(
                    maintenance = maintenance,
                    urlSore = urlUpdate,
                    minVersion = minVersion
                )
            )
        }
    }
}