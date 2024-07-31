package com.heyrex.mytourismapp.data.repository

import com.heyrex.mytourismapp.core.data.api.ApiService
import com.heyrex.mytourismapp.core.domain.RequestFailure
import com.heyrex.mytourismapp.features.detail.data.DetailRepositoryImpl
import com.heyrex.mytourismapp.features.detail.data.request.DetailTourismRequest
import com.heyrex.mytourismapp.features.detail.data.response.DetailDataAlbumTourismResponse
import com.heyrex.mytourismapp.features.detail.data.response.DetailDataGeoTourismResponse
import com.heyrex.mytourismapp.features.detail.data.response.DetailDataTourismResponse
import com.heyrex.mytourismapp.features.detail.data.response.DetailTourismResponse
import com.heyrex.mytourismapp.features.detail.domain.model.toModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailRepositoryImplTest {

    private lateinit var apiService: ApiService
    private lateinit var detailRepository: DetailRepositoryImpl

    @Before
    fun setUp() {
        apiService = mockk()
        detailRepository = DetailRepositoryImpl(apiService)
    }

    @Test
    fun `login should return success result when api call is successful`() = runTest {
        val placeId = ""
        val dataResponse = DetailTourismResponse(
            data = DetailDataTourismResponse(
                id = "id",
                title = "title",
                description = "description",
                image = "image",
                geo = DetailDataGeoTourismResponse("", ""),
                album = listOf(DetailDataAlbumTourismResponse("", "")),
            )
        )
        val response = mockk<retrofit2.Response<DetailTourismResponse>> {
            every { body() } returns dataResponse
            every { isSuccessful } returns true
        }

        coEvery { apiService.getDetail(DetailTourismRequest(placeId)) } returns response

        val result = detailRepository.getDetail(placeId).first()

        Assert.assertTrue(result.isSuccess)
        Assert.assertEquals(dataResponse.data.toModel(), result.getOrNull())
    }

    @Test
    fun `login should return failure result when api call throws exception`() = runTest {
        val placeId = ""
        val exception = RequestFailure.UnknownError

        coEvery { apiService.getDetail(DetailTourismRequest(placeId)) } throws exception

        val result = detailRepository.getDetail(placeId).first()

        Assert.assertTrue(result.isFailure)
        Assert.assertEquals(exception, result.exceptionOrNull())
    }
}