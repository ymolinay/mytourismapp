package com.heyrex.mytourismapp.data.repository

import com.heyrex.mytourismapp.core.data.api.ApiService
import com.heyrex.mytourismapp.core.domain.RequestFailure
import com.heyrex.mytourismapp.features.list.data.ListRepositoryImpl
import com.heyrex.mytourismapp.features.list.data.response.ItemTourismResponse
import com.heyrex.mytourismapp.features.list.data.response.ListTourismResponse
import com.heyrex.mytourismapp.features.list.domain.model.toModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class ListRepositoryImplTest {

    private lateinit var apiService: ApiService
    private lateinit var ListRepository: ListRepositoryImpl

    @Before
    fun setUp() {
        apiService = mockk()
        ListRepository = ListRepositoryImpl(apiService)
    }

    @Test
    fun `getFriends should return success result when api call is successful`() = runTest {
        val itemDataResponse =
            ItemTourismResponse("1", "Huaraz", "Huaraz", "Huaraz")
        val itemsResponse = ListTourismResponse(listOf(itemDataResponse))
        val response = mockk<Response<ListTourismResponse>> {
            every { body() } returns itemsResponse
            every { isSuccessful } returns true
        }

        coEvery { apiService.getList() } returns response

        val result = ListRepository.getList().first()

        Assert.assertTrue(result.isSuccess)
        Assert.assertEquals(itemsResponse.data.map { it.toModel() }, result.getOrNull())
    }

    @Test
    fun `getFriends should return failure result when api call throws exception`() = runTest {
        val exception = RequestFailure.UnknownError

        coEvery { apiService.getList() } throws exception

        val result = ListRepository.getList().first()

        Assert.assertTrue(result.isFailure)
        Assert.assertEquals(exception, result.exceptionOrNull())
    }
}