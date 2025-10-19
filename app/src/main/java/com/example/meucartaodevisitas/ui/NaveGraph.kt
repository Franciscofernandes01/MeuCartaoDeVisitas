package com.example.meucartaodevisitas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.meucartaodevisitas.ui.screens.ProjectListScreen
import com.example.meucartaodevisitas.ui.screens.ProjectDetailScreen
import com.example.meucartaodevisitas.ui.screens.ProfileScreen

object Routes {
    const val PROFILE = "profile"
    const val PROJECT_LIST = "project_list"
    const val PROJECT_DETAIL = "project_detail"
}

@Composable
fun NavGraph(startDestination: String = Routes.PROFILE, navController: NavHostController) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        composable(Routes.PROFILE) {
            ProfileScreen(
                onOpenProjects = {
                    navController.navigate(Routes.PROJECT_LIST)
                }
            )
        }

        composable(Routes.PROJECT_LIST) {
            ProjectListScreen(onProjectClick = { projectId ->
                navController.navigate("${Routes.PROJECT_DETAIL}/$projectId")
            })
        }

        composable(
            route = "${Routes.PROJECT_DETAIL}/{projectId}",
            arguments = listOf(navArgument("projectId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("projectId") ?: -1
            ProjectDetailScreen(projectId = id)
        }
    }
}