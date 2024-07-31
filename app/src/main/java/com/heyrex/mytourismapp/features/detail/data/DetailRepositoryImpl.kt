package com.heyrex.mytourismapp.features.detail.data

import com.heyrex.mytourismapp.core.data.api.ApiService
import com.heyrex.mytourismapp.core.data.api.FailureFactory
import com.heyrex.mytourismapp.core.extensions.safeCall
import com.heyrex.mytourismapp.features.detail.data.request.DetailTourismRequest
import com.heyrex.mytourismapp.features.detail.domain.model.PlaceDetail
import com.heyrex.mytourismapp.features.detail.domain.model.toModel
import com.heyrex.mytourismapp.features.detail.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class DetailRepositoryImpl(private val apiService: ApiService) : DetailRepository {

    override fun getDetail(id: String): Flow<Result<PlaceDetail?>> = flow {
        val request = apiService.getDetail(DetailTourismRequest(id))
        val response = request.safeCall({ detailResponse ->
            detailResponse.data.toModel()
        })
        emit(response)
    }.catch {
        emit(FailureFactory<PlaceDetail>().handleException(it))
    }

}