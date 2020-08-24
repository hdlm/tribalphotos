package com.tribal.tribalphotos

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.tribal.tribalphotos.repository.FavoriteRoomDatabase
import com.tribal.tribalphotos.ui.favorite.PhotoFavoriteFragment
import com.tribal.tribalphotos.ui.favorite.PhotoFavoriteProfileFragment
import com.tribal.tribalphotos.ui.home.HomeFragment
import com.tribal.tribalphotos.ui.photo.PhotoGalleryFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity() {

    private var fragmentPhotoGallery : PhotoGalleryFragment? = null
    private var fragmentPhotoFavorite: PhotoFavoriteFragment? = null
    private var fragmentUserProfile: PhotoFavoriteProfileFragment? = null

    private var mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        return@OnNavigationItemSelectedListener when(it.itemId) {
            R.id.navigation_home -> {
                fragmentPhotoGallery = PhotoGalleryFragment()
                replaceFragment(HomeFragment())
                true
            }
            R.id.navigation_photos -> {
                fragmentPhotoGallery ?: run {
                    fragmentPhotoGallery = PhotoGalleryFragment()
                }
                replaceFragment(fragmentPhotoGallery!!)
                true
            }
            R.id.navigation_favorite -> {
                fragmentPhotoFavorite ?: run {
                    fragmentPhotoFavorite = PhotoFavoriteFragment()
                }
                replaceFragment(fragmentPhotoFavorite!!)
                true
            }
            R.id.navigation_profile -> {
                fragmentUserProfile ?: run {
                    fragmentUserProfile = PhotoFavoriteProfileFragment()
                }
                replaceFragment(fragmentUserProfile!!)
                true
            }
            else -> false
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(HomeFragment())
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


//        val navView: BottomNavigationView = findViewById(R.id.nav_view)
//
//        val navController = findNavController(R.id.nav_host_fragment)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
    }

    private fun showPhotoGallery() {

    }

    private fun replaceFragment(fragment: Fragment) {
        Log.d(TAG, "${this.javaClass.simpleName} -> replaceFragment() invoked to ${fragment.javaClass.simpleName }")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentHolder, fragment)
            .commit()
    }

    fun gotoUserProfileFragment(): Unit {
        fragmentUserProfile ?: run {
            fragmentUserProfile = PhotoFavoriteProfileFragment()
        }
        replaceFragment(fragmentUserProfile!!)
    }

    companion object {
        val TAG = "TAGTAG"
        var INSTANCE : MainActivity? = null

        internal fun getInstance() : MainActivity {
            val tempInstance = INSTANCE
            if ( tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = MainActivity()
                INSTANCE = instance
                return instance
            }
        }

    }

}