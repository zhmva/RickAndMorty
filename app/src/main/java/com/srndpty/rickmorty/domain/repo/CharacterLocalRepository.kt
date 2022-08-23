package com.srndpty.rickmorty.domain.repo

import com.srndpty.rickmorty.domain.entity.Character
import kotlinx.coroutines.flow.Flow

interface CharacterLocalRepository {
    suspend fun checkCharacterData(): Flow<Boolean >
    suspend fun checkExistsCharacterEntity(id: Int):Flow<Boolean>
    suspend fun actionCharacterEntity(character: Character, id: Int, isExists: Boolean)
    suspend fun getCharacterEntity(): Flow<List<Character>>
}