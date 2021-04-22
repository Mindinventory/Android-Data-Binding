package com.example.hiltwithcoroutine.common.exception

import com.example.databindingsample.data.base.MetaResponse
import java.io.IOException

open class AppHttpException(private val response: MetaResponse) : IOException() {

    override val message: String?
        get() = response.message

    class ServerException(response: MetaResponse) : AppHttpException(response)
    class UnauthorizedException(response: MetaResponse) : AppHttpException(response)
    class UnProcessableException(response: MetaResponse) : AppHttpException(response)
    class UnExpectedException(response: MetaResponse) : AppHttpException(response)
    class ClientException(response: MetaResponse) : AppHttpException(response)
}