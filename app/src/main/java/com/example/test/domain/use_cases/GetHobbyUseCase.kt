package com.example.test.domain.use_cases

import com.example.test.core.use_case.BaseSuspendUseCase
import com.example.test.domain.repositories.Repository
import javax.inject.Inject

class GetHobbyUseCase @Inject constructor(
    private val repository: Repository
) : BaseSuspendUseCase<Unit, Unit>(){
    override suspend fun execute(params: Unit) {
        repository.getHobbies()
    }
}