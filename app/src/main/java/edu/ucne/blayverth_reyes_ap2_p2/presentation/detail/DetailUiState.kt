package edu.ucne.blayverth_reyes_ap2_p2.presentation.detail

import edu.ucne.blayverth_reyes_ap2_p2.domain.model.Model

data class DetailUiState(
    val isLoading: Boolean = false,
    val gasto: Model? = null,
    val error: String? = null
)
