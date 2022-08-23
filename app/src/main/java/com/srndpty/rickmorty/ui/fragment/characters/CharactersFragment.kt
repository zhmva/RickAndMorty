package com.srndpty.rickmorty.ui.fragment.characters

import android.text.Editable
import android.text.TextWatcher
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.srndpty.rickmorty.R
import com.srndpty.rickmorty.base.BaseFragment
import com.srndpty.rickmorty.databinding.FragmentCharactersBinding
import com.srndpty.rickmorty.domain.entity.Character
import com.srndpty.rickmorty.ui.fragment.characters.adapter.CharactersAdapter
import com.srndpty.rickmorty.utils.Arguments
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding>(
    FragmentCharactersBinding::inflate
), CharactersAdapter.Result {
    private val viewModel: CharactersViewModel by viewModels()

    private val adapter: CharactersAdapter by lazy { CharactersAdapter(this) }

    override fun setupUI() {
    }

    override fun setupObservers() {
        super.setupObservers()
        observeSearch()
        observeState()
    }

    private fun observeSearch() {
        requireBinding().editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val value = requireBinding().editSearch.text.toString()
                searchBy(value)
            }

        })
    }

    private fun searchBy(value: String) {
        viewModel.setSearchBy(value)
    }

    private fun observeState() {
        adapter.loadStateFlow.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleState(it) }.launchIn(lifecycleScope)
    }

    private fun handleState(state: CombinedLoadStates) {
        requireBinding().charProgress.isVisible = state.refresh is LoadState.Loading
    }

    override fun onClickListener(id: Int) {
        navigateDetail(id)
    }

    private fun navigateDetail(id: Int) {
        val action =
            CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailsFragment(
            )
        findNavController().navigate(action)
    }
}