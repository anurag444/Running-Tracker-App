package com.example.runningtrackerapp

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.runningtrackerapp.other.CONSTANTS.ACTION_SHOW_TRACKING_FRAGMENT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //This is called when MainActivity is destroyed and then user clicks on noti
        // else it will call newIntent
        navigateToTrackingFragment(intent)

        setSupportActionBar(toolbar)

        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())
        bottomNavigationView.setOnNavigationItemReselectedListener { /* No Operation*/ }
        //triggered when we navigate to different fragments
        navHostFragment.findNavController().addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.settingsFragment, R.id.runFragment, R.id.statisticsFragment ->
                    bottomNavigationView.visibility = View.VISIBLE

                else -> bottomNavigationView.visibility = View.GONE
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToTrackingFragment(intent)
    }
    private fun navigateToTrackingFragment(intent: Intent?){
        if(intent?.action == ACTION_SHOW_TRACKING_FRAGMENT){
            navHostFragment.findNavController().navigate(R.id.action_global_trackingFragment)
        }
    }

    fun updateStatusBarColor(color: String?) { // Color must be in hexadecimal fromat
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(Color.parseColor(color))
    }
}