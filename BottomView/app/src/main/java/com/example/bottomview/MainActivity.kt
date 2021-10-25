package com.example.bottomview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.account_icon_layout.*
import kotlinx.android.synthetic.main.contact_icon_layout.*
import kotlinx.android.synthetic.main.explore_icon_layout.*
import kotlinx.android.synthetic.main.message_icon_layout.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val destinationMap = mapOf(
            R.id.messageFragment to message_motion_layout,
            R.id.contactFragment to contact_motion_layout,
            R.id.exploreFragment to explore_motion_layout,
            R.id.accountFragment to account_motion_layout,
        )

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController, AppBarConfiguration(destinationMap.keys))

        destinationMap.forEach { map->
            map.value.setOnClickListener {
                navController.navigate(map.key)
            }
        }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            controller.popBackStack()

            destinationMap.values.forEach {
                it.progress = 0f
            }

            destinationMap.get(destination.id)?.transitionToEnd()


        }


    }
}