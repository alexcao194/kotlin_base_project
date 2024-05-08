package com.example.test.domain.use_cases

import com.example.test.domain.entities.Data
import com.example.test.domain.repositories.Repository
import com.example.test.domain.use_cases.base.BaseSuspendUseCase
import javax.inject.Inject

class SuspendGetDataUseCase @Inject constructor(
    private val repository: Repository
) : BaseSuspendUseCase<Unit, Data>() {
    override suspend fun execute(params: Unit): Data {
        return repository.getDataSuspending()
    }
}