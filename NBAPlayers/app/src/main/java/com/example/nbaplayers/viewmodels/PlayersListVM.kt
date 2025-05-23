package com.example.nbaplayers.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbaplayers.model.Meta
import com.example.nbaplayers.model.Player
import com.example.nbaplayers.network.ApiNBA
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel get list of player
 *
 * @param nbaApi interface for connection to server
 */
@HiltViewModel
class PlayersListVM @Inject constructor(
    private val nbaApi: ApiNBA
) : ViewModel() {
    private val _players = MutableLiveData<List<Player>>(emptyList())
    val players: LiveData<List<Player>> get() = _players

    private val _meta = MutableStateFlow<Meta?>(null)
    private val meta: StateFlow<Meta?> get() = _meta

    fun fetchData() {
        viewModelScope.launch {
            try{
                val response = if (meta.value != null) {
                    nbaApi.getPlayers(perPage = meta.value!!.perPage, cursor = meta.value!!.nextCursor)
                } else {
                    nbaApi.getPlayers()
                }

                _players.value = _players.value?.plus(response.data)
                _meta.value = response.meta
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}