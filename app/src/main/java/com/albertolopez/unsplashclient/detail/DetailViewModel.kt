package com.albertolopez.unsplashclient.detail

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.albertolopez.unsplashclient.remotedatasource.UnsplashRepository
import com.albertolopez.unsplashclient.remotedatasource.model.PhotoDetail
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * View model associated with DetailActivity.
 */
class DetailViewModel @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) : ViewModel() {
    //LiveData variables used for modifying the UI via data binding:
    var photoDetailLiveData: MutableLiveData<PhotoDetail> = MutableLiveData()
    var userDataVisibility: MutableLiveData<Int> = MutableLiveData(View.GONE)
    var cameraBrandVisibility: MutableLiveData<Int> = MutableLiveData(View.GONE)
    var cameraModelDataVisibility: MutableLiveData<Int> = MutableLiveData(View.GONE)


    /**
     * Performs a network request using a coroutine to obtain the detailed information of a photo.
     * @param idPhoto the photo identifier
     */
    fun getPhotoDetail(idPhoto: String?) = liveData(Dispatchers.IO) {
        var getPhotoSuccessful: Boolean
        try {
            val photoDetail = unsplashRepository.getPhotoDetail(idPhoto)
            //Update photo detail values
            photoDetailLiveData.postValue(photoDetail)
            //If user name is available, set the views which display this information as visible
            if (photoDetail.user?.username != null) {
                userDataVisibility.postValue(View.VISIBLE)
            }
            //If camera brand information is available, set the views which display this information as visible
            if (photoDetail.exif?.make != null) {
                cameraBrandVisibility.postValue(View.VISIBLE)
            }
            //If camera model information is available, set the views which display this information as visible
            if (photoDetail.exif?.model != null) {
                cameraModelDataVisibility.postValue(View.VISIBLE)
            }
            getPhotoSuccessful = true
        } catch (ex: Exception) {
            getPhotoSuccessful = false
        }
        //Emit a boolean value to notify the activity if the request result was successful or not
        emit(getPhotoSuccessful)
    }
}
