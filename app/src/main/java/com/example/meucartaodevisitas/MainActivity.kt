package com.example.meucartaodevisitas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.meucartaodevisitas.data.local.AppDatabase
import com.example.meucartaodevisitas.data.model.ProjectRepository
import com.example.meucartaodevisitas.navigation.NavGraph
import com.example.meucartaodevisitas.ui.theme.MeuCartaoDeVisitasTheme
import com.example.meucartaodevisitas.viewmodel.ProjectsViewModel
import com.example.meucartaodevisitas.viewmodel.ProjectsViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Criar DB + Repository
        val db = AppDatabase.getDatabase(this)
        val dao = db.projectDao()
        val repository = ProjectRepository(dao, "Franciscofernandes01")

        // Criar ViewModel
        val factory = ProjectsViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory)[ProjectsViewModel::class.java]

        // Compose UI
        setContent {
            MeuCartaoDeVisitasTheme {
                NavGraph(
                    repository = repository,
                    viewModel = viewModel
                )
            }
        }
    }
}
