package com.example.meucartaodevisitas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meucartaodevisitas.data.model.ProjectRepository
import com.example.meucartaodevisitas.data.model.Project
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/*data class ProjectsUiState(
    val isLoading: Boolean = false,
    val projects: List<Project> = emptyList()
)

class ProjectsViewModel(
    private val repository: ProjectRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProjectsUiState(isLoading = true))
    val uiState: StateFlow<ProjectsUiState> = _uiState

    init {
        // Observa DB e atualiza o estado
        viewModelScope.launch {
            repository.getProjectsFlow().collect { list ->
                _uiState.value = _uiState.value.copy(projects = list, isLoading = false)
            }
        }
        // Sincroniza com a rede ao iniciar
        refreshFromNetwork()
    }

    fun refreshFromNetwork() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                repository.refreshFromNetwork()
            } catch (e: Exception) {
                // opcional: log ou Snackbar
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
}*/
class ProjectsViewModel(
    private val repository: ProjectRepository
) : ViewModel() {

    val projects = repository.getProjectsFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            repository.refreshFromNetwork()
        }
    }
}
