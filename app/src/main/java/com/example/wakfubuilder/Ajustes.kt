package com.example.wakfubuilder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wakfubuilder.ui.theme.WakfuBuilderTheme

class Ajustes : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WakfuBuilderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WakfuAjustes()
                }
            }
        }
    }
}

@Composable
fun WakfuAjustes(){
    Scaffold(
        topBar = {
            WakfuAjustesTopBar()
        },

        bottomBar = {
            WakfuAjustesBottomBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {

        }
    }

    WakfuAjustesContent()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WakfuAjustesTopBar(modifier: Modifier = Modifier) {
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
                    text = stringResource(R.string.ajustes),
                )
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WakfuAjustesBottomBar(modifier: Modifier = Modifier) {
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
fun WakfuAjustesContent(){
    Column(
        modifier = Modifier
            .padding(top = 60.dp, bottom = 60.dp)
            .padding(horizontal = 8.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

    }
}

@Preview
@Composable
fun WakfuAjustesPreview(){
    WakfuBuilderTheme {
        WakfuAjustes()
    }
}