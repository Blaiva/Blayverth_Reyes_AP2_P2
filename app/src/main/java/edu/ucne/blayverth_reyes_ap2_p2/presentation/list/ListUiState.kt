package edu.ucne.blayverth_reyes_ap2_p2.presentation.list

import edu.ucne.blayverth_reyes_ap2_p2.domain.model.Model

data class ListUiState(
    val isLoading: Boolean = false,
    val gastos: List<Model> = emptyList(),
    val error: String? = null
)
