package com.example.meucartaodevisitas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.meucartaodevisitas.model.ProjectsRepository

@Composable
fun ProjectDetailScreen(projectId: Int) {
    val project = ProjectsRepository.findById(projectId)
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (project == null) {
            Text("Projeto não encontrado (id = $projectId)")
        } else {
            Text(text = "ID: ${project.id}")
            Text(text = "Nome: ${project.name}")
            Text(text = "Descrição: ${project.description}")
        }
    }
}