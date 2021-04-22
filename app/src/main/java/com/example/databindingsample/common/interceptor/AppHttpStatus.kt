package com.example.hiltwithcoroutine.common.interceptor

enum class AppHttpStatus(val codes: List<Int>) {
    SUCCESS(listOf(200, 201)),
    UN_AUTHORIZED(listOf(401, 403)),
    UN_PROCESSABLE_ENTITY(listOf(422)),
    CLIENT_ERRORS(listOf(400, 404, 409, 429)),
    SERVER_ERRORS(listOf(500)),
}
