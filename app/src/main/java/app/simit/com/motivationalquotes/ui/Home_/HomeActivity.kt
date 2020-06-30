package app.simit.com.motivationalquotes.ui.Home_

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import app.simit.com.motivationalquotes.R
import app.simit.com.motivationalquotes.ui.Home_.quotes.QuotesFragment
import app.simit.com.motivationalquotes.ui.Home_.saved.SavedFragment
import app.simit.com.motivationalquotes.ui.Home_.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    val fragment1: Fragment = QuotesFragment()
    val fragment2: Fragment = SavedFragment()
    val fragment3: Fragment = SettingsFragment()
    val fm = supportFragmentManager
    var active: Fragment = fragment1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
//        Utils.initQuotes()
        val navView = findViewById<BottomNavigationView>(R.id.nav_view)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build()
//        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
//        NavigationUI.setupWithNavController(navView, navController)

        fm.beginTransaction().add(R.id.nav_host_fragment, fragment3, "3").hide(fragment3).commit()
        fm.beginTransaction().add(R.id.nav_host_fragment, fragment2, "2").hide(fragment2).commit()
        fm.beginTransaction().add(R.id.nav_host_fragment, fragment1, "1").commit()

        navView.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.navigation_home -> {
                    fm.beginTransaction().hide(active).show(fragment1).commit()
                    active = fragment1
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    fm.beginTransaction().hide(active).show(fragment2).commit()
                    active = fragment2
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_notifications -> {
                    fm.beginTransaction().hide(active).show(fragment3).commit()
                    active = fragment3
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
                }

            }
        }

    }
}