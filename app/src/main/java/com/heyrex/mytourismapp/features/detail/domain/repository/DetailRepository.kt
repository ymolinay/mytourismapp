package com.heyrex.mytourismapp.features.detail.domain.repository

import com.heyrex.mytourismapp.features.detail.domain.model.PlaceDetail
import kotlinx.coroutines.flow.Flow

interface DetailRepository {

    @Throws(Exception::class)
    fun getDetail(id: String): Flow<Result<PlaceDetail?>>

}