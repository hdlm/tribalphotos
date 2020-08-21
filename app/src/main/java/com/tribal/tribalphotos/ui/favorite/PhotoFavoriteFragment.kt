package com.tribal.tribalphotos.ui.favorite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tribal.tribalphotos.MainActivity
import com.tribal.tribalphotos.R
import com.tribal.tribalphotos.model.Favorite
import com.tribal.tribalphotos.ui.adapter.DynamicAdapter
import com.tribal.tribalphotos.ui.adapter.itemModel.FavoriteItemModel
import com.tribal.tribalphotos.ui.adapter.itemModel.ItemModel
import com.tribal.tribalphotos.ui.adapter.typeFactory.FavoriteTypesFactoryImpl
import com.tribal.tribalphotos.utils.makeGoneAlpha
import com.tribal.tribalphotos.utils.makeVisibleAlpha
import kotlinx.android.synthetic.main.fragment_photo_favorite.*
import kotlinx.android.synthetic.main.no_items_layout.*
import kotlinx.android.synthetic.main.recyclerview_item_favorite_row.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent


class PhotoFavoriteFragment : Fragment(), KoinComponent {

    private val tribalFavoriteViewModelPhoto: PhotoFavoritesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_photo_favorite, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(MainActivity.TAG, "view created: ${this.javaClass.simpleName}")

        observeViewModel()
        tribalFavoriteViewModelPhoto.getFavorites()

        initView()

    }

    private fun initView(): Unit {

//        rvGallery.layoutManager = BeelineLayoutManager().apply {
//            configLookup = object : BeelineLayoutManager.ConfigLookup {
//                override fun getSpanSize(position: Int): Int = 2
//                override fun getZIndex(position: Int): Float = 1f
//                override fun isSolid(position: Int): Boolean = true
//                override fun getVerticalOverlay(position: Int): Float = 0f
//                override fun getGravity(position: Int): BeelineLayoutManager.Gravity = BeelineLayoutManager.Gravity.LEFT
//            }
//        }
//        rvGallery.clipToOutline = true
        rvFavorite.layoutManager = LinearLayoutManager(context)
        rvFavorite.setHasFixedSize(true)

    }

    private fun observeViewModel() = tribalFavoriteViewModelPhoto.run {
        favoritesLiveData.observe (viewLifecycleOwner, {
            setAdapter(it)
        })

        loadingState.observe(viewLifecycleOwner, Observer {
            if (it) {
                // skeleton.show()
            } else {
                //skeleton.hide()
            }
        })

    }

    private fun setAdapter (list: List<Favorite?>): Unit{

        if (list.isEmpty()) {
            rvFavorite.makeGoneAlpha(200) {
                tvWhoops.text = getString(R.string.fragment_photo_favorite_no_items)
                lyNoElements.makeVisibleAlpha(200) { }
            }
        } else {
            rvFavorite.apply {
                makeGoneAlpha(50) {
                    adapter = DynamicAdapter(
                        typeFactory = FavoriteTypesFactoryImpl(),
                        items = getFavoritesForAdapter(list)
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