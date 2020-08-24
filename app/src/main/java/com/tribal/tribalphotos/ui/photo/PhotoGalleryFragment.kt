package com.tribal.tribalphotos.ui.photo

import PhotoGalleryLayoutManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.tribal.tribalphotos.MainActivity
import com.tribal.tribalphotos.R
import com.tribal.tribalphotos.model.Favorite
import com.tribal.tribalphotos.model.unsplash.Photo
import com.tribal.tribalphotos.ui.adapter.DynamicAdapter
import com.tribal.tribalphotos.ui.adapter.itemModel.ItemModel
import com.tribal.tribalphotos.ui.adapter.itemModel.PhotoGalleryItemModel
import com.tribal.tribalphotos.ui.adapter.typeFactory.PhotoGalleryTypesFactoryImpl
import com.tribal.tribalphotos.ui.favorite.PhotoFavoriteViewModel
import com.tribal.tribalphotos.utils.makeGoneAlpha
import com.tribal.tribalphotos.utils.makeVisibleAlpha
import com.tribal.tribalphotos.utils.mapTo
import kotlinx.android.synthetic.main.fragment_photo_gallery.*
import kotlinx.android.synthetic.main.item_photo_row.*
import kotlinx.android.synthetic.main.no_items_layout.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent


class PhotoGalleryFragment : Fragment(), KoinComponent {

    private val photoGalleryViewModel: PhotoGalleryViewModel by viewModel()
    private val favoriteViewModelPhoto: PhotoFavoriteViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_photo_gallery, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(MainActivity.TAG, "view created: ${this.javaClass.simpleName}")

        progressBarPhotoGallery.visibility = View.VISIBLE
        favoriteViewModelPhoto.prepareLocalDatabase(requireContext())

        observeViewModel()
        photoGalleryViewModel.getRandomPhotos()

        initViews()

    }

    private fun initViews() {

        rvGallery.layoutManager = PhotoGalleryLayoutManager().apply {
            configLookup = object : PhotoGalleryLayoutManager.ConfigLookup {
                override fun getSpanSize(position: Int): Int = 2
                override fun getZIndex(position: Int): Float = 1f
                override fun isSolid(position: Int): Boolean = true
                override fun getVerticalOverlay(position: Int): Float = 0f
                override fun getGravity(position: Int): PhotoGalleryLayoutManager.Gravity = PhotoGalleryLayoutManager.Gravity.LEFT
            }
        }
        rvGallery.clipToOutline = true
        rvGallery.setHasFixedSize(true)

    }

    private fun observeViewModel() = photoGalleryViewModel.run {

        photosLiveData.observe (viewLifecycleOwner, Observer {
            setAdapter(it)
        })

        loadingState.observe(viewLifecycleOwner, Observer {
            if (it) {
                progressBarPhotoGallery.visibility = View.VISIBLE
            } else {
                progressBarPhotoGallery.visibility = View.GONE
            }
        })
    }

    private fun setAdapter(list: List<Photo?>): Unit {
        if (list.isEmpty()) {
            rvGallery.makeGoneAlpha(200) {
                tv_whoops.text = getString(R.string.fragment_photo_gallery_no_items)
                lYNoElements.makeVisibleAlpha(200) { }
            }
        } else {
            rvGallery.apply {
                makeGoneAlpha(50) {
                    adapter = DynamicAdapter(
                        typeFactory = PhotoGalleryTypesFactoryImpl(),
                        items = getPhotosGalleryForAdapter(list),
                        onClick = { itemModel ->
                            val photo = (itemModel as PhotoGalleryItemModel).model
                            var favorite =  photo.mapTo(Favorite::class.java)
                            Log.d(MainActivity.TAG, "onClick event fire")
                            favoriteViewModelPhoto.addFavorite(favorite!!)
                } )
                    makeVisibleAlpha(100)
                }
            }
        }
    }


    private fun getPhotosGalleryForAdapter(list: List<Photo?>): List<ItemModel> {
        val itemList = ArrayList<ItemModel>()
        list.forEach {
            itemList.add(PhotoGalleryItemModel(it!!))
        }
        return itemList

    }

}


