package com.selimozturk.rickandmortyapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.selimozturk.rickandmortyapp.databinding.CharacterItemGridBinding
import com.selimozturk.rickandmortyapp.databinding.CharacterItemRowBinding
import com.selimozturk.rickandmortyapp.domain.models.CharacterDomainData
import com.selimozturk.rickandmortyapp.util.setVisible

class CharactersAdapter(
    var onItemClicked: ((CharacterDomainData) -> Unit) = {},
) :
    PagingDataAdapter<CharacterDomainData, RecyclerView.ViewHolder>(DiffUtilCallBack()) {
    var listType = TYPE_LIST
    var favoriteCharacterItems: List<CharacterDomainData> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (listType) {
            TYPE_LIST -> {
                val binding = CharacterItemRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                CharactersRowViewHolder(binding)
            }
            TYPE_GRID -> {
                val binding = CharacterItemGridBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                CharactersGridViewHolder(binding)
            }
            else -> {
                throw RuntimeException("The type has to be GRID or LIST")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (listType) {
            TYPE_LIST -> initLayoutRow(holder as CharactersRowViewHolder, position)
            TYPE_GRID -> initLayoutGrid(holder as CharactersGridViewHolder, position)
            else -> {
                throw RuntimeException("The type has to be GRID or LIST")
            }
        }
    }

    inner class CharactersRowViewHolder(private val binding: CharacterItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(characterDomainData: CharacterDomainData) {
            binding.characterItemRow.setOnClickListener {
                onItemClicked.invoke(characterDomainData)
            }
            if (isCharacterFavorite(characterDomainData.id)) {
                binding.favoriteButton.setVisible(true)
                characterDomainData.apply {
                    isFavorite = true
                }
            } else {
                binding.favoriteButton.setVisible(false)
            }
            binding.characterItem = characterDomainData
        }
    }

    inner class CharactersGridViewHolder(private val binding: CharacterItemGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(characterDomainData: CharacterDomainData) {
            binding.characterItemGrid.setOnClickListener {
                onItemClicked.invoke(characterDomainData)
            }
            if (isCharacterFavorite(characterDomainData.id)) {
                binding.favoriteButton.setVisible(true)
                characterDomainData.apply {
                    isFavorite = true
                }
            } else {
                binding.favoriteButton.setVisible(false)
            }
            binding.characterItem = characterDomainData
        }
    }

    private fun initLayoutRow(holder: CharactersRowViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    private fun initLayoutGrid(holder: CharactersGridViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<CharacterDomainData>() {
        override fun areItemsTheSame(
            oldItem: CharacterDomainData,
            newItem: CharacterDomainData
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: CharacterDomainData,
            newItem: CharacterDomainData
        ): Boolean {
            return oldItem == newItem
        }
    }

    private fun isCharacterFavorite(characterId: Int): Boolean {
        var result = false
        favoriteCharacterItems.forEach {
            if (it.id == characterId) {
                result = true
            }
        }
        return result
    }

    companion object {
        private const val TYPE_LIST = 1
        private const val TYPE_GRID = 2
    }
}