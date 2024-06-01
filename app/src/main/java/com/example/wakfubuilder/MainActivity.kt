package com.example.wakfubuilder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
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
import com.example.wakfubuilder.data.Datasource
import com.example.wakfubuilder.model.BottomNavItem
import com.example.wakfubuilder.model.Builds
import com.example.wakfubuilder.model.Clases
import com.example.wakfubuilder.model.Favoritos
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

/*
Función para iniciar el NavHost y el bottom bar
 */
@Composable
fun WakfuBuilder() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            /*
            Si la pantalla actual no es Login, crea la barra de navegación inferior
             */
            if (currentDestination?.route != "Login") {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { paddingValues ->
        /*
        Inicialización de las rutas para la navegación entre pantallas de la app
         */
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

/*
Función para la pantalla de inicio
 */
@Composable
fun WakfuLogin(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image( //Muestra el logo de la aplicación
            painter = painterResource(id = R.drawable.wakfubuilderlogo),
            contentDescription = "Wakfu Builder Logo",
            contentScale = Crop,
            modifier = Modifier
                .height(350.dp)
                .width(300.dp)
                .padding(bottom = 20.dp)
                .padding(horizontal = 20.dp)
        )
        Text( //Muestra un text de bienvenida
            text = "¡Bienvenido!",
            modifier = Modifier
                .padding(bottom = 20.dp),
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )
        Button( //Botón con navController que permite ir a la pantalla de inicio al pulsarlo
            modifier = Modifier
                .size(width = 130.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF026673)
            ),

            onClick = {
                navController.navigate("Inicio")
            }) {
            Text( //texto para nombrar al botón de acceso
                text = stringResource(R.string.buttonAcceder),
                modifier = Modifier,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

/*
Función para la pantalla inicio
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WakfuInicio(navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(

                title = {

                    Text( //Text para mostrar el título de la pantalla inicio
                        text = stringResource(R.string.app_name),
                        modifier = Modifier,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )

                },
                navigationIcon = { //icono para navegar hacía ajustes
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
    ) {
        LazyColumn(contentPadding = it) {

        }
    }
    //llama a la función BuildList, la cual contiene los datos a mostrar en inicio
    BuildsList(
        buildList = Datasource().loadBuilds()
    )
    Column(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    ) {
        Button( //botón para ir a la pantalla de creación
            modifier = Modifier
                .size(width = 130.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF026673)
            ),

            onClick = { //navigate con la ruta a la pantalla de creación
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

/*
Función para añadir los datos de la lista builds a una lazycolumn y a la card
 */
@Composable
fun BuildsList(buildList: List<Builds>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.padding(top = 65.dp)) {
        items(buildList) { builds ->
            BuildsCard(
                builds = builds,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

/*
función para mostrar la card con contenido de la lista Builds
 */
@Composable
fun BuildsCard(builds: Builds, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .height(150.dp)
            .fillMaxWidth()
    ) {
        Row {
            Text(
                text = builds.nombreBuild,
                modifier = modifier
                    .padding(start = 8.dp),
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )

        }

        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(builds.imageResourceId),
                contentDescription = "",
                modifier = modifier
                    .height(60.dp)
                    .width(60.dp),
                contentScale = Crop
            )

            Image(
                painter = painterResource(builds.casco1),
                contentDescription = "",
                modifier = modifier
                    .height(45.dp)
                    .width(45.dp)
            )
            Image(
                painter = painterResource(builds.coraza1),
                contentDescription = "",
                modifier = modifier
                    .height(45.dp)
                    .width(45.dp)
            )
            Image(
                painter = painterResource(builds.cinturon1),
                contentDescription = "",
                modifier = modifier
                    .height(45.dp)
                    .width(45.dp)
            )
            Image(
                painter = painterResource(builds.amuleto1),
                contentDescription = "",
                modifier = modifier
                    .height(45.dp)
                    .width(45.dp)
            )
            Image(
                painter = painterResource(builds.bota1),
                contentDescription = "",
                modifier = modifier
                    .height(45.dp)
                    .width(45.dp)
            )
        }
    }
}

/*
Función para la pantalla de favoritos
 */
@Composable
fun WakfuFavoritos(navController: NavHostController) {
    Scaffold(
        topBar = {
            WakfuTopBar(navController = navController, stringResource(id = R.string.favoritos))
        }
    ) {
        LazyColumn(contentPadding = it) {

        }
    }
}

/*
Función para la pantalla de creación
 */
@Composable
fun WakfuCreacion(navController: NavHostController) {

    var showMenu by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            WakfuTopBar(navController = navController, stringResource(id = R.string.nuevaBuild))
        }
    ) {
        LazyColumn(contentPadding = it) {

        }
        DropdownMenu(expanded = showMenu, onDismissRequest = {
            showMenu = false
        }) {
            DropdownMenuItem(
                text = { /*TODO*/ }, onClick = {

                })
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

                }) {
                Text(
                    text = stringResource(R.string.guardarBuild),
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}

/*
función para la pantalla de Ajustes
 */
@Composable
fun WakfuAjustes(navController: NavHostController) {
    Scaffold(
        topBar = {
            WakfuTopBar(navController = navController, stringResource(id = R.string.ajustes))
        }
    ) {
        LazyColumn(contentPadding = it) {

        }
    }
}

/*
función para la pantalla de busqueda
 */
@Composable
fun WakfuBusqueda(navController: NavHostController) {
    var query by remember {
        mutableStateOf("")
    }
    val resultado = remember {
        mutableStateOf(listOf<Any>())
    }
    TextField(
        value = query,
        onValueChange = {
            query = it

        },
        label = { Text("Buscar por") },
        modifier = Modifier.fillMaxWidth()
    )
    Scaffold(
        topBar = {
            WakfuTopBar(navController = navController, stringResource(id = R.string.buscar))
        }
    ) {

        LazyColumn(contentPadding = it) {
            items(resultado.value) {}
        }
    }
}

/*
Función para definir la barra de navegación inferior
 */
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    //Lista para almacenar los iconos navegables en una lista con model
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

/*
Función para definir la barra de navegación superior
 */
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

/*
Función para el preview de la clase MainActivity a través de la función LoginPreview(
 */
@Preview
@Composable
fun LoginPreview() {
    WakfuBuilderTheme {
        WakfuBuilder()
    }
}