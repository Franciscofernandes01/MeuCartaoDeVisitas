package com.example.meucartaodevisitas.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.meucartaodevisitas.viewmodel.ProjectsViewModel
import com.example.meucartaodevisitas.ui.screens.*

@Composable
fun AppNavGraph(navController: NavHostController, viewModel: ProjectsViewModel) {

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(
                onNavigateToProjects = {
                    navController.navigate("projects")
                }
            )
        }

        composable("projects") {
            ProjectListScreen(
                viewModel = viewModel,
                onProjectClick = { id ->
                    navController.navigate("detail/$id")
                }
            )
        }

        composable("detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")!!.toLong()
            ProjectDetailScreen(
                projectId = id,
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
