package com.selimozturk.rickandmortyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.selimozturk.rickandmortyapp.data.local.entities.FavoriteCharacter

@Database(entities = [FavoriteCharacter::class], version = 1)
abstract class RickAndMortyDatabase : RoomDatabase() {
    abstract val rickAndMortyDao: RickAndMortyDao
}