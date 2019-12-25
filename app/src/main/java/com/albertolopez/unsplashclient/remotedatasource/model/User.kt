package com.albertolopez.unsplashclient.remotedatasource.model

/**
 * Model used in /photos/{id} service to obtain the user's information.
 */
data class User(
    val username: String? = null
)