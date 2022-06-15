package com.selimozturk.rickandmortyapp.data.network.entities

data class ResponseCharacters(
    val info: Info,
    val results: List<CharacterData>
)

