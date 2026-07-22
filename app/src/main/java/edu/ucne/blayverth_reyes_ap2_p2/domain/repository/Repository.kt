package edu.ucne.blayverth_reyes_ap2_p2.domain.repository

import edu.ucne.blayverth_reyes_ap2_p2.data.remote.Resource
import edu.ucne.blayverth_reyes_ap2_p2.domain.model.Model
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getGastos(): Flow<Resource<List<Model>>>
    fun getGastoDetail(id: Int): Flow<Resource<Model>>
}