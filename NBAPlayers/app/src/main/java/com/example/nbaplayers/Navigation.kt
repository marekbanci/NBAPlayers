package com.example.nbaplayers


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nbaplayers.views.PlayerDetail
import com.example.nbaplayers.views.PlayerList
import com.example.nbaplayers.views.TeamDetail

/**
 * Navigation graph
 * define all route of application
 * @param navController navigation controller, initialized [rememberNavController]
 */
@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { PlayerList(navController) }
        composable(
            route = "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            if (id != null) {
                PlayerDetail(navController, id)
            }
        }
        composable(
            route = "team/detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            if (id != null) {
                TeamDetail(navController, id)
            }
        }
    }
}