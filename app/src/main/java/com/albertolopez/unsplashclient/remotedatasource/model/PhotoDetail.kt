package com.albertolopez.unsplashclient.remotedatasource.model

/**
 * Model used in /photos/{id} service to obtain the photo detailed information.
 */
data class PhotoDetail(
    val id: String? = null,
    val description: String? = null,
    val alt_description: String? = null,
    val urls: Urls? = null,
    val user: User? = null,
    val exif: Exif? = null
)

