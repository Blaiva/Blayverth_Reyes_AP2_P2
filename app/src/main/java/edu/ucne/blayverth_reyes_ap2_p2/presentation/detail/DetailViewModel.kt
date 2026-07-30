package edu.ucne.blayverth_reyes_ap2_p2.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.blayverth_reyes_ap2_p2.data.remote.Resource
import edu.ucne.blayverth_reyes_ap2_p2.domain.model.Model
import edu.ucne.blayverth_reyes_ap2_p2.domain.usecase.GetGastoDetailUseCase
import edu.ucne.blayverth_reyes_ap2_p2.domain.usecase.SaveGastoUseCase
import edu.ucne.blayverth_reyes_ap2_p2.presentation.detail.DetailUiEvent
import edu.ucne.blayverth_reyes_ap2_p2.presentation.detail.DetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getGastoDetailUseCase: GetGastoDetailUseCase,
    private val saveGastoUseCase: SaveGastoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DetailUiState())
    val state = _state.asStateFlow()

    fun loadGasto(id: Int) {
        if (id <= 0) {
            _state.update { DetailUiState(gastoId = 0) }
            return
        }

        _state.update { DetailUiState() }

        viewModelScope.launch {
            getGastoDetailUseCase(id).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        result.data?.let { gasto ->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    gastoId = gasto.gastoId,
                                    suplidor = gasto.suplidor,
                                    ncf = gasto.ncf,
                                    itbis = gasto.itbis.toString(),
                                    monto = gasto.monto.toString(),
                                    fecha = gasto.fecha
                                )
                            }
                        }
                    }
                    is Resource.Error -> {
                        _state.update { it.copy(isLoading = false, error = result.message) }
                    }
                }
            }
        }
    }

    fun onEvent(event: DetailUiEvent) {
        when (event) {
            is DetailUiEvent.OnSuplidorChanged -> {
                _state.update { it.copy(suplidor = event.suplidor, suplidorError = null) }
            }
            is DetailUiEvent.OnNcfChanged -> {
                _state.update { it.copy(ncf = event.ncf, ncfError = null) }
            }
            is DetailUiEvent.OnItbisChanged -> {
                _state.update { it.copy(itbis = event.itbis, itbisError = null) }
            }
            is DetailUiEvent.OnMontoChanged -> {
                _state.update { it.copy(monto = event.monto, montoError = null) }
            }
            is DetailUiEvent.OnFechaChanged -> {
                _state.update { it.copy(fecha = event.fecha, fechaError = null) }
            }
            is DetailUiEvent.ClearSavedState -> {
                _state.update { it.copy(isSaved = false) }
            }
            is DetailUiEvent.OnSave -> {
                save()
            }
        }
    }

    private fun validate(): Boolean {
        var isValid = true

        if (_state.value.suplidor.isBlank()) {
            _state.update { it.copy(suplidorError = "El suplidor es obligatorio") }
            isValid = false
        }

        if (_state.value.ncf.isBlank()) {
            _state.update { it.copy(ncfError = "El NCF es obligatorio") }
            isValid = false
        }

        val itbisVal = _state.value.itbis.toDoubleOrNull()
        if (itbisVal == null || itbisVal < 0) {
            _state.update { it.copy(itbisError = "Ingrese un ITBIS válido") }
            isValid = false
        }

        val montoVal = _state.value.monto.toDoubleOrNull()
        if (montoVal == null || montoVal <= 0) {
            _state.update { it.copy(montoError = "Ingrese un monto válido") }
            isValid = false
        }

        if (_state.value.fecha.isBlank()) {
            _state.update { it.copy(fechaError = "La fecha es obligatoria") }
            isValid = false
        }

        return isValid
    }

    private fun save() {
        if (!validate()) return

        val current = _state.value
        val gasto = Model(
            gastoId = current.gastoId,
            suplidor = current.suplidor,
            ncf = current.ncf,
            itbis = current.itbis.toDoubleOrNull() ?: 0.0,
            monto = current.monto.toDoubleOrNull() ?: 0.0,
            fecha = current.fecha
        )

        viewModelScope.launch {
            saveGastoUseCase(gasto).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true, error = null) }
                    }
                    is Resource.Success -> {
                        _state.update { it.copy(isLoading = false, isSaved = true) }
                    }
                    is Resource.Error -> {
                        _state.update { it.copy(isLoading = false, error = result.message) }
                    }
                }
            }
        }
    }
}