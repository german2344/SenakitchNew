package com.example.senakitchnew

import android.content.Intent
import android.os.Bundle
import android.view.Menu

import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.senakitchnew.ImportClasses.popupalert
import com.example.senakitchnew.databinding.ActivityLoginBinding
import com.example.senakitchnew.databinding.ActivityMenuBinding

class menu_activity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMenu.toolbar)



        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_menu)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.edit, R.id.nav_slideshow1, R.id.Comentario, R.id.Descargar
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.salir -> {
                    // Navigate to the login destination
                    val loginIntent = Intent(this, activity_login::class.java)
                    startActivity(loginIntent)
                    true
                }

                else -> {
                    // Handle other menu item clicks
                    if (menuItem.itemId != navController.currentDestination?.id) {
                        // Avoid unnecessary navigation if already on the selected destination
                        navController.navigate(menuItem.itemId)
                    }
                    // Close the drawer after selecting an item
                    drawerLayout.closeDrawers()
                    true
                }
            }
        }
    }


        override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_activity, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_menu)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }





}