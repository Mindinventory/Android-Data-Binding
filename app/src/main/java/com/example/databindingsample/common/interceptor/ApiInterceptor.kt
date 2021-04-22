package com.example.databindingsample.common.interceptor

import com.example.hiltwithcoroutine.common.exception.AppHttpException
import com.example.hiltwithcoroutine.common.interceptor.AppHttpStatus
import com.example.databindingsample.data.base.BaseResponse
import com.example.databindingsample.data.base.MetaResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import timber.log.Timber
import javax.inject.Inject

class ApiInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = getRequest(chain.request())
        return handleResponse(chain.proceed(builder.build()))
    }

    private fun getRequest(request: Request): Request.Builder = request.newBuilder()

    private fun handleResponse(response: Response): Response {
        return when (response.code) {
            in AppHttpStatus.SUCCESS.codes -> response
            in AppHttpStatus.UN_AUTHORIZED.codes -> {
                val body = response.peekBody(Long.MAX_VALUE)
                parseErrorResponse(body).run {
                    throw AppHttpException.UnauthorizedException(this)
                }
            }
            in AppHttpStatus.UN_PROCESSABLE_ENTITY.codes -> {
                val body = response.peekBody(Long.MAX_VALUE)
                parseErrorResponse(body).run {
                    throw AppHttpException.UnProcessableException(this)
                }
            }
            in AppHttpStatus.CLIENT_ERRORS.codes -> {
                val body = response.peekBody(Long.MAX_VALUE)
                parseServerErrorResponse(body).run {
                    throw AppHttpException.ClientException(this)
                }
            }
            in AppHttpStatus.SERVER_ERRORS.codes -> {
                val body = response.peekBody(Long.MAX_VALUE)
                parseServerErrorResponse(body).run {
                    throw AppHttpException.ServerException(this)
                }
            }
            else -> throw getUnExpectedException()
        }
    }

    private fun parseErrorResponse(body: ResponseBody): MetaResponse {
        val json = Json.parseToJsonElement(body.string())
        body.string()
            .takeIf { it.isNotEmpty() }?.let {
                try {
                    return Json.decodeFromString<BaseResponse>(json.toString()).meta
                } catch (e: Exception) {
                    Timber.e("parseErrorResponse >> $e")
                    throw getUnExpectedException()
                }
            } ?: throw  getUnExpectedException()

    }

    private fun parseServerErrorResponse(body: ResponseBody): MetaResponse {
        val json = Json.parseToJsonElement(body.string())
        body.string()
            .takeIf { it.isNotEmpty() }?.let {
                try {
                    return Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                    }.decodeFromString(json.toString())
                } catch (e: Exception) {
                    Timber.e("parseErrorResponse >> $e")
                    throw getUnExpectedException()
                }
            } ?: throw  getUnExpectedException()
    }

    private fun getUnExpectedException(): AppHttpException.UnExpectedException {
        return AppHttpException.UnExpectedException(
            MetaResponse(
                0,
                "UnExpectedException"
            )
        )
    }
}
