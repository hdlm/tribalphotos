package com.tribal.tribalphotos.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.tribal.tribalphotos.MainActivity
import com.tribal.tribalphotos.R
import com.tribal.tribalphotos.model.Favorite
import com.tribal.tribalphotos.ui.photo.PhotoGalleryViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.KoinComponent

class UserProfileFragment : Fragment(), KoinComponent {

    private val photoGalleryViewModel: PhotoGalleryViewModel by sharedViewModel()
    private lateinit var picUserProfile : ImageView
    private lateinit var tvUsername : TextView
    private lateinit var tvName : TextView
    private lateinit var tvPortfolio : TextView
    private lateinit var tvBio: TextView
    private lateinit var tvTotalLikes : TextView
    private lateinit var tvTotalPhotos : TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(MainActivity.TAG, "view created: ${this.javaClass.simpleName}")

        picUserProfile = view.findViewById(R.id.imgvUserProfile)
        tvUsername = view.findViewById(R.id.tvUsername)
        tvName = view.findViewById(R.id.tvName)
        tvPortfolio = view.findViewById(R.id.tvPortfolio)
        tvBio = view.findViewById(R.id.tvBio)
        tvTotalLikes = view.findViewById(R.id.tvTotalLikes)
        tvTotalPhotos = view.findViewById(R.id.tvTotalPhotos)
        progressBar = view.findViewById(R.id.progressBarUserProfile)

        observeViewModel()
        initViews()
    }

    private fun initViews() {
        progressBar.visibility = View.VISIBLE

        photoGalleryViewModel.userProfileSelectedLiveData.value?.let {
            updateUserProfile(it)
        }
    }

    private fun observeViewModel(): Unit = photoGalleryViewModel.run {
        userProfileSelectedLiveData.observe (viewLifecycleOwner, Observer {
                it?.let {
                    updateUserProfile(it)
                }
            })

            loadingState.observe(viewLifecycleOwner, Observer {
                if (it) {
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }
            })
        }

    private fun updateUserProfile(favorite: Favorite) : Unit {

        with(favorite) {
            Picasso.get().load(favorite.profileImage)
                .error(com.tribal.tribalphotos.R.drawable.ic_launcher_background)
                .into(picUserProfile)
            tvUsername.text = username
            tvName.text = name
            tvPortfolio.text = linkProfile
            tvBio.text = bio
            tvTotalLikes.text = totalLikes.toString()
            tvTotalPhotos.text = totalPhotos.toString()
        }

        progressBar.visibility = View.GONE

    }

}