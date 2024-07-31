package com.heyrex.mytourismapp.core.data.api

import com.heyrex.mytourismapp.features.detail.data.request.DetailTourismRequest
import com.heyrex.mytourismapp.features.detail.data.response.DetailTourismResponse
import com.heyrex.mytourismapp.features.list.data.response.ListTourismResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    companion object {
        //Headers
        const val CONTENT_TYPE = "Content-Type: application/json"
        const val ACCEPT = "Accept: application/json"
    }

    @Headers(CONTENT_TYPE, ACCEPT)
    @GET(AppURLs.VERSION + AppURLs.API + AppURLs.LIST)
    suspend fun getList(): Response<ListTourismResponse>

    @Headers(CONTENT_TYPE, ACCEPT)
    @POST(AppURLs.VERSION + AppURLs.API + AppURLs.DETAIL)
    suspend fun getDetail(@Body request: DetailTourismRequest): Response<DetailTourismResponse>

}