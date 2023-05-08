package com.selimozturk.rickandmortyapp.ui.character_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selimozturk.rickandmortyapp.data.repository.CharacterRepository
import com.selimozturk.rickandmortyapp.domain.models.CharacterDetailDomainData
import com.selimozturk.rickandmortyapp.domain.models.CharacterDomainData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel@Inject constructor(
    private val characterRepository: CharacterRepository,
): ViewModel(){
    private val _character = MutableLiveData<CharacterDetailDomainData>()
    val character: LiveData<CharacterDetailDomainData> get() = _character

    private var _loading: MutableLiveData<Boolean> = MutableLiveData(true)
    val loading: LiveData<Boolean> get() = _loading

    fun getCharacter(id:Int){
        viewModelScope.launch {
            if(characterRepository.getCharacter(id)!=null){
                _character.postValue(characterRepository.getCharacter(id))
                _loading.postValue(false)
            }
        }
    }

    fun insertFavoriteCharacter(characterDomainData: CharacterDomainData){
        viewModelScope.launch {
            characterRepository.insertFavoriteCharacter(characterDomainData)
        }
    }

    fun deleteFavoriteCharacter(characterId:Int){
        viewModelScope.launch {
            characterRepository.deleteFavoriteCharacter(characterId)
        }
    }
}