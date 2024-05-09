package com.example.test.core.use_case

abstract class BaseUseCase<in Params, out T> {
        abstract fun execute(params: Params): T
}