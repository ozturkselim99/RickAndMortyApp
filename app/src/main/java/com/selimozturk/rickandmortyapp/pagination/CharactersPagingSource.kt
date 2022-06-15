package com.selimozturk.rickandmortyapp.pagination

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.selimozturk.rickandmortyapp.data.network.RickAndMortyApi
import com.selimozturk.rickandmortyapp.data.network.entities.CharacterData
import javax.inject.Inject

class CharactersPagingSource @Inject constructor(
    private val remoteDataSource: RickAndMortyApi,
    private val name: String,
    private val status: String
) : PagingSource<Int, CharacterData>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterData>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterData> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = remoteDataSource.getAllCharacters(status, name, nextPage)
            var nextPageNumber: Int? = null

            if (response.body()?.info?.next != null) {
                val uri = Uri.parse(response.body()!!.info.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }
            LoadResult.Page(
                data = response.body()!!.results,
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}