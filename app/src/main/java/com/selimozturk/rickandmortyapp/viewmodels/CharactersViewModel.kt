package com.selimozturk.rickandmortyapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.selimozturk.rickandmortyapp.data.network.entities.characterDataListToCharacterDomainDataList
import com.selimozturk.rickandmortyapp.data.repository.CharacterRepository
import com.selimozturk.rickandmortyapp.domain.models.CharacterDomainData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
): ViewModel() {
    fun getAllFavoriteCharacter():List<CharacterDomainData>{
        return characterRepository.getAllFavoriteCharacters()
    }

    fun getAllCharacters(status: String, name: String): LiveData<PagingData<CharacterDomainData>> {
        return characterRepository.getAllCharacters(status,name).characterDataListToCharacterDomainDataList().cachedIn(viewModelScope)
    }
}