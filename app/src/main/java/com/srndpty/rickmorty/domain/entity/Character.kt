package com.srndpty.rickmorty.domain.entity

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
){
    companion object{
        fun create(
        id: Int,
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String,
        image: String
        ) = Character(
            id, name, status,species,type, gender, image
        )
    }
}
