package com.example.test.domain.use_cases.base

abstract class BaseUseCase<in Params, out T> {
        abstract fun execute(params: Params): T
}