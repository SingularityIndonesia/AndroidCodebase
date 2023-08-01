package com.singularity_code.codebase.util.auth

import android.content.Context
import com.singularity_code.codebase.util.Singularity

private const val AUTH_PREF_NAME = "AUTH_PREF"
private const val LOGIN_TOKEN_KEY = "LOGIN_TOKEN_KEY"
private val AuthPreference
    get() = Singularity.application.getSharedPreferences(
        "AUTH_PREF",
        Context.MODE_PRIVATE
    )

var LoginToken: AuthToken?
    set(value) {
        val token = value?.toStringJson() ?: "{}"
        AuthPreference.edit()
            .putString(LOGIN_TOKEN_KEY, token)
            .apply()
    }
    get() = AuthPreference
        .getString(LOGIN_TOKEN_KEY, "{}")
        .jsonToAuthToken()

val IsAuthorized: Boolean
    get() {
        val loginToken = LoginToken

        return !loginToken?.let {
            (it.basic ?: "") + (it.jwt ?: "") + (it.bearer ?: "")
        }.isNullOrBlank()
    }

fun logout() {
    AuthPreference.edit()
        .clear()
        .apply()
}