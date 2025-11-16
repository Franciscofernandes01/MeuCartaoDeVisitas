package com.example.meucartaodevisitas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.meucartaodevisitas.viewmodel.ProjectsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectListScreen(
    viewModel: ProjectsViewModel,
    onProjectClick: (Long) -> Unit
) {
    val state = viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.value.isLoading && state.value.projects.isEmpty()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                items(state.value.projects) { project ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        onClick = { onProjectClick(project.id) }
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(text = project.name, style = MaterialTheme.typography.titleMedium)
                            Text(text = project.description ?: "(sem descrição)")
                        }
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = { viewModel.refreshFromNetwork() },
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
        ) {
            Text("↻")
        }
    }
}
