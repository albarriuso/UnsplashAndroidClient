package com.albertolopez.unsplashclient.remotedatasource.model

/**
 * Model used in /photos/{id} service to obtain the camera data.
 */
data class Exif(
    val make: String? = null,
    val model: String? = null
)

