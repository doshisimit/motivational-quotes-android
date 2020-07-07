package app.simit.com.motivationalquotes.ui.Home_

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import app.simit.com.motivationalquotes.R
import app.simit.com.motivationalquotes.databinding.ActivityHomeBinding
import app.simit.com.motivationalquotes.ui.Home_.quotes.QuotesFragment
import app.simit.com.motivationalquotes.ui.Home_.saved.SavedFragment
import app.simit.com.motivationalquotes.ui.Home_.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding;
    val fragment1: QuotesFragment = QuotesFragment()
    val fragment2: Fragment = SavedFragment()
    val fragment3: Fragment = SettingsFragment()
    val fm = supportFragmentManager
    var active: Fragment = fragment1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        binding.topAppBar.setTitle("")
        setSupportActionBar(binding.topAppBar)

//        Utils.initQuotes()

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

        binding.navView.setOnNavigationItemSelectedListener {

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_option, menu)
        val item: MenuItem = menu!!.findItem(R.id.app_bar_search)
        val searchView: SearchView = item.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
//                Snackbar.make(binding.container, "Query : " + query, Snackbar.LENGTH_LONG).show()
                if (active is QuotesFragment) {
                    query?.let { fragment1.SearchQuery(it) }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
}