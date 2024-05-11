package com.example.test.domain.use_cases

import com.example.test.core.use_case.BaseSuspendUseCase
import com.example.test.domain.entities.Hobby
import com.example.test.domain.repositories.Repository
import javax.inject.Inject

class InsertHobbyUseCase @Inject constructor(
    private val repository: Repository
) : BaseSuspendUseCase<Hobby, Unit>() {
    override suspend fun execute(params: Hobby) {
        return repository.insertHobby(params)
    }

}