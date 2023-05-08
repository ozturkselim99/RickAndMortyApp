package com.selimozturk.rickandmortyapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.selimozturk.rickandmortyapp.R
import com.selimozturk.rickandmortyapp.adapters.CharactersAdapter
import com.selimozturk.rickandmortyapp.databinding.ActivityMainBinding
import com.selimozturk.rickandmortyapp.domain.models.CharacterDomainData
import com.selimozturk.rickandmortyapp.util.showToast
import com.selimozturk.rickandmortyapp.viewmodels.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    private val viewModel: CharactersViewModel by viewModels()
    private var listTypeControl: Boolean = true
    private val charactersAdapter = CharactersAdapter()
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setupCharactersRecyclerView()
        initViews()
        submitDataToCharactersAdapter("", "")
        filterCharacters()
    }

    private fun initViews() {
        binding.listTypeIcon.setOnClickListener {
            listTypeControl = !listTypeControl
            setupCharactersRecyclerView()
            setListTypeIcon(binding.listTypeIcon)
        }
        charactersAdapter.addLoadStateListener { loadStates ->
            binding.charactersLoadingProgressBar.visibility =
                if (loadStates.refresh is LoadState.Loading) View.VISIBLE else View.GONE
            binding.retryButton.visibility =
                (if (loadStates.refresh is LoadState.Error) View.VISIBLE else View.GONE)
        }
        charactersAdapter.onItemClicked = ::onItemClicked
        binding.retryButton.setOnClickListener(::onRetryButtonClicked)
    }

    private fun onItemClicked(character: CharacterDomainData) {
        val intent = Intent(this, CharacterDetailActivity::class.java)
        intent.putExtra("characterId", character.id.toString())
        intent.putExtra("isCharacterFavorite", character.isFavorite.toString())
        startActivity(intent)
    }

    private fun onRetryButtonClicked(view: View) {
        charactersAdapter.retry()
    }

    private fun filterCharacters() {
        var selectedStatus = ""
        var searchCharacterName = ""
        binding.statusChipGroup.setOnCheckedStateChangeListener { chipGroup, checkedIds ->
            if (checkedIds.size == 1) {
                val chip: Chip = chipGroup.findViewById(checkedIds[0])
                selectedStatus = chip.text.toString()
                showToast("${chip.text} Selected")
            } else {
                selectedStatus = ""
            }
            submitDataToCharactersAdapter(selectedStatus, searchCharacterName)
        }
        binding.etSearch.addTextChangedListener { editable ->
            editable?.let {
                searchCharacterName = editable.toString().ifEmpty {
                    ""
                }
            }
            submitDataToCharactersAdapter(selectedStatus, searchCharacterName)
        }
    }

    private fun submitDataToCharactersAdapter(selectedStatus: String, searchCharacterName: String) {
        lifecycleScope.launch {
            viewModel.getAllCharacters(selectedStatus, searchCharacterName)
                .observe(this@MainActivity) {
                    charactersAdapter.submitData(lifecycle, it)
                    charactersAdapter.favoriteCharacterItems = viewModel.getAllFavoriteCharacter()
                }
        }
    }

    private fun setupCharactersRecyclerView() {
        binding.charactersRW.layoutManager = if (listTypeControl) {
            GridLayoutManager(this, 2)
        } else {
            LinearLayoutManager(this)
        }
        binding.charactersRW.adapter = charactersAdapter
        charactersAdapter.listType = if (listTypeControl) 2 else 1
    }

    private fun setListTypeIcon(imageView: ImageView) {
        val icon = if (!listTypeControl) R.drawable.ic_grid else R.drawable.ic_line
        imageView.setImageResource(icon)
    }

}