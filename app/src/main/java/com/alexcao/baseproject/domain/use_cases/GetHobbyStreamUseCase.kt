package com.alexcao.baseproject.domain.use_cases

import com.alexcao.baseproject.domain.entities.Hobby
import com.alexcao.baseproject.domain.repositories.Repository
import com.alexcao.baseproject.core.use_case.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHobbyStreamUseCase @Inject constructor(
    private val repository: Repository
) : BaseUseCase<Unit, Flow<List<Hobby>>>() {
    override fun execute(params: Unit): Flow<List<Hobby>> {
        return repository.getHobbyStream()
    }
}