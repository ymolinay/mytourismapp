package com.heyrex.mytourismapp.features.list.data

import com.heyrex.mytourismapp.core.data.api.ApiService
import com.heyrex.mytourismapp.core.data.api.FailureFactory
import com.heyrex.mytourismapp.core.extensions.safeCall
import com.heyrex.mytourismapp.features.list.domain.model.PlaceSimple
import com.heyrex.mytourismapp.features.list.domain.model.toModel
import com.heyrex.mytourismapp.features.list.domain.repository.ListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ListRepositoryImpl(private val apiService: ApiService) : ListRepository {

    override fun getList(): Flow<Result<List<PlaceSimple>?>> = flow {
        val request = apiService.getList()
        val response = request.safeCall({ listResponse ->
            listResponse.data.map { it.toModel() } ?: emptyList()
        })
        emit(response)
    }.catch {
        emit(FailureFactory<List<PlaceSimple>>().handleException(it))
    }

}