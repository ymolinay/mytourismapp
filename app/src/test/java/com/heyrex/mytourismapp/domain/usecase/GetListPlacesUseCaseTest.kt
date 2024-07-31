package com.heyrex.mytourismapp.domain.usecase

import com.heyrex.mytourismapp.features.list.domain.repository.ListRepository
import com.heyrex.mytourismapp.features.list.domain.usecase.GetListPlacesUseCase
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetListPlacesUseCaseTest {

    lateinit var sut: GetListPlacesUseCase

    @Mock
    lateinit var listRepository: ListRepository

    @Before
    fun setup() {
        initInteractor()
    }

    private fun initInteractor() {
        sut = GetListPlacesUseCase(listRepository)
    }

    @Test
    @Throws(Exception::class)
    fun testOnGetSampleCallGetRemoteObjectOnlyOnce() {
        runBlocking { sut.execute() }
        verify(listRepository, times(1)).getList()
    }
}