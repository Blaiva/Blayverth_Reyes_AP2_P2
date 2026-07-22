package edu.ucne.blayverth_reyes_ap2_p2.data.remote.dto

import com.squareup.moshi.JsonClass
import edu.ucne.blayverth_reyes_ap2_p2.domain.model.Model

@JsonClass(generateAdapter = true)
data class Dto(
    val id: Int,
    val fecha: String,
    val suplidor: String,
    val ncf: String,
    val itbis: Double,
    val monto: Double
){
    fun toDomain() = Model(
        id = id,
        fecha = fecha,
        suplidor = suplidor,
        ncf = ncf,
        itbis = itbis,
        monto = monto
    )
}