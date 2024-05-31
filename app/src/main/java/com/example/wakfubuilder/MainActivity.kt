package com.example.wakfubuilder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.compose.rememberNavController
import com.example.wakfubuilder.ui.theme.WakfuBuilderTheme

enum class WakfuScreens(@StringRes val title: Int){
    MainActivity(title = R.string.main),
    Inicio(title = R.string.app_name),
    Favoritos(title = R.string.favoritos),
    Ajustes(title = R.string.ajustes),
    Busqueda(title = R.string.buscar),
    Creacion(title = R.string.nuevaBuild)

}

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
                    LoginWakfu()
                }
            }
        }
    }
}


@Composable
fun LoginWakfu (
    navController: NavHostController = rememberNavController()
){

    Scaffold(
        topBar = {
            WakfuLoginTopBar()
        }
    ) {
        LazyColumn(contentPadding = it) {

        }
        NavHost(
            navController = navController,
            startDestination = WakfuScreens.MainActivity.name,
            modifier = Modifier.padding()
        ){
            composable(route = WakfuScreens.MainActivity.name){
                navController.navigate(WakfuScreens.MainActivity.name)
            }
            composable(route = WakfuScreens.Inicio.name){

            }
        }
    }
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        Image(painter = painterResource(id = R.drawable.wakfubuilderlogo),
            contentDescription = "Wakfu Builder Logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(350.dp)
                .width(300.dp)
                .padding(bottom = 20.dp)
                .padding(horizontal = 20.dp)
        )
        Text(text = "¡Bienvenido!",
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
                containerColor = Color(0xFF026673)),

            onClick = {
                navController.navigate(WakfuScreens.Inicio.name)
            }) {
            Text(text = stringResource(R.string.buttonAcceder),
                modifier = Modifier,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WakfuLoginTopBar(){
    CenterAlignedTopAppBar(

        title = {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

            }
        }
    )
}

@Composable
fun AccederInicio(){

}
@Preview
@Composable
fun LoginPreview(){
    WakfuBuilderTheme {
        LoginWakfu()
    }
}