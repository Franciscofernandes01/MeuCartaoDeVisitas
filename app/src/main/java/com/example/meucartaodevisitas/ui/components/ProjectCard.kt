package com.example.meucartaodevisitas.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.meucartaodevisitas.data.model.Project

@Composable
fun ProjectCard(
    project: Project,
    onClick: (Long) -> Unit
) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = { onClick(project.id) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = project.name,
                style = MaterialTheme.typography.titleMedium
            )

            if (!project.description.isNullOrEmpty()) {
                Text(
                    text = project.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Text(
                text = project.htmlUrl,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
