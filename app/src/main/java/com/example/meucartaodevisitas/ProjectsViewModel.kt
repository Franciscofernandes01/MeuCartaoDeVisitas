package com.example.meucartaodevisitas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meucartaodevisitas.data.model.Project
import com.example.meucartaodevisitas.data.model.ProjectRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow


class ProjectsViewModel(
    private val repository: ProjectRepository
) : ViewModel() {

    // Flow de projetos vindo do banco local (Room)
    val projects: StateFlow<List<Project>> =
        repository.getProjectsFlow()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )
    fun getProject(id: Long): Flow<Project?> {
        return repository.getProjectById(id)
    }


    // Forçar atualização vindo da API do GitHub
    fun refresh() {
        viewModelScope.launch {
            repository.refreshFromNetwork()
        }
    }
}
