package ru.somarov.marathon.backend.main.core.api.response

data class BaseResponse<T>(private val list: List<T>)