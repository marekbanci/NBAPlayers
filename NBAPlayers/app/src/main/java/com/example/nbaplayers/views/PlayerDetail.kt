package com.example.nbaplayers.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nbaplayers.network.UiState
import com.example.nbaplayers.viewmodels.PlayerDetailVM

/**
 * View of player detail
 *
 * @param navController controller for navigate between views
 * @param id of player
 * @param viewModel manage data for UI, get from [hiltViewModel]
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerDetail(
    navController: NavController,
    id: Int,
    viewModel: PlayerDetailVM = hiltViewModel()
) {
    val detailState by viewModel.playerDetail.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val state = detailState) {
                is UiState.Loading -> {
                    CircularProgressIndicator()
                }

                is UiState.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        item {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "${state.data.firstName} ${state.data.lastName}",
                                        fontSize = 24.sp
                                    )
                                }
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp)) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text("Position: ${state.data.position}")
                                    }
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text("Height: ${state.data.height}")
                                    }
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text("Weight: ${state.data.weight}")
                                    }
                                }

                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp)) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text("Jersey: ${state.data.jerseyNumber}")
                                    }
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text("College: ${state.data.college}")
                                    }
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text("Country: ${state.data.country}")
                                    }
                                }

                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 24.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "DRAFT",
                                        fontSize = 20.sp
                                    )
                                }

                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp)) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text("Year: ${state.data.draftYear}")
                                    }
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text("Round: ${state.data.draftRound}")
                                    }
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text("Number: ${state.data.draftNumber}")
                                    }
                                }

                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Club: ${state.data.team.clubName}",
                                        fontSize = 18.sp,
                                        modifier = Modifier
                                            .padding(top = 16.dp)
                                            .clickable {
                                                navController.navigate("team/detail/${state.data.team.id}")
                                            }
                                    )
                                }
                            }
                        }
                    }
                }

                is UiState.Error -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Error: ${state.message}", color = Color.Red)
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.getPlayerDetail(id) }) {
                            Text("Retry")
                        }
                    }
                }

                null -> {
                    Text("Initializing...")
                }
            }
        }
    }
}