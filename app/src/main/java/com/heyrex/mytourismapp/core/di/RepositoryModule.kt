package com.heyrex.mytourismapp.core.di

import com.google.firebase.firestore.FirebaseFirestore
import com.heyrex.mytourismapp.core.data.api.ApiService
import com.heyrex.mytourismapp.features.detail.data.DetailRepositoryImpl
import com.heyrex.mytourismapp.features.detail.domain.repository.DetailRepository
import com.heyrex.mytourismapp.features.list.data.ListRepositoryImpl
import com.heyrex.mytourismapp.features.list.domain.repository.ListRepository
import com.heyrex.mytourismapp.features.splash.data.AppDataRepositoryImpl
import com.heyrex.mytourismapp.features.splash.domain.repository.AppDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideListRepository(
        apiService: ApiService,
    ): ListRepository {
        return ListRepositoryImpl(
            apiService = apiService,
        )
    }

    @Provides
    @Singleton
    fun provideDetailRepository(
        apiService: ApiService,
    ): DetailRepository {
        return DetailRepositoryImpl(
            apiService = apiService,
        )
    }

    @Provides
    @Singleton
    fun provideAppDataRepository(
        firestore: FirebaseFirestore,
    ): AppDataRepository {
        return AppDataRepositoryImpl(
            firestore = firestore,
        )
    }

}