package com.albertolopez.unsplashclient.remotedatasource.model

/**
 * Model used in /photos service to obtain the photo information
 */
data class Photo(
    val id: String? = null,
    val urls: Urls? = null
)