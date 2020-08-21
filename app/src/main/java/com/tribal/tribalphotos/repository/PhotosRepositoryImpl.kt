package com.tribal.tribalphotos.repository

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.tribal.tribalphotos.MainActivity
import com.tribal.tribalphotos.model.unsplash.Photo
import com.tribal.tribalphotos.network.volley.VolleyClient
import com.tribal.tribalphotos.utils.mapTo
import kotlinx.coroutines.suspendCancellableCoroutine
import org.koin.core.KoinComponent
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class PhotosRepositoryImpl(context: Context) : PhotosRepository, KoinComponent {
    private var queue = VolleyClient.getInstance(context)
    val url = "https://api.unsplash.com/photos/?client_id=oP_fIPn5u0SWwECyN5Ko7dvl5bFJ1gX_PXenVSsPg3Q&random?count=10"

    override suspend fun getPhotos(): List<Photo?>? =
        suspendCancellableCoroutine { cont ->
            val url = "https://api.unsplash.com/photos/?client_id=oP_fIPn5u0SWwECyN5Ko7dvl5bFJ1gX_PXenVSsPg3Q&random?count=10"
            val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    if (cont.isActive) {
                        var lst : MutableList<Photo?> = ArrayList()
                        for(idx in 0 until response.length()) {
                            Log.d(MainActivity.TAG, "Loading photo ($idx): %s".format(response.getJSONObject(idx).toString()))
                            var photo: Photo? = response.getJSONObject(idx)?.mapTo(
                                Photo::class.java)
                            Log.d(MainActivity.TAG, "user profile: ${photo?.user?.profile_image?.medium}")
                            lst.add(photo)
                        }
                        cont.resume(lst)
                    }
                },
                Response.ErrorListener { error ->
                    Log.d("TAGTAG", "WebService -> Response is: ${error.message}")
                    if(cont.isActive) {
                        cont.resumeWithException(error)
                    }
                }
            )
            queue.addToRequestQueue(jsonArrayRequest)

        }


}

