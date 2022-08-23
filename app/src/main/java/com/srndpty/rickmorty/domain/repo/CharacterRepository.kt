package com.srndpty.rickmorty.domain.repo

import androidx.paging.PagingData
import com.srndpty.rickmorty.domain.entity.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getSearchCharacter(searchBy: String): Flow<PagingData<Character>>

    suspend fun getCharacter(id: Int): Flow<PagingData<Character>>

}