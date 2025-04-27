package com.example.nbaplayers.views.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nbaplayers.model.Player

/**
 * Component for display card of player
 *
 * @param player data about player
 * @param onClick onClick call from View
 */
@Composable
fun PlayerCard(player: Player, onClick: () -> Unit) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onClick() },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column (modifier = Modifier.fillMaxWidth()){
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Name:")
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Position:")
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Club:")
                    }
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("${player.firstName} ${player.lastName}")
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(player.position)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(player.team.name)
                    }
                }
            }
        }
    }
}