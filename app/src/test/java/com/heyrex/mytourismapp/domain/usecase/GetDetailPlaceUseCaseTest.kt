package com.heyrex.mytourismapp.domain.usecase

import com.heyrex.mytourismapp.features.detail.domain.repository.DetailRepository
import com.heyrex.mytourismapp.features.detail.domain.usecase.GetDetailPlaceUseCase
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetDetailPlaceUseCaseTest {

    lateinit var sut: GetDetailPlaceUseCase

    @Mock
    lateinit var detailRepository: DetailRepository

    @Before
    fun setup() {
        initInteractor()
    }

    private var placeId: String = ""
    private fun initInteractor() {
        sut = GetDetailPlaceUseCase(detailRepository)
    }

    @Test
    @Throws(Exception::class)
    fun testOnGetSampleCallGetRemoteObjectOnlyOnce() {
        runBlocking { sut.execute(placeId) }
        verify(detailRepository, times(1)).getDetail(placeId)
    }
}