package com.example.test.domain.use_cases

import com.example.test.domain.entities.Hobby
import com.example.test.domain.repositories.Repository
import com.example.test.core.use_case.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHobbyStreamUseCase @Inject constructor(
    private val repository: Repository
) : BaseUseCase<Unit, Flow<List<Hobby>>>() {
    override fun execute(params: Unit): Flow<List<Hobby>> {
        return repository.getHobbyStream()
    }
}