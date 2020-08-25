package com.tribal.tribalphotos.ui.favorite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.tribal.tribalphotos.MainActivity
import com.tribal.tribalphotos.R
import com.tribal.tribalphotos.ui.adapter.itemModel.PhotoGalleryItemModel
import com.tribal.tribalphotos.ui.photo.PhotoGalleryViewModel
import kotlinx.android.synthetic.main.fragment_photo_favorite_profile.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.KoinComponent


class PhotoFavoriteProfileFragment : Fragment(), KoinComponent {
    private val photoGalleryViewModel: PhotoGalleryViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo_favorite_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(MainActivity.TAG, "view created: ${this.javaClass.simpleName}")

//        progressBarPhotoGallery.visibility = View.VISIBLE
        observeViewModel()
        initViews()
}

    private fun initViews() {
        photoGalleryViewModel.userProfileSelectedLiveData?.let {
            try {
                updateUserProfile(photoGalleryViewModel.userProfileSelectedLiveData as PhotoGalleryItemModel)
            } catch (ignore: java.lang.ClassCastException) { }
        }
    }

    private fun observeViewModel(): Unit = photoGalleryViewModel.run {
        userProfileSelectedLiveData?.let { itemModel ->
            itemModel.observe(viewLifecycleOwner, Observer {
                it?.let {
                    updateUserProfile(it as PhotoGalleryItemModel)
                }
            })

            loadingState.observe(viewLifecycleOwner, Observer {
                if (it) {
                    progressBarUserProfile.visibility = View.VISIBLE
                } else {
                    progressBarUserProfile.visibility = View.GONE
                }
            })
        }
    }

    private fun updateUserProfile(regis: PhotoGalleryItemModel) : Unit {
        with(regis.model) {
            Picasso.get().load(user?.profile_image?.medium)
                .error(R.drawable.ic_launcher_background)
                .into(imgvUserProfile)

            tvUsername.text = user?.id
            tvPortfolio.text = user?.portfolio_url
            tvBio.text = user?.bio
//            tvTotalPhotos.text = total_photos.toString()
        }
    }

}