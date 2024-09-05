package api.adapters.presenters

import kotlinx.serialization.Serializable

@Serializable
data class TokensPresenter(
    val accessToken: String,
    val refreshToken: String,
)