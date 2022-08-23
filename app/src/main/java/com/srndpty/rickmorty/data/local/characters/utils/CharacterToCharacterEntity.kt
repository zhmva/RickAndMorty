package com.srndpty.rickmorty.data.local.characters.utils

import com.srndpty.rickmorty.base.BaseMapper
import com.srndpty.rickmorty.data.local.characters.entities.CharacterEntity
import com.srndpty.rickmorty.domain.entity.Character

class CharacterToCharacterEntity: BaseMapper<Character, CharacterEntity> {
        override fun invoke(p1: Character): CharacterEntity  = CharacterEntity(
            id = p1.id, name = p1.name, image = p1.image, status = p1.status, type = p1.type,
            gender = p1.gender, species = p1.species
        )
}