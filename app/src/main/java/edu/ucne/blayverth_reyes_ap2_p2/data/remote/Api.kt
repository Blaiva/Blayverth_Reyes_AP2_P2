package edu.ucne.blayverth_reyes_ap2_p2.data.remote

import edu.ucne.blayverth_reyes_ap2_p2.data.remote.dto.Dto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface Api {
    @GET("Gastos")
    suspend fun getGastos(): Response<List<Dto>>

    @GET("Gastos/{id}")
    suspend fun getGastoDetail(
        @Path("id") id: Int
    ): Response<Dto>

    @POST("Gastos")
    suspend fun saveGasto(
        @Body gasto: Dto
    ): Response<Dto>

    @PUT("Gastos/{id}")
    suspend fun updateGasto(
        @Path("id") id: Int,
        @Body gasto: Dto
    ): Response<Unit>
}