package com.alexcao.baseproject.core.use_case

abstract class BaseSuspendUseCase<in Params, out T> {
    abstract suspend fun execute(params: Params): T
}