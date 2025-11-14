package com.example.meucartaodevisitas.data.model

import com.example.meucartaodevisitas.data.local.ProjectDao
import com.example.meucartaodevisitas.data.model.Project
import com.example.meucartaodevisitas.data.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ProjectRepository(
    private val dao: ProjectDao,
    private val apiUser: String // passe o username do GitHub aqui ao instanciar

) {
    private val api = RetrofitClient.create()

    fun getProjectById(id: Long): Flow<Project?> {
        return dao.getProjectById(id)
    }

    fun getProjectsFlow(): Flow<List<Project>> = dao.getAll()

    suspend fun refreshFromNetwork() {
        withContext(Dispatchers.IO) {
            val repoDtos = api.listUserRepos(apiUser)
            val projects = repoDtos.map { dto ->
                Project(
                    id = dto.id,
                    name = dto.name,
                    description = dto.description,
                    htmlUrl = dto.htmlUrl
                )
            }
            dao.insertAll(projects)
        }
    }
}

