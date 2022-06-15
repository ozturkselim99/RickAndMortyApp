package com.selimozturk.rickandmortyapp.domain.models

import com.selimozturk.rickandmortyapp.data.local.entities.FavoriteCharacter
import com.selimozturk.rickandmortyapp.data.network.entities.CharacterData

data class CharacterDomainData(
    val id: Int,
    val image: String,
    val name: String,
    val status: String,
    val species: String,
    var isFavorite: Boolean = false
)

fun CharacterDomainData.characterDomainDataToFavoriteCharacter(): FavoriteCharacter {
    return FavoriteCharacter(
        id, image, name, status, species
    )
}

fun List<FavoriteCharacter>.favoriteCharacterListToCharacterDomainDataList(): List<CharacterDomainData> {
    return map {
        CharacterDomainData(
            id = it.id,
            image = it.image,
            name = it.name,
            status = it.status,
            species = it.species,
        )
    }
}

fun List<CharacterData>.characterDataListToCharacterDomainDataList(): List<CharacterDomainData> {
    return map {
        CharacterDomainData(
            id = it.id,
            image = it.image,
            name = it.name,
            status = it.status,
            species = it.species,
        )
    }
}
