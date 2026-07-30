package edu.ucne.blayverth_reyes_ap2_p2.presentation.detail

data class DetailUiState(
    val gastoId: Int = 0,
    val suplidor: String = "",
    val ncf: String = "",
    val itbis: String = "",
    val monto: String = "",
    val fecha: String = "",
    val suplidorError: String? = null,
    val ncfError: String? = null,
    val itbisError: String? = null,
    val montoError: String? = null,
    val fechaError: String? = null,
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
    val error: String? = null
)