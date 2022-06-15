package com.selimozturk.rickandmortyapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_favorites")
data class FavoriteCharacter(
    @PrimaryKey val id: Int,
    val image: String,
    val name: String,
    val status: String,
    val species: String,
)