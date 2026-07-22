package edu.ucne.blayverth_reyes_ap2_p2.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Screen: NavKey {
    @Serializable
    data object List: Screen()

    @Serializable
    data class Detail(val id: Int): Screen()
}