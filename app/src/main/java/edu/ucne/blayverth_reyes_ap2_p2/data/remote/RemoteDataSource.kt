package edu.ucne.blayverth_reyes_ap2_p2.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: Api
) {
}