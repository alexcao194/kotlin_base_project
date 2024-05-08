package com.example.test.domain.use_cases.base

abstract class BaseSuspendUseCase<in Params, out T> {
    abstract suspend fun execute(params: Params): T
}