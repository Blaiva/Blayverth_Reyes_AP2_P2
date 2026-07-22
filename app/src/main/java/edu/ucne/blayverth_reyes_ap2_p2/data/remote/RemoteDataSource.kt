package edu.ucne.blayverth_reyes_ap2_p2.data.remote

import edu.ucne.blayverth_reyes_ap2_p2.data.remote.dto.Dto
import edu.ucne.blayverth_reyes_ap2_p2.data.remote.dto.ResponseDto
import retrofit2.HttpException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: Api
) {
    suspend fun getGastos(): Result<ResponseDto>{
        return try {
            val response = api.getGastos()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error de red ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun getGastoDetail(id: Int): Result<Dto>{
        return try {
            val response = api.getGastoDetail(id)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error de red ${response.code()}"))
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }
}