package dev.fernandoflorez.platziconf.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.fernandoflorez.platziconf.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setActionBar( findViewById(R.id.toolbarMain) )
        configNav()
    }

    private fun configNav() {
        val navigationMenu: BottomNavigationView = findViewById(R.id.navigationMenu)
        NavigationUI.setupWithNavController(navigationMenu, Navigation.findNavController(this, R.id.mainContainerView))
    }
}
