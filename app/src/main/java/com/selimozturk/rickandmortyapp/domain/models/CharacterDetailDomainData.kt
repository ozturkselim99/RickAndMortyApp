package com.selimozturk.rickandmortyapp.domain.models

data class CharacterDetailDomainData (
    val id: Int,
    val image: String,
    val name: String,
    val status: String,
    val species: String,
    val numberOfEpisode: String,
    val gender: String,
    val originLocationName: String,
    val lastKnowLocationName: String,
    val lastSeenEpisodeInfo: String,
)