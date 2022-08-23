package com.srndpty.rickmorty.data.local.characters.dao

import androidx.room.*
import com.srndpty.rickmorty.data.local.characters.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {
//    @Query("SELECT * FROM character_entity")
//    suspend fun getCheckData(): Flow<List<CharacterEntity>>

    @Delete
    suspend fun deleteCharacterEntity(characterEntity: CharacterEntity)

    @Query("SELECT * FROM character_entity WHERE id =:id")
    suspend fun getCharacterEntityById(id: Int): CharacterEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg characters: CharacterEntity)

    @Query("SELECT * FROM character_entity ")
    suspend fun getAll(): Flow<List<CharacterEntity>>
}
