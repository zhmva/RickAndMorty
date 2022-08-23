package com.srndpty.rickmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.srndpty.rickmorty.data.local.characters.dao.CharactersDao
import com.srndpty.rickmorty.data.local.characters.entities.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getCharacterDao(): CharactersDao
}