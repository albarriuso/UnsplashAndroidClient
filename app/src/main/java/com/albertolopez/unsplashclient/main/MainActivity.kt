package com.albertolopez.unsplashclient.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager

import com.albertolopez.unsplashclient.BR
import com.albertolopez.unsplashclient.R
import com.albertolopez.unsplashclient.databinding.ActivityMainBinding
import com.albertolopez.unsplashclient.detail.DetailActivity
import com.albertolopez.unsplashclient.detail.DetailActivity.Companion.PHOTO_ID_EXTRA
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

import javax.inject.Inject

/**
 * Main activity, displays a list of photos displayed in a grid
 */
class MainActivity : DaggerAppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Init main view model
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        //Init data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.setVariable(BR.mainViewModel, viewModel)
        //Configure recycler view
        photos_recycler.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, ITEMS_PER_ROW)
        }

        //Observe for the network request which retrieves the list of photos
        viewModel.photos.observe(this, Observer { photos ->
            progressBar.visibility = View.GONE
            //If request was unsuccessful, display a toast to inform the user.
            if (photos.isNullOrEmpty()) {
                Toast.makeText(
                    this,
                    getString(R.string.main_activity_load_error),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                //If request was successful, display the result.
                viewModel.setPhotos(photos)
            }

        })

        //Observe for image clicks to start the detail activity
        viewModel.imageId.observe(this, Observer {
            it?.let { selectedImageId ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(PHOTO_ID_EXTRA, selectedImageId)
                startActivity(intent)
            }
        })
    }

    companion object {
        const val ITEMS_PER_ROW = 2
    }
}