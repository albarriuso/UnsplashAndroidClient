package com.albertolopez.unsplashclient.remotedatasource.model

/**
 * Model used in /photos and /photos/{id} services to obtain the photo's urls.
 */
data class Urls(
    val raw: String? = null,
    var full: String? = null,
    var regular: String? = null,
    var small: String? = null,
    var thumb: String? = null
)
