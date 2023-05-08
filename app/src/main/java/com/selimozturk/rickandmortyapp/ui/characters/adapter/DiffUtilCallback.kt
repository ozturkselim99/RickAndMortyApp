package com.selimozturk.rickandmortyapp.ui.characters.adapter

import androidx.recyclerview.widget.DiffUtil
import com.selimozturk.rickandmortyapp.domain.models.CharacterDomainData

class DiffUtilCallBack : DiffUtil.ItemCallback<CharacterDomainData>() {

    override fun areItemsTheSame(
        oldItem: CharacterDomainData, newItem: CharacterDomainData
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: CharacterDomainData, newItem: CharacterDomainData
    ): Boolean {
        return oldItem == newItem
    }

}