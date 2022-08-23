package com.srndpty.rickmorty.domain.use_case

import com.srndpty.rickmorty.domain.entity.Character
import com.srndpty.rickmorty.domain.repo.CharacterLocalRepository
import javax.inject.Inject

class ActionCharacterEntityUseCase  @Inject constructor(
    private val repository: CharacterLocalRepository
) {
    suspend fun actionAnimeEntity(character: Character, id: Int, isExists: Boolean) =
        repository.actionCharacterEntity(character, id, isExists)
}