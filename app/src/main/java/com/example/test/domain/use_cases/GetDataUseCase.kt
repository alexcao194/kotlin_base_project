package com.example.test.domain.use_cases

import com.example.test.domain.entities.Data
import com.example.test.domain.repositories.Repository
import com.example.test.core.use_case.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDataUseCase @Inject constructor(
    private val repository: Repository
) : BaseUseCase<Unit, Flow<List<Data>>>() {
    override fun execute(params: Unit): Flow<List<Data>> {
        return repository.getDataStream()
    }
}