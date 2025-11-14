package com.example.meucartaodevisitas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.meucartaodevisitas.viewmodel.ProjectsViewModel

@Composable
fun ProjectDetailScreen(
    projectId: Long,
    viewModel: ProjectsViewModel
) {
    val projects by viewModel.projects.collectAsState()

    // Tenta encontrar o projeto na lista
    val project = projects.firstOrNull { it.id == projectId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        when {
            projects.isEmpty() -> {
                Text("Carregando...", style = MaterialTheme.typography.titleMedium)
                CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
            }

            project == null -> {
                Text(
                    "Projeto não encontrado (id = $projectId)",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            else -> {
                Text("ID: ${project.id}", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))

                Text("Nome: ${project.name}", style = MaterialTheme.typography.bodyLarge)
                Spacer(Modifier.height(8.dp))

                Text(
                    "Descrição: ${project.description ?: "Sem descrição"}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
