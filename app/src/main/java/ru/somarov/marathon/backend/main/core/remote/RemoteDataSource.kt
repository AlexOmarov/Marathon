package ru.somarov.marathon.backend.main.core.remote

class RemoteDataSource(private val remoteService: RemoteService) {

    suspend fun getRemoteMarathons(runnerId: Int) =
        remoteService.getMarathons(1, 20, runnerId)
}