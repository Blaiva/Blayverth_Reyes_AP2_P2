package edu.ucne.blayverth_reyes_ap2_p2.data.remote

import edu.ucne.blayverth_reyes_ap2_p2.data.remote.dto.Dto
import edu.ucne.blayverth_reyes_ap2_p2.data.remote.dto.ResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("Gastos")
    suspend fun getGastos(
    ): Response<ResponseDto>

    @GET("Gastos/{id}")
    suspend fun getGastoDetail(
        @Path("id") id: Int
    ): Response<Dto>
}