package com.tribal.tribalphotos.ui.photo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tribal.tribalphotos.MainActivity
import com.tribal.tribalphotos.R
import com.tribal.tribalphotos.model.Photo
import com.tribal.tribalphotos.repository.TribalPhotosRepository
import com.tribal.tribalphotos.repository.TribalPhotosRepositoryImpl
import com.tribal.tribalphotos.ui.TribalPhotosViewModel
import com.tribal.tribalphotos.ui.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.fragment_photo_gallery.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import org.koin.core.inject


class PhotoGalleryFragment : Fragment(), KoinComponent {

    private val tribalPhotosViewModel: TribalPhotosViewModel by viewModel()
    private lateinit var linearLayoutManager: LinearLayoutManager
//    protected val repository: TribalPhotosRepositoryImpl by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_photo_gallery, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(MainActivity.TAG, "view created: ${this.javaClass.simpleName}")

//        val sayHello = repository.giveHello()
        val probando: String = tribalPhotosViewModel.cadenaPrueba
//        observeViewModel()
        tribalPhotosViewModel.getRandomPhotos()


        initViews()

    }

    private fun initViews() {

        linearLayoutManager = LinearLayoutManager(context)
        rvGallery.layoutManager = linearLayoutManager

    }

    private fun observeViewModel() = tribalPhotosViewModel.run {
        photosLiveData.observe (viewLifecycleOwner, Observer {
            val adapter = RecyclerAdapter(it)
            rvGallery.layoutManager = linearLayoutManager
            rvGallery.adapter = adapter
        })

        loadingState.observe(viewLifecycleOwner, Observer {
            if (it) {
//                skeleton.show()
            } else {
//                skeleton.hide()
            }
        })
    }


    companion object {
        fun newInstance(): PhotoGalleryFragment {
            return PhotoGalleryFragment()
        }
    }

}