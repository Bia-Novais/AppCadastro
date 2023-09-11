package com.example.appcadastro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appcadastro.ui.theme.AppCadastroTheme
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppCadastroTheme {
                App()

            }
        }
    }
    @Preview(showBackground = true, widthDp =  350, heightDp = 800)
    @Composable
    fun App(){
        AppCadastroTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column {
                    SimpleTopAppBar()
                }
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    CamposTexto()
                    ElevatedButtonSample()
                    ButtonWithIconSample()
                }

            }

        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SimpleTopAppBar() {
        Scaffold (
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color.LightGray),

                    title = {
                        Text(
                            "Fazer Meu Cadastro",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                )
            },
            content = { innerPadding ->
                LazyColumn(
                    contentPadding = innerPadding,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {


                }
            }

        )
    }


    //Botão
    @Composable
    fun ElevatedButtonSample() {
        ElevatedButton(onClick = { /* Do something! */ }) { Text("Confirmar") }
    }

}

@Preview(showBackground = true, widthDp =  350, heightDp = 100)
@Composable
fun ButtonWithIconSample() {
    Button(onClick = { /* Do something! */ }) {
        Icon(
            Icons.Filled.Clear,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text("Cancelar")
    }
}



@Composable
@OptIn(ExperimentalMaterial3Api::class)
@Preview
fun CamposTexto() {

    val nome = remember { mutableStateOf("") }
    val telefone = remember { mutableStateOf("") }
    val bairro = remember { mutableStateOf("") }
    val cep = remember { mutableStateOf("") }
    val options = listOf("Telefone Fixo", "Celular", "Whatsapp")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Cadastro:",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = nome.value,
            onValueChange = { nome.value = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = telefone.value,
            onValueChange = { telefone.value = it },
            label = { Text("Telefone") },
            modifier = Modifier.fillMaxWidth()
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            TextField(
                // The `menuAnchor` modifier must be passed to the text field for correctness.
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                value = selectedOptionText,
                onValueChange = {},
                label = { Text("Selecione um opção") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }
        TextField(
            value = bairro.value,
            onValueChange = { bairro.value = it },
            label = { Text("Observação") },
            modifier = Modifier.fillMaxWidth()
        )


    }
}