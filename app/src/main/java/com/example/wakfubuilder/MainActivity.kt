package com.example.wakfubuilder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.wakfubuilder.model.BottomNavItem
import com.example.wakfubuilder.ui.theme.WakfuBuilderTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WakfuBuilderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WakfuBuilder()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WakfuBuilder() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            if (currentDestination?.route != "Login") {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "Login",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("Login") { WakfuLogin(navController = navController) }
            composable("Inicio") { WakfuInicio(navController = navController) }
            composable("Favoritos") { WakfuFavoritos(navController = navController) }
            composable("Buscar") { WakfuBusqueda(navController = navController) }
            composable("Crear") { WakfuCreacion(navController = navController) }
            composable("Ajustes") { WakfuAjustes(navController = navController) }
        }

    }


}

@Composable
fun WakfuLogin(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.wakfubuilderlogo),
            contentDescription = "Wakfu Builder Logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(350.dp)
                .width(300.dp)
                .padding(bottom = 20.dp)
                .padding(horizontal = 20.dp)
        )
        Text(
            text = "¡Bienvenido!",
            modifier = Modifier
                .padding(bottom = 20.dp),
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )
        Button(
            modifier = Modifier
                .size(width = 130.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF026673)
            ),

            onClick = {
                navController.navigate("Inicio")
            }) {
            Text(
                text = stringResource(R.string.buttonAcceder),
                modifier = Modifier,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WakfuInicio(navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(

                title = {

                    Text(
                        text = stringResource(R.string.app_name),
                    )

                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate("Ajustes")
                        }
                    ) {
                        Icon(Icons.Filled.Menu, contentDescription = "Settings")
                    }


                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF026673),
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { it ->
        LazyColumn(contentPadding = it) {

        }
    }
    Column(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    ) {
        Button(
            modifier = Modifier
                .size(width = 130.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF026673)
            ),

            onClick = {
                navController.navigate("Crear")
            }) {
            Text(
                text = stringResource(R.string.crearBuild),
                modifier = Modifier,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WakfuFavoritos(navController: NavHostController) {
    Scaffold(
        topBar = {
            WakfuTopBar(navController = navController, stringResource(id = R.string.favoritos))
        }
    ) { it ->
        LazyColumn(contentPadding = it) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WakfuCreacion(navController: NavHostController) {
    Scaffold(
        topBar = {
            WakfuTopBar(navController = navController, stringResource(id = R.string.crearBuild))
        }
    ) { it ->
        LazyColumn(contentPadding = it) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WakfuAjustes(navController: NavHostController) {
    Scaffold(
        topBar = {
            WakfuTopBar(navController = navController, stringResource(id = R.string.ajustes))
        }
    ) { it ->
        LazyColumn(contentPadding = it) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WakfuBusqueda(navController: NavHostController) {

    Scaffold(
        topBar = {
            WakfuTopBar(navController = navController, stringResource(id = R.string.buscar))
        }
    ) { it ->
        LazyColumn(contentPadding = it) {

        }
    }
}


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val itemsBarNavigation = listOf(
        BottomNavItem("Inicio", Icons.Default.Home, "Inicio"),
        BottomNavItem("Favoritos", Icons.Default.Favorite, "Favoritos"),
        BottomNavItem("Buscar", Icons.Default.Search, "Buscar")
    )
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        itemsBarNavigation.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentDestination?.route == item.route,
                onClick = { navController.navigate(item.route) }

            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WakfuTopBar(navController: NavHostController, stringResource: String) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource,
                modifier = Modifier,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
        },
        navigationIcon = {

            IconButton(
                onClick = {
                    navController.navigateUp()
                }
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },

        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF026673),
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@Composable
fun AccederInicio() {

}

@Preview
@Composable
fun LoginPreview() {
    WakfuBuilderTheme {
        WakfuBuilder()
    }
}