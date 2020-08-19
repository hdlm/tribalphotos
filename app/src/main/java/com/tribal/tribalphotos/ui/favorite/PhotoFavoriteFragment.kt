package com.tribal.tribalphotos.ui.favorite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tribal.tribalphotos.MainActivity
import com.tribal.tribalphotos.R
import com.tribal.tribalphotos.model.Favorite
import com.tribal.tribalphotos.ui.TribalFavoritesViewModel
import com.tribal.tribalphotos.ui.adapter.DynamicAdapter
import com.tribal.tribalphotos.ui.adapter.itemModel.ItemModel
import com.tribal.tribalphotos.ui.adapter.typeFactory.PhotoGalleryTypesFactoryImpl
import com.tribal.tribalphotos.utils.makeGoneAlpha
import com.tribal.tribalphotos.utils.makeVisibleAlpha
import kotlinx.android.synthetic.main.fragment_photo_gallery.*
import kotlinx.android.synthetic.main.no_items_layout.*
import kotlinx.android.synthetic.main.recyclerview_item_row.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import java.util.Observer


class PhotoFavoriteFragment : Fragment(), KoinComponent {

    private val tribalFavoriteViewModel: TribalFavoritesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_photo_favorite, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(MainActivity.TAG, "view created: ${this.javaClass.simpleName}")

        observeViewModel()
        tribalFavoriteViewModel.getUserInfo()

        initView()

    }

    //PENDING teminar la implementacion de los metodos de la clase
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
        rvGallery.setHasFixedSize(true)

    }

    private fun observeViewModel() = tribalFavoriteViewModel.run {

    }

    private fun setAdapter (list: List<Favorite?>): Unit{
        //PENDING actualizar el RecyclerView
        if (list.isEmpty()) {
            rvGallery.makeGoneAlpha(200) {
                tvWhoops.text = getString(R.string.fragment_photo_gallery_no_items)
                lyNoElements.makeVisibleAlpha(200) { }
            }
        } else {
            rvGallery.apply {
                makeGoneAlpha(50) {
                    adapter = DynamicAdapter(
                        typeFactory = PhotoGalleryTypesFactoryImpl(),
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
//            itemList.add(Favorite)
        }
        return itemList
    }


}