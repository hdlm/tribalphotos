package com.tribal.tribalphotos.repository

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.tribal.tribalphotos.MainActivity
import com.tribal.tribalphotos.model.Photo
import com.tribal.tribalphotos.network.volley.VolleyClient
import com.tribal.tribalphotos.utils.mapTo
import kotlinx.coroutines.suspendCancellableCoroutine
import org.json.JSONObject
import org.koin.core.KoinComponent
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class TribalPhotosRepositoryImpl(context: Context) : TribalPhotosRepository, KoinComponent {
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
                            var photo: Photo? = response.getJSONObject(idx)?.mapTo(Photo::class.java)
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
//            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
//                Response.Listener { response ->
//                    if (cont.isActive) {
//                        Log.d("TAGTAG", "Response: %s".format(response.toString()))
//                        val jsonArray = response.keys().forEach { item ->
//                            Log.d("TAGTAG", item)
//                        }
//                        val photo: Photo? = response.get("data")?.mapTo(Photo::class.java)
//                        var lst : MutableList<Photo?> = ArrayList()
//                        lst.add(photo)
//
//                        cont.resume(lst)
//                    }
//                },
//                Response.ErrorListener { error ->
//                    Log.d("TAGTAG", "WebService -> Response is: ${error.message}")
//                    if(cont.isActive) {
//                        cont.resumeWithException(error)
//                    }
//                }
//            )
            queue.addToRequestQueue(jsonArrayRequest)

        }

    override fun giveHello(): String = "Hello Kotlin"

}


//    fun getAll(callback: ICallback): Unit {
//
//        val jsonObjectRequest = JsonObjectRequest(
//            Request.Method.GET, url, null,
//            Response.Listener { response ->
//                Log.d(MainActivity.TAG, "WebService -> getAll(), response is: ${response.toString().substring(0,500)}")
//                val divisa = Divisa.getInstance()
//
//                val data = response.getJSONArray("data")
//                for(idx in 0..data.length()-1) {
//                    val dataObj = data.getJSONObject(idx)
//
//                    var b = dataObj.getString("b")
//                    var q = dataObj.getString("q")
//                    var i = dataObj.getString("i")
//                    divisa.add(b, q, i)
//                }
//                callback.done(divisa)
//
//            },
//            Response.ErrorListener { error ->
//                Log.d(MainActivity.TAG, "WebService -> Response is: ${error.message}")
//            })
//
//        queue.add(jsonObjectRequest)
//
//    }
//
//
//    interface ICallback {
//        fun done(divisa:Divisa): Unit
//    }
