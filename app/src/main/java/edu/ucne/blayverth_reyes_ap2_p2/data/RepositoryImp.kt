package edu.ucne.blayverth_reyes_ap2_p2.data

import edu.ucne.blayverth_reyes_ap2_p2.data.remote.RemoteDataSource
import edu.ucne.blayverth_reyes_ap2_p2.domain.repository.Repository
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): Repository {
}