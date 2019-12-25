package com.albertolopez.unsplashclient.remotedatasource

import com.albertolopez.unsplashclient.remotedatasource.model.Photo
import com.albertolopez.unsplashclient.remotedatasource.model.PhotoDetail
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Definition of the API methods
 */
interface WebService {
    @GET("/photos")
    suspend fun getPhotos(): List<Photo>

    @GET("/photos/{id}")
    suspend fun getPhoto(@Path("id") id: String?): PhotoDetail
}