package com.heyrex.mytourismapp.features.splash.domain.usecase

import com.heyrex.mytourismapp.features.splash.domain.repository.AppDataRepository
import javax.inject.Inject

class GetAppDataUseCase
@Inject constructor(private val getAppDataRepository: AppDataRepository) {
    fun execute() = getAppDataRepository.getAppData()
}