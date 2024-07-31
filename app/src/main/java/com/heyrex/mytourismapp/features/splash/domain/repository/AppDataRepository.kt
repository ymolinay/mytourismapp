package com.heyrex.mytourismapp.features.splash.domain.repository

import com.heyrex.mytourismapp.features.splash.domain.model.AppData
import kotlinx.coroutines.flow.Flow

interface AppDataRepository {

    @Throws(Exception::class)
    fun getAppData(): Flow<Result<AppData>>
}