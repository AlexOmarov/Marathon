package ru.somarov.marathon.backend.main.core.remote

import java.io.IOException

abstract class BaseDataSource<T : Any, V: Any>(private val tag: String) {

    fun handle(arg: V, func: (arg: V) -> T): Result<T> {
        return try {
            val res = func(arg)
            Result.Success(res)
        } catch (e: Throwable) {
            Result.Error(IOException("Error handling $tag", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}