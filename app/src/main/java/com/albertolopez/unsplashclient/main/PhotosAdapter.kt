package com.albertolopez.unsplashclient.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.albertolopez.unsplashclient.BR
import com.albertolopez.unsplashclient.R
import com.albertolopez.unsplashclient.databinding.PhotoViewBinding
import com.albertolopez.unsplashclient.remotedatasource.model.Photo

/**
 * Adapter used to display the photos in the activity.
 */
class PhotosAdapter(private val viewModel: MainViewModel, private val list: List<Photo>) : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<PhotoViewBinding>(layoutInflater, R.layout.photo_view, parent, false)
        return PhotosViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(viewModel, position)
    }

    class PhotosViewHolder(private val binding: PhotoViewBinding) : RecyclerView.ViewHolder(binding.root) {

        /**
         * When a ViewHolder's view is created, set the view model and the item's position via data-binding.
         */
        fun bind(viewModel: MainViewModel, position: Int) {
            binding.setVariable(BR.mainViewModel, viewModel)
            binding.setVariable(BR.photoListPosition, position)
            binding.executePendingBindings()
        }
    }
}