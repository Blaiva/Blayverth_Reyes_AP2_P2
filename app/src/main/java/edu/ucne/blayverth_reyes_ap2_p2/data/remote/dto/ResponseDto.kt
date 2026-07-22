package edu.ucne.blayverth_reyes_ap2_p2.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseDto(
    val items: List<Dto>
)