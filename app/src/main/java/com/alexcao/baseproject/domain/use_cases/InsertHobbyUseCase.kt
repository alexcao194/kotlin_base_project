package com.alexcao.baseproject.domain.use_cases

import com.alexcao.baseproject.core.use_case.BaseSuspendUseCase
import com.alexcao.baseproject.domain.entities.Hobby
import com.alexcao.baseproject.domain.repositories.Repository
import javax.inject.Inject

class InsertHobbyUseCase @Inject constructor(
    private val repository: Repository
) : BaseSuspendUseCase<Hobby, Unit>() {
    override suspend fun execute(params: Hobby) {
        return repository.insertHobby(params)
    }

}