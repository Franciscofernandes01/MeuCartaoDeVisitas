package com.example.meucartaodevisitas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.meucartaodevisitas.data.model.ProjectRepository
import com.example.meucartaodevisitas.ui.screens.ProjectDetailScreen
import com.example.meucartaodevisitas.ui.screens.ProjectListScreen
import com.example.meucartaodevisitas.viewmodel.ProjectsViewModel

object Routes {
    const val PROJECT_LIST = "project_list"
    const val PROJECT_DETAIL = "project_detail"
}

@Composable
fun NavGraph(
    repository: ProjectRepository,
    viewModel: ProjectsViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.PROJECT_LIST
    ) {

        // LISTA DE PROJETOS
        composable(Routes.PROJECT_LIST) {
            ProjectListScreen(
                viewModel = viewModel,
                onProjectClick = { projectId ->
                    navController.navigate("${Routes.PROJECT_DETAIL}/$projectId")
                }
            )
        }

        // DETALHE DO PROJETO
        composable(
            route = "${Routes.PROJECT_DETAIL}/{projectId}",
            arguments = listOf(
                navArgument("projectId") { type = NavType.LongType }
            )
        ) { backStackEntry ->

            val id = backStackEntry.arguments?.getLong("projectId") ?: 0L

            ProjectDetailScreen(
                projectId = id,
                viewModel = viewModel
            )
        }
    }
}
