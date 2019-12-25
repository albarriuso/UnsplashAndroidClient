package com.albertolopez.unsplashclient.remotedatasource

import javax.inject.Inject

class UnsplashRepository @Inject constructor(val webService: WebService) {

    suspend fun getPhotos() = webService.getPhotos()

    suspend fun getPhotoDetail(idPhoto: String?) = webService.getPhoto(idPhoto)
}