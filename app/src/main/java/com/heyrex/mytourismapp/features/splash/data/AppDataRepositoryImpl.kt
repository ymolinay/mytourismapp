package com.heyrex.mytourismapp.features.splash.data

import com.google.firebase.firestore.FirebaseFirestore
import com.heyrex.mytourismapp.core.data.api.FailureFactory
import com.heyrex.mytourismapp.features.splash.domain.model.AppData
import com.heyrex.mytourismapp.features.splash.domain.repository.AppDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AppDataRepositoryImpl(
    private val firestore: FirebaseFirestore
) : AppDataRepository {

    override fun getAppData(): Flow<Result<AppData>> = flow {
        val documentSnapshot = firestore
            .collection("mytourismapp")
            .document("appdata")
            .get()
            .await()
        val result = documentSnapshot.toObject(AppData::class.java) ?: AppData()
        emit(Result.success(result))
    }.catch {
        emit(FailureFactory<AppData>().handleException(it))
    }

}