package com.albertolopez.unsplashclient.main


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.albertolopez.unsplashclient.remotedatasource.UnsplashRepository
import com.albertolopez.unsplashclient.remotedatasource.model.Photo
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * View model associated with MainActivity.
 */
class MainViewModel @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) : ViewModel() {

    //LiveData which is set with an image's id once it is clicked.
    val imageId : MutableLiveData<String> = MutableLiveData()
    //Photos list attached to the adapter
    private var photosList: ArrayList<Photo> = ArrayList()
    //RecyclerView's adapter
    val adapter = PhotosAdapter(this, photosList)

    //Coroutine used to obtain the list of photos to be displayed by a network request.
    val photos = liveData(Dispatchers.IO) {
        try{
            //Perform network request
            val obtainedPhotos = unsplashRepository.getPhotos()
            //Emit the result.
            emit(obtainedPhotos)
        }catch (ex:Exception){
            //Request failed, emit empty list.
            emit(ArrayList<Photo>())
        }
    }

    /**
     * Given the item position in the list, retrieves its small image url
     * @param position the position of the item in the list
     * @return the small image url.
     */
    fun getImageUrl(position: Int):String?{
        return photos.value?.get(position)?.urls?.small
    }

    /**
     * Sets the received lists of photos to be displayed in the recycler view.
     * @param photos the list of photos to be shown
     */
    fun setPhotos(photos: List<Photo>){
        photosList.clear()
        photosList.addAll(photos)
        adapter.notifyDataSetChanged()
    }

    /**
     * Triggered when a RecyclerView's element is clicked (via data-binding)
     * @param position the position of the clicked item
     */
    fun onPhotoClick(position:Int){
        imageId.value = photos.value?.get(position)?.id
    }
}


