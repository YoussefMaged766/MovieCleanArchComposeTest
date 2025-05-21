package com.devYoussef.cleanarchtest.data.repository

import com.devYoussef.cleanarchtest.data.remote.ApiService
import com.devYoussef.cleanarchtest.domain.repository.remote.IRestApiNetworkProvider
import java.io.File
import java.lang.reflect.Type

class RetrofitRestApiNetworkProviderImpl(private val apiService: ApiService) :
    IRestApiNetworkProvider {
    override suspend fun <ResponseBody> get(
        pathUrl: String,
        headers: Map<String, Any>?,
        queryParams: Map<String, Any>?,
        responseType: Type
    ): ResponseBody {
        val response = apiService.get(
            pathUrl = pathUrl,
            headers = headers ?: mapOf(),
            queryParams = queryParams ?: mapOf()
        )
        return response.body() as ResponseBody
    }

    override suspend fun <RequestBody, ResponseBody> post(
        pathUrl: String,
        headers: Map<String, Any>?,
        queryParams: Map<String, Any>?,
        requestBody: RequestBody?,
        responseType: Type
    ): ResponseBody {
        TODO("Not yet implemented")
    }

    override suspend fun <RequestBody, ResponseBody> put(
        pathUrl: String,
        headers: Map<String, Any>?,
        queryParams: Map<String, Any>?,
        requestBody: RequestBody?,
        responseType: Type
    ): ResponseBody {
        TODO("Not yet implemented")
    }

    override suspend fun <ResponseBody> delete(
        pathUrl: String,
        headers: Map<String, Any>?,
        queryParams: Map<String, Any>?,
        responseType: Type
    ): ResponseBody {
        TODO("Not yet implemented")
    }

    override suspend fun <ResponseBody> postWithFiles(
        pathUrl: String,
        headers: Map<String, Any>?,
        queryParams: Map<String, Any>?,
        requestBody: Map<String, Any>?,
        files: Map<String, List<File>>?,
        responseType: Type
    ): ResponseBody {
        TODO("Not yet implemented")
    }
}