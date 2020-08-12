package com.tribal.tribalphotos

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tribal.tribalphotos.ui.photo.PhotoGalleryFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "TAGTAG"
    }

    private lateinit var txtMessage: TextView
    private var mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        return@OnNavigationItemSelectedListener when(it.itemId) {
            R.id.navigation_home -> {
//                replaceFragment(HomeFragment.newInstance())
                true
            }
            R.id.navigation_photos -> {
                replaceFragment(PhotoGalleryFragment.newInstance())
                true
            }
            R.id.navigation_favorite -> {
//                replaceFragment(PhotoGalleryFragment.newInstance())
                true
            }
            R.id.navigation_profile -> {
//                replaceFragment(PhotoGalleryFragment.newInstance())
                true
            }
            else -> false
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(PhotoGalleryFragment.newInstance())
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        txtMessage = findViewById<TextView>(R.id.message)
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

    private fun replaceFragment(fragment: Fragment) {
        Log.d(TAG, "${this.javaClass.simpleName} -> replaceFragment() invoked to ${fragment.javaClass.simpleName }")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentHolder, fragment)
            .commit()
    }

}