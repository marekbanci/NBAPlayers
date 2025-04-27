package com.example.nbaplayers.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbaplayers.model.Team
import com.example.nbaplayers.network.ApiNBA
import com.example.nbaplayers.network.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel get team detail
 *
 * @param nbaApi interface for connection to server
 * @param savedStateHandle get data from navigation component
 */
@HiltViewModel
class TeamDetailVM @Inject constructor(
    private val nbaApi: ApiNBA,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _teamDetail = MutableLiveData<UiState<Team>>()
    val teamDetail: LiveData<UiState<Team>> get() = _teamDetail

    init {
        val id: Int = savedStateHandle["id"] ?: 0
        getPlayerDetail(id)
    }

    fun getPlayerDetail(id: Int) {
        _teamDetail.value = UiState.Loading
        viewModelScope.launch {
            try {
                val response = nbaApi.getTeamByID(id)
                _teamDetail.value = UiState.Success(response.data)
            } catch (e: Exception) {
                _teamDetail.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}