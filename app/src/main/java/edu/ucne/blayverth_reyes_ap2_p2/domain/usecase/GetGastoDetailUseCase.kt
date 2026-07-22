package edu.ucne.blayverth_reyes_ap2_p2.domain.usecase

import edu.ucne.blayverth_reyes_ap2_p2.data.remote.Resource
import edu.ucne.blayverth_reyes_ap2_p2.domain.model.Model
import edu.ucne.blayverth_reyes_ap2_p2.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGastoDetailUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(id: Int): Flow<Resource<Model>>{
        return repository.getGastoDetail(id)
    }
}