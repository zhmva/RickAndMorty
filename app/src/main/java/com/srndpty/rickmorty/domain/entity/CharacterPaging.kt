package com.srndpty.rickmorty.domain.entity

data class CharacterPaging(
    val character :  List<Character>,
    val current_page: Int,
    val has_next_page: Boolean
){
    companion object{
        fun create(character: List<Character>, current_page: Int, has_next_page: Boolean) =
            CharacterPaging(character, current_page, has_next_page)
    }
}
