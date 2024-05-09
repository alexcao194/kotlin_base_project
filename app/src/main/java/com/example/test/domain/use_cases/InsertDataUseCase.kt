package com.example.test.domain.use_cases

import com.example.test.core.use_case.BaseSuspendUseCase
import com.example.test.domain.entities.Data
import com.example.test.domain.repositories.Repository
import javax.inject.Inject

class InsertDataUseCase @Inject constructor(
    private val repository: Repository
) : BaseSuspendUseCase<Data, Unit>() {
    override suspend fun execute(params: Data) {
        return repository.insertData(params)
    }

}