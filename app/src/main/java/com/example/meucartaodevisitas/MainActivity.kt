package com.example.meucartaodevisitas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.meucartaodevisitas.model.ProjectsRepository
import com.example.meucartaodevisitas.ui.components.ProjectCard
import com.example.meucartaodevisitas.ui.theme.MeuCartaoDeVisitasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeuCartaoDeVisitasTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}

// ====================== NAVIGATION ======================
@Composable
fun NavGraph(navController: androidx.navigation.NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    color = Color(0xFFF5F5F5)
                ) {
                    CartaoDeVisitas(
                        navToProjects = { navController.navigate("projectList") }
                    )
                }
            }
        }

        composable("projectList") {
            ProjectListScreen(onProjectClick = { projectId ->
                navController.navigate("projectDetail/$projectId")
            })
        }

        composable(
            "projectDetail/{projectId}",
            arguments = listOf(navArgument("projectId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("projectId") ?: 0
            ProjectDetailScreen(projectId = id)
        }
    }
}

// ====================== HOME SCREEN ======================
@Composable
fun CartaoDeVisitas(navToProjects: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        // CABEÇALHO
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1A237E))
                .padding(vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = "Foto de perfil",
                modifier = Modifier.size(120.dp).clip(CircleShape)
            )

            Spacer(Modifier.height(16.dp))

            Text(
                "Francisco Fernandes",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                "Tecnólogo em Sistemas para Internet",
                fontSize = 16.sp,
                color = Color(0xFFBBDEFB)
            )
        }

        Spacer(Modifier.weight(1f))

        // CONTATOS
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, bottom = 32.dp)
        ) {
            LinhaContato(
                icon = ImageVector.vectorResource(R.drawable.phone),
                texto = "(xx) xxxxx-xxxx"
            )
            Spacer(Modifier.height(12.dp))
            LinhaContato(
                icon = ImageVector.vectorResource(R.drawable.mail),
                texto = "francisco20240029341@alu.uern.br"
            )

            Spacer(Modifier.height(24.dp))

            // BOTÃO PARA VER PROJETOS
            Button(
                onClick = navToProjects,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver Projetos")
            }
        }
    }
}

@Composable
fun LinhaContato(icon: ImageVector, texto: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = Color(0xFF0288D1)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            texto,
            fontSize = 14.sp,
            color = Color(0xFF424242)
        )
    }
}

// ====================== LISTA DE PROJETOS ======================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectListScreen(onProjectClick: (Int) -> Unit) {
    val projects = ProjectsRepository.projects

    Scaffold(topBar = { TopAppBar(title = { Text("Meus Projetos") }) }) { padding ->
        if (projects.isEmpty()) {
            Text(
                "Nenhum projeto encontrado.",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            )
        } else {
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(projects) { project ->
                    ProjectCard(project = project, onClick = onProjectClick)
                }
            }
        }
    }
}

// ====================== DETALHES DO PROJETO ======================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectDetailScreen(projectId: Int) {
    Scaffold(topBar = { TopAppBar(title = { Text("Detalhes do Projeto") }) }) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Detalhes do Projeto: $projectId", fontSize = 20.sp)
        }
    }
}
