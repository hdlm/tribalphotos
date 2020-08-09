package com.tribal.tribalphotos

import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var txtMessage: TextView
    private var mOnNavigationItemSelectedLister = BottomNavigationView.OnNavigationItemSelectedListener {
        return@OnNavigationItemSelectedListener when(it.itemId) {
            R.id.navigation_home -> {
                txtMessage.setText("Home")
                true
            }
            R.id.navigation_photos -> {
                txtMessage.setText("See Photos")
                true
            }
            R.id.navigation_favorite -> {
                txtMessage.setText("Favorites")
                true
            }
            R.id.navigation_profile -> {
                txtMessage.setText("Profile")
                true
            }
            else -> false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtMessage = findViewById<TextView>(R.id.message)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedLister)


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
}