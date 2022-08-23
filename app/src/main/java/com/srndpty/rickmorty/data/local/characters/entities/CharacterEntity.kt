package com.srndpty.rickmorty.data.local.characters.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.srndpty.rickmorty.data.common.utils.Characters

@Entity(tableName = Characters.name)
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
)

