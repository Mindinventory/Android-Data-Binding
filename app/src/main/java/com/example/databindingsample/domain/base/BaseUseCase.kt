package com.example.databindingsample.domain.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseUseCase<out S, in T> where S : Any {

    abstract suspend fun execute(params: T): S

    operator fun invoke(
        context: CoroutineContext = Dispatchers.IO,
        scope: CoroutineScope,
        params: T,
        onResult: (Result<Throwable, S>) -> Unit = {}
    ) {
        val job = scope.async(context) { either { execute(params) } }
        scope.launch(Dispatchers.Main) { onResult(job.await()) }
    }

    class None
}
