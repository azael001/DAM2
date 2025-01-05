package com.example.ut2a4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ut2a4.ui.theme.UT2A4Theme
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UT2A4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   MyAppScaffold()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppScaffold(){
    val navController= rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text="MyApp")},
                colors = TopAppBarDefaults.topAppBarColors(Color.Blue)
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected=true,
                    onClick = {navController.navigate("inicio")},
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Inicio")},
                    label = { Text(text = "Home")}
                )
                NavigationBarItem(
                    selected=false,
                    onClick = {navController.navigate("mapa")},
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Mapas")},
                    label = { Text(text = "Mapa")}
                )
                NavigationBarItem(
                    selected=false,
                    onClick = {navController.navigate("corrutinas")},
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Corrutinas")},
                    label = { Text(text = "Corrutinas")}
                )
            }
        }
    ) {
        NavHost(navController=navController, startDestination = "inicio"){
            composable("inicio") {
                Pantallainicio()
            }
            composable("mapa") {
                Pantallamapa()
            }
            composable("corrutinas") {
                Pantallacorrutinas()
            }
        }
    }
}
@Composable
fun Pantallainicio(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = "App con Scaffold (TopBar y BotonBar)", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = "ver. 0.1 " , fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }

}
fun simularTarea(){
    try{
        Thread.sleep(1000)
    } catch (e: InterruptedException){}
}

@Composable
fun Pantallamapa(){
    val auditorio = LatLng(27.903166, -15.4491194)
    val cameraPositionState = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(auditorio,10f)
    }
GoogleMap(
    modifier = Modifier.fillMaxSize(),
    cameraPositionState= cameraPositionState
){
    Marker(
        state = MarkerState(position = auditorio),
        title = "Teatro Auditorio de Agüimes",
        snippet = "Marcador en el Teatro Auditorio Aguimes"
    )
}

}
@Composable
fun Pantallacorrutinas(){
    var texto by remember { mutableStateOf("Inicio") }
    var corrutineScope = rememberCoroutineScope()
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text="Corrutinas", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text(texto)
        Button(
            onClick = {
                corrutineScope.launch(Dispatchers.Default) {
                    for (i in 1 .. 10){
                        simularTarea()
                        texto="$i"
                    }
                }
            }
        ) { Text("Comenzar") }
    }
}


