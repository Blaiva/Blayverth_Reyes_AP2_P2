package edu.ucne.blayverth_reyes_ap2_p2.presentation.detail

sealed interface DetailUiEvent {
    data class OnSuplidorChanged(val suplidor: String) : DetailUiEvent
    data class OnNcfChanged(val ncf: String) : DetailUiEvent
    data class OnItbisChanged(val itbis: String) : DetailUiEvent
    data class OnMontoChanged(val monto: String) : DetailUiEvent
    data class OnFechaChanged(val fecha: String) : DetailUiEvent
    data object OnSave : DetailUiEvent
    data object ClearSavedState : DetailUiEvent
}