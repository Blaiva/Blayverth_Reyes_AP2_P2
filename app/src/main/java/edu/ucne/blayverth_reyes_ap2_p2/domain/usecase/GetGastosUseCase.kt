package edu.ucne.blayverth_reyes_ap2_p2.domain.usecase

import edu.ucne.blayverth_reyes_ap2_p2.data.remote.Resource
import edu.ucne.blayverth_reyes_ap2_p2.domain.model.Model
import edu.ucne.blayverth_reyes_ap2_p2.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGastosUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Resource<List<Model>>> {
        return repository.getGastos()
    }
}