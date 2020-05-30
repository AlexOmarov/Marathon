package ru.somarov.marathon.backend.main.core.remote

import ru.somarov.marathon.backend.main.core.api.entity.Runner
import java.io.IOException

class RemoteDataSource(private val remoteService: RemoteService) {

    suspend fun getRemoteMarathons() =
        remoteService.getMarathons(1, 20)
    suspend fun getFreeMarathons(runnerId: Int) =
        remoteService.getFreeMarathons(runnerId.toString(), 20)

    suspend fun login(username: String, password: String): Result<Runner> {
        return try {
            val runner = remoteService.login(username, password)
            Result.Success(runner)
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
    }

    suspend fun logout(id: Int): Result<String> {
        return try {
            remoteService.logout(id)
            Result.Success("Success")
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging out", e))
        }
    }

}