package com.selimozturk.rickandmortyapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.selimozturk.rickandmortyapp.data.local.RickAndMortyDao
import com.selimozturk.rickandmortyapp.data.network.RickAndMortyApi
import com.selimozturk.rickandmortyapp.data.network.entities.CharacterData
import com.selimozturk.rickandmortyapp.data.network.entities.characterDataToCharacterDetailDomainData
import com.selimozturk.rickandmortyapp.domain.models.CharacterDetailDomainData
import com.selimozturk.rickandmortyapp.domain.models.CharacterDomainData
import com.selimozturk.rickandmortyapp.domain.models.characterDomainDataToFavoriteCharacter
import com.selimozturk.rickandmortyapp.domain.models.favoriteCharacterListToCharacterDomainDataList
import com.selimozturk.rickandmortyapp.pagination.CharactersPagingSource
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val remoteDataSource: RickAndMortyApi,
    private val localDataSource: RickAndMortyDao,
) {
    suspend fun getCharacter(id: Int): CharacterDetailDomainData? {
        var character: CharacterDetailDomainData?=null
        val response = remoteDataSource.getCharacter(id)
        if (response.isSuccessful) {
            val characterData = response.body()
            characterData?.let {
                character = characterData.characterDataToCharacterDetailDomainData()
            }
        }
        return character
    }

    suspend fun insertFavoriteCharacter(characterDomainData: CharacterDomainData){
        localDataSource.insertFavoriteCharacter(characterDomainData.characterDomainDataToFavoriteCharacter())
    }

    fun getAllFavoriteCharacters():List<CharacterDomainData>{
        return localDataSource.getAllFavoriteCharacters().favoriteCharacterListToCharacterDomainDataList()
    }

    suspend fun deleteFavoriteCharacter(characterId:Int){
        localDataSource.deleteFavoriteCharacter(characterId)
    }

    fun getAllCharacters(status: String, name: String): LiveData<PagingData<CharacterData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
            ),
            pagingSourceFactory = {
                CharactersPagingSource(remoteDataSource, name,status)
            }
        ).liveData
    }
}