package com.srndpty.rickmorty.data.local.characters.utils

import com.srndpty.rickmorty.base.BaseMapper
import com.srndpty.rickmorty.data.local.characters.entities.CharacterEntity
import com.srndpty.rickmorty.domain.entity.Character

class CharacterEntityToCharacter : BaseMapper<CharacterEntity, Character> {
    override fun invoke(p1: CharacterEntity): Character =
        Character.create(
            p1.id, p1.image, p1.name, p1.status,
            p1.type, p1.gender, p1.species)
}