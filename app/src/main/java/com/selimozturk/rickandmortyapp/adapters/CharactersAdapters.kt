package com.selimozturk.rickandmortyapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.selimozturk.rickandmortyapp.databinding.CharacterItemGridBinding
import com.selimozturk.rickandmortyapp.databinding.CharacterItemRowBinding
import com.selimozturk.rickandmortyapp.domain.models.CharacterDomainData
import com.selimozturk.rickandmortyapp.util.setVisible

class CharactersAdapter(
    var onItemClicked: ((CharacterDomainData) -> Unit) = {},
) : PagingDataAdapter<CharacterDomainData, RecyclerView.ViewHolder>(DiffUtilCallBack()) {

    var listType = TYPE_LIST
    var favoriteCharacterItems = emptyList<CharacterDomainData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_LIST -> CharactersRowViewHolder(
                CharacterItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            TYPE_GRID -> CharactersGridViewHolder(
                CharacterItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            when (holder) {
                is CharactersRowViewHolder -> holder.bind(it)
                is CharactersGridViewHolder -> holder.bind(it)
                else -> throw IllegalArgumentException("Invalid ViewHolder type")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return listType
    }

    inner class CharactersRowViewHolder(private val binding: CharacterItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(characterDomainData: CharacterDomainData) {
            binding.characterItemRow.setOnClickListener {
                onItemClicked.invoke(characterDomainData)
            }
            val isCharacterFavorite = isCharacterFavorite(characterDomainData.id)
            binding.favoriteButton.setVisible(isCharacterFavorite)
            characterDomainData.apply { isFavorite = isCharacterFavorite }
            binding.characterItem = characterDomainData
        }
    }

    inner class CharactersGridViewHolder(private val binding: CharacterItemGridBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(characterDomainData: CharacterDomainData) {
            binding.characterItemGrid.setOnClickListener {
                onItemClicked.invoke(characterDomainData)
            }
            val isCharacterFavorite = isCharacterFavorite(characterDomainData.id)
            binding.favoriteButton.setVisible(isCharacterFavorite)
            characterDomainData.apply { isFavorite = isCharacterFavorite }
            binding.characterItem = characterDomainData
        }
    }

    private fun isCharacterFavorite(characterId: Int): Boolean {
        return favoriteCharacterItems.any { it.id == characterId }
    }

    companion object {
        private const val TYPE_LIST = 1
        private const val TYPE_GRID = 2
    }

}
