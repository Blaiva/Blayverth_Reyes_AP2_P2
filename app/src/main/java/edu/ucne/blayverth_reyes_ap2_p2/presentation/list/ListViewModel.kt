package edu.ucne.blayverth_reyes_ap2_p2.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.blayverth_reyes_ap2_p2.data.remote.Resource
import edu.ucne.blayverth_reyes_ap2_p2.domain.repository.Repository
import edu.ucne.blayverth_reyes_ap2_p2.domain.usecase.GetGastosUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getGastosUseCase: GetGastosUseCase
): ViewModel() {
    private val _state = MutableStateFlow(ListUiState())
    val state = _state.asStateFlow()

    init {
        loadGastos()
    }

    fun loadGastos() {
        viewModelScope.launch {
            getGastosUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                gastos = result.data ?: emptyList()
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(isLoading = false, error = result.message)
                        }
                    }
                }
            }
        }
    }
}