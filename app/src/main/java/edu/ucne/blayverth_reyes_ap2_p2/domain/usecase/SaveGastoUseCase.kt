package edu.ucne.blayverth_reyes_ap2_p2.domain.usecase

import edu.ucne.blayverth_reyes_ap2_p2.data.remote.Resource
import edu.ucne.blayverth_reyes_ap2_p2.domain.model.Model
import edu.ucne.blayverth_reyes_ap2_p2.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveGastoUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(gasto: Model): Flow<Resource<Model>> {
        return if (gasto.gastoId == 0) {
            repository.saveGasto(gasto)
        } else {
            repository.updateGasto(gasto.gastoId, gasto)
        }
    }
}