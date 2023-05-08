package com.selimozturk.rickandmortyapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.selimozturk.rickandmortyapp.data.local.entities.FavoriteCharacter

@Dao
interface RickAndMortyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCharacter(character: FavoriteCharacter)

    @Query("DELETE FROM character_favorites WHERE id = :characterId")
    suspend fun deleteFavoriteCharacter(characterId: Int)

    @Query("SELECT * FROM character_favorites")
    fun getAllFavoriteCharacters(): List<FavoriteCharacter>
}