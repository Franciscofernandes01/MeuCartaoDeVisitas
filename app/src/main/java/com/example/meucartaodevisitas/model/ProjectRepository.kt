package com.example.meucartaodevisitas.model



import com.example.meucartaodevisitas.model.Project


object ProjectsRepository {
    val projects = listOf(
        Project(1, "Website pessoal", "Landing page e seção sobre mim"),
        Project(2, "App ToDo", "Aplicativo simples para gerenciar tarefas"),
        Project(3, "API REST", "Serviço backend em Ktor/Express"),
        Project(4, "Portfolio", "Coleção de projetos e estudos de caso"),
        Project(5, "Chat App", "Protótipo de chat com WebSockets")
    )

    fun findById(id: Int): Project? = projects.find { it.id == id }
}
