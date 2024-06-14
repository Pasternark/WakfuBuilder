package com.example.wakfubuilder

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.Crop
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

var buildsMain = Datasource().loadBuilds()


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
Función para la pantalla de login
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
            text = stringResource(R.string.bienvenido),
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
    val contextForToast = LocalContext.current.applicationContext
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(

                title = {

                    Text( //Text para mostrar el título de la pantalla inicio
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.displayMedium
                        //modifier = Modifier,
                        //fontFamily = FontFamily.Monospace,
                        //fontWeight = FontWeight.Bold,

                        //fontSize = 25.sp
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
    BuildsList()
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
                Toast.makeText(
                    contextForToast,
                    "Todos los caminos llevan a Roma, menos este.",
                    Toast.LENGTH_SHORT
                ).show()

                // navController.navigate("Crear")
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
fun BuildsList(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.padding(top = 65.dp)) {
        items(buildsMain) { builds ->
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
fun BuildsCard(modifier: Modifier = Modifier, builds: Builds) {
    val contextForToast = LocalContext.current.applicationContext
    remember { mutableStateOf(buildsMain) }
    Card(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .height(300.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {//inicio del body de la card
        Row {
            Image(
                painter = painterResource(builds.imageResourceId),
                contentDescription = "",
                modifier = modifier
                    .height(80.dp)
                    .width(80.dp),
                contentScale = Crop
            )
            Column(
                Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = builds.nombreBuild,
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier

                        .padding(top = 8.dp),
                )
                Text(
                    text = builds.nombreClase,
                    modifier = Modifier

                        .padding(top = 8.dp),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }

        }


        Row(
            modifier = Modifier
                .height(145.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Image(
                    painter = painterResource(builds.casco1),
                    contentDescription = "",
                    modifier = modifier
                        .height(55.dp)
                        .width(55.dp)
                )
                Image(
                    painter = painterResource(builds.coraza1),
                    contentDescription = "",
                    modifier = modifier
                        .height(55.dp)
                        .width(55.dp)
                )
            }
            Column {
                Image(
                    painter = painterResource(builds.cinturon1),
                    contentDescription = "",
                    modifier = modifier
                        .height(55.dp)
                        .width(55.dp)
                )
                Image(
                    painter = painterResource(builds.amuleto1),
                    contentDescription = "",
                    modifier = modifier
                        .height(55.dp)
                        .width(55.dp)
                )
            }
            Column {
                Image(
                    painter = painterResource(builds.bota1),
                    contentDescription = "",
                    modifier = modifier
                        .height(55.dp)
                        .width(55.dp)
                )

            }

        }
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(modifier = modifier, onClick = {

                //Toast.makeText(contextForToast, "¡Eliminado con exito!", Toast.LENGTH_SHORT).show()
            }) {
                Icon(// sin uso
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete item"
                )
            }
            IconButton(modifier = modifier
                .padding(horizontal = 5.dp), onClick = {
                //si el elemento favorito de la card es favorito y se pulsa el icono, cambia de true a false
                if (builds.favorito) {
                    builds.favorito = false

                    Toast.makeText( //toast que muestra cuando se elimina un nuevo elemento a favoritos
                        contextForToast,
                        "¡Eliminado de favoritos!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else { //si el elemento no era favorito, se marca como tal
                    builds.favorito = true
                    //toast que muestra cuando se añade un elemento a favoritos
                    Toast.makeText(contextForToast, "¡Añadido a favoritos!", Toast.LENGTH_SHORT)
                        .show()
                }
            }) {
                //si el elemento está marcado como favorito (true), muestra el icono de favoritos relleno
                if (builds.favorito) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorito"
                    )
                } else {//si el elemento no está marcado como favorito, muestra un icono sin relleno
                    Icon(

                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "No Favorito"
                    )
                }

            }//fin de iconButton
        }//fin del segundo row
    } //fin de la card

}

/*
Función para la pantalla de favoritos
 */
@Composable
fun WakfuFavoritos(navController: NavHostController, modifier: Modifier = Modifier) {
    //variable que almacena una nueva lista en base a la lista de builds, aplicando un filtro
    //si la variable favorito = true, entonces la añade
    val buildsFavoritas = buildsMain.filter { it.favorito }

    Scaffold(
        topBar = {
            WakfuTopBar(navController = navController, stringResource(id = R.string.favoritos))
        }
    ) {
        LazyColumn(contentPadding = it) {

        }

    }
    //se muestra una lista de cards con los elementos favoritos
    LazyColumn(modifier = modifier.padding(top = 65.dp)) {
        items(buildsFavoritas) { builds ->
            BuildsCard(
                builds = builds,
                modifier = Modifier.padding(8.dp)
            )
        }
    }

}


/*
Función para la pantalla de creación, triste pero no se alcanzó a implementar...
 */
@Composable
fun WakfuCreacion(navController: NavHostController) {
    remember {
        mutableStateOf(buildsMain)
    }


    LocalContext.current.applicationContext
    Scaffold(
        topBar = {
            WakfuTopBar(navController = navController, stringResource(id = R.string.nuevaBuild))
        }
    ) {
        LazyColumn(contentPadding = it) {
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
función para la pantalla de Ajustes, tampoco funciona...
 */
@Composable
fun WakfuAjustes(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    LocalContext.current.applicationContext
    Scaffold(
        topBar = {
            WakfuTopBar(navController = navController, stringResource(id = R.string.ajustes))
        }
    ) {
        LazyColumn(contentPadding = it) {

        }
    }
    Row(
        modifier = Modifier
            .padding(top = 65.dp)
            .padding(horizontal = 30.dp),
    ) {
        Text(
            text = "Idioma de la app",
            fontFamily = FontFamily.Monospace,
            fontSize = 25.sp,
            modifier = Modifier
                .padding(15.dp)
        )
        IconButton(
            onClick = {
                expanded = true
            }
        ) {
            Icon(
                imageVector = Icons.Default.Place,
                contentDescription = "Open Menu"
            )
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                //DropdownMenuItem(text = { "Español" }, onClick = {expanded = false})
                //DropdownMenuItem(text = { "Inglés" }, onClick = {expanded = false})
            }
        }

    }
}

/*
función para la pantalla de busqueda
 */
@Composable
fun WakfuBusqueda(navController: NavHostController) {
    var valueInput by remember {
        mutableStateOf("")
    }
    // val results = remember { mutableStateOf(listOf<Builds>()) }
    val resultados = remember { mutableStateOf(buildsMain) } //crea una lista mutable de buildsMain
    Scaffold(
        topBar = {
            WakfuTopBar(navController = navController, stringResource(id = R.string.buscar))
        }
    ) {

        LazyColumn(contentPadding = it) {

        }
    }
    Column(
        modifier = Modifier
            .padding(top = 85.dp)
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier
                .width(365.dp),
            value = valueInput, onValueChange = {
                valueInput = it
                //aquí debía implementarse la busqueda desde la lista mutable creada arriba
                //no alcancé a implementarlo así que la lee directo de la Datasource
                //así que al buscar un elemento, se siguen mostrando favoritos así no los haya
                //aunque no repercute en el resto de las listas mutables ni funciones
                resultados.value = Datasource().search(valueInput)
            },
            enabled = true,
            label = {
                Text(
                    text = stringResource(R.string.busqueda_por_nombre)
                )
            },
            keyboardActions = KeyboardActions(onDone = { }),
            singleLine = true
        )


    }
    val modifier = Modifier
    LazyColumn(modifier = modifier.padding(top = 155.dp)) {
        items(resultados.value) { builds ->
            BuildsCard(
                builds = builds,
                modifier = Modifier.padding(8.dp)
            )
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
        BottomNavItem("Inicio", Icons.Default.Home, stringResource(R.string.inicio_icon)),
        BottomNavItem("Favoritos", Icons.Default.Favorite, stringResource(R.string.favoritos_icon)),
        BottomNavItem("Buscar", Icons.Default.Search, stringResource(R.string.buscar_icon))
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
                style = MaterialTheme.typography.displayMedium
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