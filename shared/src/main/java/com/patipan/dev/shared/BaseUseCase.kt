package com.patipan.dev.shared

import com.patipan.dev.model.Failed
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class BaseUseCase<in Params, out Type> where Type : Any {
    abstract suspend fun run(params: Params): Either<Failed, Type>

    operator fun invoke(params: Params, onResult: (Either<Failed, Type>) -> Unit) {
        val job = GlobalScope.async { run(params) }
        GlobalScope.launch { onResult(job.await()) }
    }
}