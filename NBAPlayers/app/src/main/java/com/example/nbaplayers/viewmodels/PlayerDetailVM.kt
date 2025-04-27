package com.example.nbaplayers.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbaplayers.model.Player
import com.example.nbaplayers.network.ApiNBA
import com.example.nbaplayers.network.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerDetailVM @Inject constructor(
    private val nbaApi: ApiNBA,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _playerDetail = MutableLiveData<UiState<Player>>()
    val playerDetail: LiveData<UiState<Player>> get() = _playerDetail

    init {
        val id: Int = savedStateHandle["id"] ?: 0
        getPlayerDetail(id)
    }

    fun getPlayerDetail(id: Int) {
        _playerDetail.value = UiState.Loading
        viewModelScope.launch {
            try {
                val response = nbaApi.getPlayerById(id)
                _playerDetail.value = UiState.Success(response)
            } catch (e: Exception) {
                _playerDetail.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}