package edu.ucne.blayverth_reyes_ap2_p2.data

import edu.ucne.blayverth_reyes_ap2_p2.data.remote.RemoteDataSource
import edu.ucne.blayverth_reyes_ap2_p2.data.remote.Resource
import edu.ucne.blayverth_reyes_ap2_p2.domain.model.Model
import edu.ucne.blayverth_reyes_ap2_p2.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): Repository {
    override fun getGastos(): Flow<Resource<List<Model>>> = flow {
        emit(Resource.Loading())
        val response = remoteDataSource.getGastos()
        response.onSuccess { gastosList ->
            val gastosDominio = gastosList.items.map { it.toDomain() }
            emit(Resource.Success(gastosDominio))
        }.onFailure { exception ->
            emit(Resource.Error(exception.message ?: "Error desconocido al obtener gastos"))
        }
    }

    override fun getGastoDetail(id: Int): Flow<Resource<Model>> = flow {
        emit(Resource.Loading())
        val response = remoteDataSource.getGastoDetail(id)
        response.onSuccess { gastoDto ->
            emit(Resource.Success(gastoDto.toDomain()))
        }.onFailure { exception ->
            emit(Resource.Error(exception.message ?: "Error desconocido al obtener el detalle"))
        }
    }
}