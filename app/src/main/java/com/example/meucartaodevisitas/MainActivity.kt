package com.example.meucartaodevisitas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.meucartaodevisitas.data.local.AppDatabase
import com.example.meucartaodevisitas.data.model.ProjectRepository
import com.example.meucartaodevisitas.ui.AppNavGraph
import com.example.meucartaodevisitas.ui.theme.MeuCartaoDeVisitasTheme
import com.example.meucartaodevisitas.viewmodel.ProjectsViewModel
import com.example.meucartaodevisitas.viewmodel.ProjectsViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // --- Inicialização do banco, repository e viewmodel ---
        val dao = AppDatabase.getInstance(this).projectDao()
        val repository = ProjectRepository(dao, "Franciscofernandes01")
        val factory = ProjectsViewModelFactory(repository)
        val viewModel: ProjectsViewModel by viewModels { factory }

        // --- Conteúdo da UI ---
        setContent {
            MeuCartaoDeVisitasTheme {
                val navController = rememberNavController()

                AppNavGraph(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}
