package com.example.meucartaodevisitas.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.meucartaodevisitas.model.ProjectsRepository
import com.example.meucartaodevisitas.ui.components.ProjectCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectListScreen(
    onProjectClick: (Int) -> Unit
) {
    val projects = ProjectsRepository.projects

    Scaffold(topBar = { TopAppBar(title = { Text("Meus Projetos") }) }) { padding ->
        if (projects.isEmpty()) {
            Text("Nenhum projeto encontrado.", modifier = Modifier
                .fillMaxSize()
                .padding(padding))
        } else {
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(projects) { project ->
                    ProjectCard(project = project, onClick = onProjectClick)
                }
            }
        }
    }
}