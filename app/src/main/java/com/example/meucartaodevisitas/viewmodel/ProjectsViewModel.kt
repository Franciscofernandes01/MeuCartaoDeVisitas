package com.example.meucartaodevisitas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meucartaodevisitas.data.model.ProjectRepository
import com.example.meucartaodevisitas.data.model.Project
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


data class ProjectsUiState(
    val isLoading: Boolean = false,
    val projects: List<Project> = emptyList()
)

class ProjectsViewModel(
    private val repository: ProjectRepository
) : ViewModel() {

    // Expor lista reativa via StateFlow
    private val _uiState = MutableStateFlow(ProjectsUiState(isLoading = true))
    val uiState: StateFlow<ProjectsUiState> = _uiState

    init {
        observeDb()
        refreshFromNetwork()
    }

    private fun observeDb() {
        viewModelScope.launch {
            repository.getProjectsFlow().collect { list ->
                _uiState.value = _uiState.value.copy(projects = list, isLoading = false)
            }
        }
    }

    fun refreshFromNetwork() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                repository.refreshFromNetwork()
            } catch (e: Exception) {
                // opcional: log
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    // exposer projeto por id como Flow (para tela de detalhe)
    fun getProjectById(id: Long) = repository.getProjectByIdFlow(id)
}
