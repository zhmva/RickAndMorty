package com.srndpty.rickmorty.domain.use_case

import androidx.paging.PagingData
import com.srndpty.rickmorty.domain.entity.Character
import com.srndpty.rickmorty.domain.repo.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend fun getSearchCharacter(searchBy: String): Flow<PagingData<Character>> =
        repository.getSearchCharacter(searchBy)
}