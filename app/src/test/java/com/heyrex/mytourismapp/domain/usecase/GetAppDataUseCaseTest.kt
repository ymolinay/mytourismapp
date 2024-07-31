package com.heyrex.mytourismapp.domain.usecase

import com.heyrex.mytourismapp.features.splash.domain.repository.AppDataRepository
import com.heyrex.mytourismapp.features.splash.domain.usecase.GetAppDataUseCase
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetAppDataUseCaseTest {

    lateinit var sut: GetAppDataUseCase

    @Mock
    lateinit var appDataRepository: AppDataRepository

    @Before
    fun setup() {
        initInteractor()
    }

    private fun initInteractor() {
        sut = GetAppDataUseCase(appDataRepository)
    }

    @Test
    @Throws(Exception::class)
    fun testOnGetSampleCallGetRemoteObjectOnlyOnce() {
        runBlocking { sut.execute() }
        verify(appDataRepository, times(1)).getAppData()
    }

}