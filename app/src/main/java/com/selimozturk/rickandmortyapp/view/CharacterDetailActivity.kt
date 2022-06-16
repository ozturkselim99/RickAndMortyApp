package com.selimozturk.rickandmortyapp.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.selimozturk.rickandmortyapp.databinding.ActivityCharacterDetailBinding
import com.selimozturk.rickandmortyapp.domain.models.CharacterDetailDomainData
import com.selimozturk.rickandmortyapp.domain.models.CharacterDomainData
import com.selimozturk.rickandmortyapp.util.setVisible
import com.selimozturk.rickandmortyapp.util.showToast
import com.selimozturk.rickandmortyapp.viewmodels.CharacterDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailActivity : AppCompatActivity() {

    private val viewModel: CharacterDetailViewModel by viewModels()
    private lateinit var characterDetailDomainData: CharacterDetailDomainData
    private val binding: ActivityCharacterDetailBinding by lazy {
        ActivityCharacterDetailBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        intent.getStringExtra("characterId")?.let {
            viewModel.getCharacter(it.toInt())
        }
        intent.getStringExtra("isCharacterFavorite")?.let {
            if (it.toBoolean()) {
                binding.addFavoriteButton.visibility = View.GONE
                binding.deleteFavoriteButton.visibility = View.VISIBLE
            } else {
                binding.addFavoriteButton.visibility = View.VISIBLE
                binding.deleteFavoriteButton.visibility = View.GONE
            }
        }
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
        binding.addFavoriteButton.setOnClickListener {
            addFavoriteCharacter(characterDetailDomainData)
        }
        binding.deleteFavoriteButton.setOnClickListener {
            deleteFavoriteCharacter(characterDetailDomainData.id)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.character.observe(this) {
            binding.characterItem = it
            characterDetailDomainData = it
        }
        viewModel.loading.observe(this){
            binding.characterDetailLoadingProgressBar.setVisible(it)
        }
    }

    private fun addFavoriteCharacter(characterDetailDomainData: CharacterDetailDomainData) {
        viewModel.insertFavoriteCharacter(
            CharacterDomainData(
                characterDetailDomainData.id,
                characterDetailDomainData.image,
                characterDetailDomainData.name,
                characterDetailDomainData.status,
                characterDetailDomainData.species
            )
        )
        showToast("Character added to favorite")
    }

    private fun deleteFavoriteCharacter(characterId: Int) {
        viewModel.deleteFavoriteCharacter(characterId)
        showToast("Character deleted from favorite")
    }
}