package com.heyrex.mytourismapp.features.list.domain.repository

import com.heyrex.mytourismapp.features.list.domain.model.PlaceSimple
import kotlinx.coroutines.flow.Flow

interface ListRepository {

    @Throws(Exception::class)
    fun getList(): Flow<Result<List<PlaceSimple>?>>

}