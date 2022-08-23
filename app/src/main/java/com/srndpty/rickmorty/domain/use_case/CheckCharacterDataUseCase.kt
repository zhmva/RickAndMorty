package com.srndpty.rickmorty.domain.use_case

import com.srndpty.rickmorty.domain.repo.CharacterLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckCharacterDataUseCase @Inject constructor(
    private val repository: CharacterLocalRepository
) {
    suspend fun checkCharacterData(): Flow<Boolean> = repository.checkCharacterData()
}