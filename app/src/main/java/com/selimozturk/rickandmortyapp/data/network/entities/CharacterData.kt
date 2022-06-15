package com.selimozturk.rickandmortyapp.data.network.entities

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.PagingData
import androidx.paging.map
import com.selimozturk.rickandmortyapp.domain.models.CharacterDetailDomainData
import com.selimozturk.rickandmortyapp.domain.models.CharacterDomainData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

data class CharacterData(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterOrigin,
    val location: CharacterLocation,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

fun LiveData<PagingData<CharacterData>>.characterDataListToCharacterDomainDataList(): LiveData<PagingData<CharacterDomainData>> {
    return map { pagingData ->
        pagingData.map {
            CharacterDomainData(
                id = it.id,
                image = it.image,
                name = it.name,
                status = it.status,
                species = it.species,
            )
        }
    }
}

fun CharacterData.characterDataToCharacterDetailDomainData(): CharacterDetailDomainData {
    return CharacterDetailDomainData(
        id,
        image,
        name,
        status,
        species,
        episode.size.toString(),
        gender,
        origin.name,
        location.name,
        getLastSeenEpisodeInfo(episode.last().toString())
    )
}

private fun getLastSeenEpisodeInfo(url: String): String {
    var lastSeenEpisodeInfo: String
    runBlocking {
        withContext(Dispatchers.IO) {
            val jsonData = URL(url).readText()
            val jsonObject = JSONObject(jsonData)
            lastSeenEpisodeInfo =
                jsonObject.getString("name") + " - " + jsonObject.getString("air_date")
        }
    }
    return lastSeenEpisodeInfo
}