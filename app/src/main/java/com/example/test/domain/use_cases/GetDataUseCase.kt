package com.example.test.domain.use_cases

import com.example.test.domain.entities.Data
import com.example.test.domain.repositories.Repository
import com.example.test.domain.use_cases.base.BaseUseCase
import javax.inject.Inject

class GetDataUseCase @Inject constructor(
    private val repository: Repository
) : BaseUseCase<Unit, Data>() {
    override fun execute(params: Unit): Data {
        return repository.getData()
    }
}