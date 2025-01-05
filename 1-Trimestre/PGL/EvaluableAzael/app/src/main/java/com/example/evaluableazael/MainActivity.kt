package com.example.evaluableazael
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.evaluableazael.ui.theme.EvaluableAzaelTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
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
            EvaluableAzaelTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Mymenu()
                }
            }
        }
    }
}

val baseDeDatos = FirebaseFirestore.getInstance()
val pokemon = baseDeDatos.collection("Pokemons")
var listaPokemons = mutableStateListOf<Pokemons>()
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mymenu() {
    val navController = rememberNavController()
    val estadoDrawer = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {

                Text(
                    text = "CRUD",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = Center
                )
                NavigationDrawerItem(
                    label = { Text("Inicio") },
                    selected = true,
                    onClick = {
                        navController.navigate("inicio")
                        coroutineScope.launch { estadoDrawer.close() }
                    },
                    icon = { Icon(Icons.Filled.Home, null) }
                )
                NavigationDrawerItem(
                    label = { Text("Insertar") },
                    selected = false,
                    onClick = {
                        navController.navigate("insertar")
                        coroutineScope.launch { estadoDrawer.close() }
                    },
                    icon = { Icon(Icons.Filled.Add, null) }
                )
                NavigationDrawerItem(
                    label = { Text("Buscar") },
                    selected = false,
                    onClick = {
                        navController.navigate("buscar")
                        coroutineScope.launch { estadoDrawer.close() }
                    },
                    icon = { Icon(Icons.Filled.Search, null) }
                )
                NavigationDrawerItem(
                    label = { Text("Actualizar") },
                    selected = false,
                    onClick = {
                        navController.navigate("actualizar")
                        coroutineScope.launch { estadoDrawer.close() }
                    },
                    icon = { Icon(Icons.Filled.Edit, null) }
                )
                NavigationDrawerItem(
                    label = { Text("Eliminar") },
                    selected = false,
                    onClick = {
                        navController.navigate("eliminar")
                        coroutineScope.launch { estadoDrawer.close() }
                    },
                    icon = { Icon(Icons.Filled.Delete, null) }
                )
                NavigationDrawerItem(
                    label = { Text("Mapa") },
                    selected = false,
                    onClick = {
                        navController.navigate("mapa")
                        coroutineScope.launch { estadoDrawer.close() }
                    },
                    icon = { Icon(Icons.Filled.LocationOn, null) }
                )
                NavigationDrawerItem(
                    label = { Text("Mostrar todos") },
                    selected = false,
                    onClick = {
                        rellenaLista()
                        navController.navigate("mostrarTodos")
                        coroutineScope.launch { estadoDrawer.close() }
                    },
                    icon = { Icon(Icons.Filled.Menu, null) }
                )
                NavigationDrawerItem(
                    label = { Text("Corrutina") },
                    selected = false,
                    onClick = {
                        navController.navigate("corrutina")
                        coroutineScope.launch { estadoDrawer.close() }
                    },
                    icon = { Icon(Icons.Filled.Build, null) }
                )
            }
        },
        drawerState = estadoDrawer
    )
    {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("CRUD en Firebase")
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                coroutineScope.launch { estadoDrawer.open() }
                            }
                        ) {
                            Icon(Icons.Filled.Menu, contentDescription = null)
                        }
                    }
                )
            }
        )
        {
            NavHost(navController = navController, startDestination = "inicio") {
                composable("inicio") {
                    PantallaInicio()
                }
                composable("insertar") {
                    PantallaInsertar()
                }
                composable("buscar") {
                    PantallaBuscar()
                }
                composable("actualizar") {
                    PantallaUpdate()
                }
                composable("eliminar") {
                    PantallaEliminar()
                }
                composable("mostrarTodos") {
                    PantallaMostrarTodos()
                }
                composable("mapa"){
                    PantallaMapa()
                }
                composable("corrutina"){
                    PantallaCorrutina()
                }
            }
        }
    }
}
@Composable
fun PantallaInicio(){

}
@Composable
fun PantallaBuscar(){
    val contexto = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Buscar", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        var numeroPokedex by remember { mutableStateOf("") }
        var nombre by remember { mutableStateOf("") }
        var tipo by remember { mutableStateOf("") }
        var vida by remember { mutableStateOf("0") }
        fun vaciarCampos() {
            numeroPokedex = ""
        }
        OutlinedTextField(
            value = numeroPokedex,
            onValueChange = { numeroPokedex = it },
            label = { Text(text = "NumeroPokedex") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )
        Text(text = "Nombre del Pokemon: $nombre")
        Text(text = "Nombre del Tipo: $tipo")
        Text(text = "Numero de vida: $vida")
        Button(
            onClick = {
                pokemon.document(numeroPokedex.toString()).get().addOnSuccessListener {
                    if (it.exists()) {
                        numeroPokedex = it.get("numeroPokedex").toString()
                        nombre = it.get("nombre").toString()
                        tipo = it.get("tipo").toString()
                        vida = it.get("vida").toString()
                        Toast.makeText(contexto, "Registro encontrado", Toast.LENGTH_SHORT).show()
                        vaciarCampos()
                    }
                    else {
                        Toast.makeText(contexto, "Registro NO encontrado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        ) {
            Text("Buscar por NumeroPokedex")
        }
    }
}



@Composable
fun PantallaUpdate(){
    val contexto = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Actualizar", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        var numeroPokedex by remember { mutableStateOf("") }
        var nombre by remember { mutableStateOf("") }
        var tipo by remember { mutableStateOf("") }
        var vida by remember { mutableStateOf("0") }
        fun vaciarCampos() {
            numeroPokedex = ""
            nombre = ""
            tipo = ""
            vida = "0"
        }
        OutlinedTextField(
            value = numeroPokedex,
            onValueChange = { numeroPokedex = it },
            label = { Text(text = "NumeroPokedex") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text(text = "Nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )
        OutlinedTextField(
            value = tipo,
            onValueChange = { tipo = it },
            label = { Text(text = "Tipo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )
        OutlinedTextField(
            value = vida,
            onValueChange = { vida = it },
            label = { Text(text = "Vida") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )
        Button(
            onClick = {
                pokemon.document(numeroPokedex.toString()).set(hashMapOf(
                    "numeroPokedex" to numeroPokedex.toString(),
                    "nombre" to nombre.toString(),
                    "tipo" to tipo.toString(),
                    "vida" to vida.toInt()
                )
                )
                vaciarCampos()
                Toast.makeText(contexto, "Registro actualizado", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text("Actualizar")
        }
    }
}


@Composable
fun PantallaEliminar(){
    val contexto = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Eliminar", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        var numeroPokedex by remember { mutableStateOf("") }
        fun vaciarCampos() {
            numeroPokedex = ""
        }
        OutlinedTextField(
            value = numeroPokedex,
            onValueChange = { numeroPokedex = it },
            label = { Text(text = "NumeroPokedex") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )
        Button(
            onClick = {
                pokemon.document(numeroPokedex.toString()).get().addOnSuccessListener {
                    if (it.exists()) {
                        it.reference.delete()
                        Toast.makeText(contexto, "Registro eliminado", Toast.LENGTH_SHORT).show()
                        vaciarCampos()
                    }
                    else {
                        Toast.makeText(contexto, "Registro NO eliminado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        ) {
            Text("Eliminar por Numero de Pokedex")
        }
    }
}
@Composable
fun PantallaInsertar(){
    val contexto = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Insertar", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        var numeroPokedex by remember { mutableStateOf("") }
        var nombre by remember { mutableStateOf("") }
        var tipo by remember { mutableStateOf("") }
        var vida by remember { mutableStateOf("0") }
        fun vaciarCampos() {
            numeroPokedex = ""
            nombre = ""
            tipo = ""
            vida = "0"
        }
        OutlinedTextField(
            value = numeroPokedex,
            onValueChange = { numeroPokedex = it },
            label = { Text(text = "numeroPokedex") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text(text = "nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )
        OutlinedTextField(
            value = tipo,
            onValueChange = { tipo = it },
            label = { Text(text = "tipo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )
        OutlinedTextField(
            value = vida,
            onValueChange = { vida = it },
            label = { Text(text = "vida") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )
        Button(
            onClick = {
                val poke= Pokemons(numeroPokedex,nombre,tipo,vida)
                pokemon.document(numeroPokedex).set(poke)
                Toast.makeText(contexto, "Registro insertado", Toast.LENGTH_SHORT).show()
                vaciarCampos()
            }
        ) {
            Text("Insertar")
        }
    }
}

private fun rellenaLista(){
    listaPokemons.clear()
    pokemon.get().addOnSuccessListener {
        for (docum in it){
            val pok = Pokemons(
                docum.data.get("numeroPokedex").toString(),
                docum.data.get("nombre").toString(),
                docum.data.get("tipo").toString(),
                docum.data.get("vida").toString()
            )
            listaPokemons.add(pok)
        }
    }
}
@Composable
fun PantallaMostrarTodos(){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(listaPokemons) { po ->
            MostrarPokemon(po)
        }
    }
}
@Composable
fun MostrarPokemon(po: Pokemons) {
    HorizontalDivider(modifier = Modifier.fillMaxWidth().width(16.dp))
    Text(text = "NumeroPokedex: ${po.numeroPokedex}")
    Text(text = "Nombre: ${po.nombre}")
    Text(text = "Tipo: ${po.tipo}")
    Text(text = "Vida: ${po.vida}")
    HorizontalDivider(modifier = Modifier.fillMaxWidth().width(16.dp))
}
@Composable
fun PantallaMapa() {
    val auditorio = LatLng(27.84489, -15.44385)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(auditorio, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = auditorio),
            title = "Teatro Victor Jara",
            snippet ="Marcador en el Teatro Victor Jara"
        )
    }
}
@Composable
fun PantallaCorrutina(){
    var texto by remember { mutableStateOf("Inicio") }
    var corrutineScope = rememberCoroutineScope()
    var numero by remember { mutableStateOf("") }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text="Corrutinas", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = numero, onValueChange= { numero= it}, label ={ Text("Introduce un número")} )

        Text(texto)
        Button(
            onClick = {
                corrutineScope.launch(Dispatchers.Default) {
                    var numeroInt: Int? = numero.toIntOrNull()
                    if (numeroInt != null) {
                        for (i in numeroInt downTo   1){
                            simularTarea()
                            texto="$i"
                        }
                    }
                }
            }
        ) { Text("Comenzar") }
    }

}
fun simularTarea(){
    try{
        Thread.sleep(1000)
    } catch (e:InterruptedException){}
}
data class Pokemons(val numeroPokedex: String, val nombre: String, val tipo: String, val vida: String){
    override fun toString(): String {
        return "Pokemons(numeroPokedex='$numeroPokedex', nombre='$nombre', tipo='$tipo', vida='$vida')"
    }
}




