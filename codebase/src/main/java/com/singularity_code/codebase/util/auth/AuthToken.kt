package com.singularity_code.codebase.util.auth

import com.google.gson.annotations.SerializedName
import com.singularity_code.codebase.pattern.JsonConvertible
import com.singularity_code.codebase.util.serialization.toObject


interface AuthToken : JsonConvertible {
    val bearer: String?
    val bearerExpiredDateMillis: Long?
    val basic: String?
    val basicExpiredDateMillis: Long?
    val jwt: String?
    val jwtExpiredDateMillis: Long?
}

private data class AuthTokenImpl(
    @SerializedName("bearer")
    override val bearer: String? = null,
    @SerializedName("bearerExpiredDateMillis")
    override val bearerExpiredDateMillis: Long? = null,
    @SerializedName("basic")
    override val basic: String? = null,
    @SerializedName("basicExpiredDateMillis")
    override val basicExpiredDateMillis: Long? = null,
    @SerializedName("jwt")
    override val jwt: String? = null,
    @SerializedName("jwtExpiredDateMillis")
    override val jwtExpiredDateMillis: Long? = null
) : AuthToken

fun authToken(
    bearer: String = "",
    bearerExpiredDateMillis: Long? = null,
    basic: String = "",
    basicExpiredDateMillis: Long? = null,
    jwt: String = "",
    jwtExpiredDateMillis: Long? = null
): AuthToken = AuthTokenImpl(
    bearer = bearer,
    bearerExpiredDateMillis = bearerExpiredDateMillis,
    basic = basic,
    basicExpiredDateMillis = basicExpiredDateMillis,
    jwt = jwt,
    jwtExpiredDateMillis = jwtExpiredDateMillis
)

fun String?.jsonToAuthToken(): AuthToken? =
    this?.toObject<AuthTokenImpl>()