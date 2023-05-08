package com.selimozturk.rickandmortyapp.data.network

import com.selimozturk.rickandmortyapp.data.network.entities.CharacterData
import com.selimozturk.rickandmortyapp.data.network.entities.ResponseCharacters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("character")
    suspend fun getAllCharacters(
        @Query("status") status: String = "",
        @Query("name") name: String = "",
        @Query("page") page: Int? = null
    ): Response<ResponseCharacters>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<CharacterData>
}