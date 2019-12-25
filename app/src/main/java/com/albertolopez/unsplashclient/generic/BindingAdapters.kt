package com.albertolopez.unsplashclient.generic

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.albertolopez.unsplashclient.R
import com.squareup.picasso.Picasso.*

/**
 * Binding adapter used in the main activity.
 * Loads the image from the specified url into the image view via Picasso library.
 */
@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) = with(view.context)
    .load(imageUrl)
    .placeholder(R.drawable.loading)
    .resize(
        IMAGE_SIZE,
        IMAGE_SIZE
    )
    .centerCrop()
    .into(view)

/**
 * Binding adapter used in the detail activity.
 * Loads the image from the specified url into the image view via Picasso library.
 */
@BindingAdapter("bind:imageUrlFull")
fun loadFullImage(view: ImageView, imageUrl: String?) = with(view.context)
    .load(imageUrl)
    .placeholder(R.drawable.loading)
    .into(view)

const val IMAGE_SIZE = 250