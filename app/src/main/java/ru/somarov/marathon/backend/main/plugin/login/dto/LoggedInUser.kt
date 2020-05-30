package ru.somarov.marathon.backend.main.plugin.login.dto

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val userId: String,
    val displayName: String
)
