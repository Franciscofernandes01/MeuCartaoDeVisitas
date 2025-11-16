package com.example.meucartaodevisitas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.example.meucartaodevisitas.viewmodel.ProjectsViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectDetailScreen(
    projectId: Long,
    viewModel: ProjectsViewModel,
    onBack: () -> Unit
) {
    val project by viewModel.getProjectById(projectId).collectAsState(initial = null)
    val uriHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(padding)
        ) {
            when {
                project == null -> {
                    Text("Carregando...")
                    Spacer(modifier = Modifier.height(8.dp))
                    CircularProgressIndicator()
                }

                else -> {
                    Text(text = project!!.name, style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = project!!.description ?: "Sem descrição")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { uriHandler.openUri(project!!.htmlUrl) }) {
                        Text("Abrir no GitHub")
                    }
                }
            }
        }
    }
}
