package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.presentation.news_list.NewsListScreen
import com.example.newsapp.presentation.news_list.NewsListViewModel
import com.example.newsapp.ui.theme.NewsAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAPPTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "newsList") {
                    composable("newsList") {
                        val viewModel: NewsListViewModel = viewModel()
                        NewsListScreen(viewModel = viewModel) { newsId ->
                            navController.navigate("newsDetail/$newsId")
                        }
                    }
                    composable("newsDetail/{newsId}") { backStackEntry ->
                        val newsId = backStackEntry.arguments?.getString("newsId")
                        // Implementar tela de detalhes da notícia
                    }
                }
            }
        }
    }
}

//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Surface
//import androidx.compose.ui.Modifier
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            NewsAppTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    val navController = rememberNavController()
//                    NavHost(navController = navController, startDestination = "newsList") {
//                        composable("newsList") {
//                            val viewModel: NewsListViewModel = viewModel()
//                            NewsListScreen(viewModel = viewModel) { newsId ->
//                                navController.navigate("newsDetail/$newsId")
//                            }
//                        }
//                        composable("newsDetail/{newsId}") { backStackEntry ->
//                            val newsId = backStackEntry.arguments?.getString("newsId")
//                            // Implementar tela de detalhes da notícia
//                        }
//                    }
//                }
//            }
//        }
//    }
//}