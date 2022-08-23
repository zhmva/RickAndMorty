package com.srndpty.rickmorty.data.local.characters.repo

import com.srndpty.rickmorty.base.fromToList
import com.srndpty.rickmorty.data.local.characters.dao.CharactersDao
import com.srndpty.rickmorty.data.local.characters.utils.CharacterEntityToCharacter
import com.srndpty.rickmorty.data.local.characters.utils.CharacterToCharacterEntity
import com.srndpty.rickmorty.domain.entity.Character
import com.srndpty.rickmorty.domain.repo.CharacterLocalRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterLocalRepositoryImpl @Inject constructor(
    private val charactersDao: CharactersDao
): CharacterLocalRepository{


    private val characterEntityToCharacter = CharacterEntityToCharacter()

    private val characterToCharacterEntity = CharacterToCharacterEntity()


    override suspend fun checkCharacterData(): Flow<Boolean> = callbackFlow {
        charactersDao.getAll().collect {
            if (it.isEmpty()) {
                trySend(true)
            } else {
                trySend(false)
            }
        }
        awaitClose {
            trySend(false)
        }
    }

    override suspend fun checkExistsCharacterEntity(id: Int): Flow<Boolean> = callbackFlow {
        charactersDao.getAll().collect {
            for (item in it) {
                if (item.id == id) {
                    trySend(true)
                } else {
                    trySend(false)
                }
            }
        }
        awaitClose {
            trySend(false)
        }
    }

    override suspend fun actionCharacterEntity(character: Character,id: Int, isExists: Boolean) {
        if (isExists) {
            val characterEntity = charactersDao.getCharacterEntityById(id)
            charactersDao.deleteCharacterEntity(characterEntity)
        } else {
            val characterEntity = characterToCharacterEntity.invoke(character)
            charactersDao.insertAll(characterEntity)
        }
    }

    override suspend fun getCharacterEntity(): Flow<List<Character>> = flow {
        charactersDao.getAll().collect {
            val characterList = characterEntityToCharacter.fromToList(it)
            if (characterList != null) {
                emit(characterList)
            }
        }
    }


}
