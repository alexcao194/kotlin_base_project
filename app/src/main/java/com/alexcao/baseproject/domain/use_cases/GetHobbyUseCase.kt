package com.alexcao.baseproject.domain.use_cases

import com.alexcao.baseproject.core.use_case.BaseSuspendUseCase
import com.alexcao.baseproject.domain.repositories.Repository
import javax.inject.Inject

class GetHobbyUseCase @Inject constructor(
    private val repository: Repository
) : BaseSuspendUseCase<Unit, Unit>(){
    override suspend fun execute(params: Unit) {
        repository.getHobbies()
    }
}