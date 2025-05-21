package com.devYoussef.cleanarchtest.domain.repository.remote

import java.io.File
import java.lang.reflect.Type

interface IRestApiNetworkProvider {
    suspend fun <ResponseBody> get(
        pathUrl: String,
        headers: Map<String, Any>? = null,
        queryParams: Map<String, Any>? = null,
        responseType: Type
    ): ResponseBody

    suspend fun <RequestBody, ResponseBody> post(
        pathUrl: String,
        headers: Map<String, Any>? = null,
        queryParams: Map<String, Any>? = null,
        requestBody: RequestBody? = null,
        responseType: Type
    ): ResponseBody

    suspend fun <RequestBody, ResponseBody> put(
        pathUrl: String,
        headers: Map<String, Any>? = null,
        queryParams: Map<String, Any>? = null,
        requestBody: RequestBody? = null,
        responseType: Type
    ): ResponseBody

    suspend fun <ResponseBody> delete(
        pathUrl: String,
        headers: Map<String, Any>? = null,
        queryParams: Map<String, Any>? = null,
        responseType: Type,
    ): ResponseBody

    suspend fun <ResponseBody> postWithFiles(
        pathUrl: String,
        headers: Map<String, Any>? = null,
        queryParams: Map<String, Any>? = null,
        requestBody: Map<String, Any>? = null,
        files: Map<String, List<File>>? = null,
        responseType: Type
    ): ResponseBody
}