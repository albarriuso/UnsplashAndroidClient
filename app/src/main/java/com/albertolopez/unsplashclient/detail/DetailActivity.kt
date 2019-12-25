package com.albertolopez.unsplashclient.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.albertolopez.unsplashclient.BR
import com.albertolopez.unsplashclient.R
import com.albertolopez.unsplashclient.databinding.ActivityDetailBinding
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

/**
 * Activity used to show a photo detail view.
 */
class DetailActivity : DaggerAppCompatActivity() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Init detail view model
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
        //Init data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.lifecycleOwner = this
        binding.setVariable(BR.detailViewModel, viewModel)
        //Obtain the photo id from intent extras
        val idPhoto = intent.getStringExtra(PHOTO_ID_EXTRA)

        //Display back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        //Perform the get photo detail request and observe for its successful/unsuccessful result
        viewModel.getPhotoDetail(idPhoto).observe(this, Observer { getPhotoSuccessful ->
            //Hide progress bar once the request has finished
            progressBar.visibility = View.GONE
            //If request was unsuccessful, display a toast to inform the user.
            if (!getPhotoSuccessful) {
                Toast.makeText(
                    this,
                    getString(R.string.detail_activity_load_error),
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        //Set photo's description as action bar title if available
        viewModel.photoDetailLiveData.observe(this, Observer {
            it?.let { photo ->
                if (photo.description.isNullOrEmpty()) {
                    supportActionBar?.title = getString(R.string.detail_activity_no_title)
                } else {
                    supportActionBar?.title = photo.description
                }
            }
        })
    }

    /**
     * Handle back to main on support action bar navigation back pressed
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                this.finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        //Constant value used as intent extra key.
        const val PHOTO_ID_EXTRA = "photo_id_extra"
    }
}