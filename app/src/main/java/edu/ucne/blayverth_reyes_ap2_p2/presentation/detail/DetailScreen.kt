package edu.ucne.blayverth_reyes_ap2_p2.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.blayverth_reyes_ap2_p2.presentation.detail.DetailUiEvent
import edu.ucne.blayverth_reyes_ap2_p2.presentation.detail.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    id: Int = 0,
    viewModel: DetailViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        viewModel.loadGasto(id)
    }

    LaunchedEffect(state.isSaved) {
        if (state.isSaved) {
            viewModel.onEvent(DetailUiEvent.ClearSavedState)
            onBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (id == 0) "Registro de Gasto" else "Editar Gasto") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Atrás"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            }

            state.error?.let {
                Text(
                    text = "Error: $it",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            OutlinedTextField(
                value = state.suplidor,
                onValueChange = { viewModel.onEvent(DetailUiEvent.OnSuplidorChanged(it)) },
                label = { Text("Suplidor") },
                isError = state.suplidorError != null,
                supportingText = state.suplidorError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.ncf,
                onValueChange = { viewModel.onEvent(DetailUiEvent.OnNcfChanged(it)) },
                label = { Text("NCF") },
                isError = state.ncfError != null,
                supportingText = state.ncfError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.itbis,
                onValueChange = { viewModel.onEvent(DetailUiEvent.OnItbisChanged(it)) },
                label = { Text("ITBIS") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = state.itbisError != null,
                supportingText = state.itbisError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.monto,
                onValueChange = { viewModel.onEvent(DetailUiEvent.OnMontoChanged(it)) },
                label = { Text("Monto") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = state.montoError != null,
                supportingText = state.montoError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.fecha,
                onValueChange = { viewModel.onEvent(DetailUiEvent.OnFechaChanged(it)) },
                label = { Text("Fecha (YYYY-MM-DD)") },
                isError = state.fechaError != null,
                supportingText = state.fechaError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.onEvent(DetailUiEvent.OnSave) },
                modifier = Modifier.fillMaxWidth(),
                enabled = !state.isLoading
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Guardar")
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                Text("Guardar")
            }
        }
    }
}