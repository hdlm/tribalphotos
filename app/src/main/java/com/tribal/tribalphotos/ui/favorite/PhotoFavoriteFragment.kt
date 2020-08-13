package com.tribal.tribalphotos.ui.favorite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tribal.tribalphotos.MainActivity
import com.tribal.tribalphotos.R
import com.tribal.tribalphotos.model.Favorite
import com.tribal.tribalphotos.ui.adapter.FavoriteAdapter
import com.tribal.tribalphotos.ui.photo.PhotoGalleryFragment
import kotlinx.android.synthetic.main.fragment_photo_favorite.*


class PhotoFavoriteFragment : Fragment() {

    private var adapter: FavoriteAdapter? = null
    private var favoriteList: List<Favorite>? = null
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_photo_favorite, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(MainActivity.TAG, "view created: ${this.javaClass.simpleName}")

        initView()
    }

    private fun initView(): Unit {
        favoriteList = loadDummyList()
        adapter = FavoriteAdapter(favoriteList!!, context)
        layoutManager = LinearLayoutManager(context)
        rvFavorite.adapter= adapter
        var pausa = 0
    }

    private fun loadDummyList(): List<Favorite> {
        var list = ArrayList<Favorite>()

        list.add(Favorite("hdelamano", "buena gente", null, "thebest.com"))
        list.add(Favorite("nmala", "inteligente", null, "theteacher.com"))
        list.add(Favorite("malba", "comprometida", null, "serious.com"))

        return list.toList()
    }

    companion object {
        @JvmStatic
        fun newInstance() = PhotoFavoriteFragment()
    }
}