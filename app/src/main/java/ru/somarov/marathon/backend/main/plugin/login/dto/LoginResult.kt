package ru.somarov.marathon.backend.main.plugin.login.dto

import ru.somarov.marathon.backend.main.plugin.login.dto.LoggedInUserView

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)
