package com.tribal.tribalphotos.ui.favorite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tribal.tribalphotos.MainActivity
import com.tribal.tribalphotos.R
import com.tribal.tribalphotos.model.Favorite
import com.tribal.tribalphotos.ui.adapter.DynamicAdapter
import com.tribal.tribalphotos.ui.adapter.itemModel.FavoriteItemModel
import com.tribal.tribalphotos.ui.adapter.itemModel.ItemModel
import com.tribal.tribalphotos.ui.adapter.typeFactory.FavoriteTypesFactoryImpl
import com.tribal.tribalphotos.ui.photo.PhotoGalleryViewModel
import com.tribal.tribalphotos.utils.makeGoneAlpha
import com.tribal.tribalphotos.utils.makeVisibleAlpha
import kotlinx.android.synthetic.main.fragment_photo_favorite.*
import kotlinx.android.synthetic.main.no_items_layout.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.KoinComponent


class PhotoFavoriteFragment : Fragment(), KoinComponent {
    private val photoGalleryViewModel: PhotoGalleryViewModel by sharedViewModel()
    private val favoriteViewModelPhoto: PhotoFavoriteViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_photo_favorite, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(MainActivity.TAG, "view created: ${this.javaClass.simpleName}")

        progressBarPhotoFavorite.visibility = View.VISIBLE
        favoriteViewModelPhoto.prepareLocalDatabase(requireContext())

        observeViewModel()

        initView()

    }

    private fun initView(): Unit {

        rvFavorite.visibility = View.GONE


        val data = listOf("anastacia", "annabel", "andrianina", "adriana")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,data)
        listViewFilter.adapter = adapter

        searchFilter.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

//        rvFavorite.layoutManager = LinearLayoutManager(context)
//        rvFavorite.setHasFixedSize(true)

    }

    private fun observeViewModel() = favoriteViewModelPhoto.run {
//        favoritesLiveData.observe (viewLifecycleOwner, {
//            setAdapter(it)
//        })
//        , favoriteViewModelPhoto.filterData.value
//        favoriteViewModelPhoto.adapterLiveData.observe (viewLifecycleOwner, {
//            val data = arrayOf("anabel", "annie", "anastacia","jonny","henry")
//            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,data)
//            listViewFilter.adapter = adapter
//            favoriteViewModelPhoto.adapterLiveData.postValue(adapter)
//        })


        loadingState.observe(viewLifecycleOwner, Observer {
            if (it) {
                progressBarPhotoFavorite.visibility = View.VISIBLE
            } else {
                progressBarPhotoFavorite.visibility = View.GONE
            }
        })

    }


    private fun setAdapter (list: List<Favorite?>): Unit{

        if (list.isEmpty()) {
            rvFavorite.makeGoneAlpha(200) {
                tv_whoops.text = getString(R.string.fragment_photo_favorite_no_items)
                lYNoElements.makeVisibleAlpha(200) { }
            }
        } else {
            rvFavorite.apply {
                makeGoneAlpha(50) {
                    adapter = DynamicAdapter(
                        typeFactory = FavoriteTypesFactoryImpl(),
                        items = getFavoritesForAdapter(list),
                        onClick = { itemModel ->
                            photoGalleryViewModel.userProfileSelectedLiveData.postValue((itemModel as FavoriteItemModel).model)
                            Log.d(MainActivity.TAG, "${this.javaClass.simpleName} - onClick event fire")
                            requireView().findNavController().navigate(R.id.action_favorite_to_userprofile)
                        },
                        onClickFavorite = { itemModel ->
                            val favorite = (itemModel as FavoriteItemModel).model
                            Log.d(MainActivity.TAG, "onClick event fire")
                            favoriteViewModelPhoto.delete(favorite!!)
                        }
                    )
                    makeVisibleAlpha(100)
                }
            }
        }
    }

    private fun getFavoritesForAdapter (list: List<Favorite?>): List<ItemModel> {
        val itemList = ArrayList<ItemModel>()
        list.forEach {
            itemList.add(FavoriteItemModel(it!!))
        }
        return itemList
    }

}