package edu.ucne.blayverth_reyes_ap2_p2.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.blayverth_reyes_ap2_p2.data.remote.Resource
import edu.ucne.blayverth_reyes_ap2_p2.domain.usecase.GetGastoDetailUseCase
import edu.ucne.blayverth_reyes_ap2_p2.presentation.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getGastoDetailUseCase: GetGastoDetailUseCase,
    savedState: SavedStateHandle
): ViewModel() {
    private val _state = MutableStateFlow(DetailUiState())
    val state = _state.asStateFlow()

    init {
        val args = savedState.toRoute<Screen.Detail>()
        loadGasto(args.id)
    }

    private fun loadGasto(id: Int) {
        viewModelScope.launch {
            getGastoDetailUseCase(id).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        _state.update { it.copy(isLoading = false, gasto = result.data) }
                    }
                    is Resource.Error -> {
                        _state.update { it.copy(isLoading = false, error = result.message) }
                    }
                }
            }
        }
    }
}