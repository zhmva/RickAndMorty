package com.srndpty.rickmorty.ui.fragment.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.srndpty.rickmorty.base.BaseViewModel
import com.srndpty.rickmorty.domain.entity.Character
import com.srndpty.rickmorty.domain.use_case.ActionCharacterEntityUseCase
import com.srndpty.rickmorty.domain.use_case.GetSearchCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val actionCharacterEntityUseCase: ActionCharacterEntityUseCase,
    private val getSearchCharacterUseCase: GetSearchCharacterUseCase
) : BaseViewModel() {

    private val _charactersList = MutableStateFlow<List<Character>>(mutableListOf())
    val charactersList: StateFlow<List<Character>> get() = _charactersList

    val charFlow: Flow<PagingData<Character>>

    private val searchBy = MutableLiveData("")

    init {
        charFlow = searchBy.asFlow()
            .debounce(500)
            .flatMapLatest {
                getSearchCharacterUseCase.getSearchCharacter(it)
            }
            .cachedIn(viewModelScope)
    }


    fun getAction() {
        viewModelScope.launch(Dispatchers.IO) {
            actionCharacterEntityUseCase.get...()
                .onStart {
                    setLoading()
                }.catch {
                    hideLoading()
                }.collect {
                    when (it) {
                        is ErrorResult -> hideLoading()
                        PendingResult -> setLoading()
                        is SuccessResult -> {
                            hideLoading()
                            _animeRandom.value = it.data
                        }
                    }
                }
        }
    }

    fun setSearchBy(value: String) {
        if (searchBy.value == value) return
        searchBy.value = value
    }

}