package ru.somarov.marathon.backend.main.plugin.login.dto

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: Boolean? = null,
    val error: Int? = null
)
