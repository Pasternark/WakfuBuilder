package com.example.wakfubuilder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wakfubuilder.ui.theme.WakfuBuilderTheme


class Inicio : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WakfuBuilderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShowContentPreview()
                }
            }
        }
    }
}

@Composable
fun WakfuApp(){
    Scaffold(
        topBar = {
            WakfuInicioTopBar()
        },

        bottomBar = {
            WakfuInicioBottomBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {

        }
    }

    WakfuInicioContent()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WakfuInicioTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(

        title = {
            Row(
                modifier = Modifier
                    .background(Color(0xFF026873))
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                )
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WakfuInicioBottomBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {

            Row(
                modifier = Modifier
                    .background(Color(0xFF026873))
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(R.drawable.casa),
                    contentDescription = "House Icon",
                    modifier = Modifier
                        .size(80.dp)
                )

                Icon(
                    painter = painterResource(R.drawable.estrella__1_),
                    contentDescription = "Favs icon",
                    modifier = Modifier
                        .size(60.dp)
                )

                Icon(
                    painter = painterResource(R.drawable.lupa),
                    contentDescription = "Search Icon",
                    modifier = Modifier
                        .size(60.dp)
                )
            }
        }
    )
}

@Composable
fun WakfuInicioContent(){
    Column(
        modifier = Modifier
            .padding(top = 60.dp, bottom = 60.dp)
            .padding(horizontal = 8.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(bottom = 10.dp)
            .size(width = 130.dp, height = 50.dp)
            .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF026673)),
            onClick = { /*TODO*/ }) {
            Text(text = stringResource(id = R.string.crearBuild))
        }
    }
}

@Preview
@Composable
fun ShowContentPreview() {
    WakfuBuilderTheme {
        WakfuApp()
    }
}