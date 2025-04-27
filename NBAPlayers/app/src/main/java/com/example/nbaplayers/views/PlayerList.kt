package com.example.nbaplayers.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nbaplayers.viewmodels.PlayersListVM
import com.example.nbaplayers.views.components.PlayerCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerList(navController: NavController, viewModel: PlayersListVM = hiltViewModel()) {
    val players by viewModel.players.observeAsState(emptyList())
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        if (players.isEmpty()) {
            viewModel.fetchData()
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleItemIndex ->
                if (lastVisibleItemIndex != null && lastVisibleItemIndex >= players.size - 1) {
                    viewModel.fetchData()
                }
            }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Players") }) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            state = listState,
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(players) { item ->
                PlayerCard(item, onClick = {
                    navController.navigate("detail/${item.id}")
                })
            }
        }
    }
}