package app.simit.com.motivationalquotes.ui.Home_

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import app.simit.com.motivationalquotes.R
import app.simit.com.motivationalquotes.databinding.ActivityHomeBinding
import app.simit.com.motivationalquotes.ui.Home_.quotes.QuotesFragment
import app.simit.com.motivationalquotes.ui.Home_.saved.SavedFragment
import app.simit.com.motivationalquotes.ui.Home_.settings.SettingsFragment
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

        binding.searchEt.setEndIconOnClickListener {
            binding.searchEt.editText!!.text.clear()
            binding.imageView2.performClick()
            hideKeyboard(this)
            if (active is QuotesFragment) {
                fragment1.defaultList()
            }
        }

        binding.searchEt.editText!!.setOnEditorActionListener { v, actionId, event ->
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                if (!binding.searchEt.editText!!.text.isEmpty()) {
                    if (active is QuotesFragment) {
                        binding.searchEt.editText!!.text.toString()?.let { fragment1.SearchQuery(it) }
                    }
                } else {
                    Snackbar.make(binding.root, "Enter a keyword to search", Snackbar.LENGTH_LONG).show()
                }
            }
            return@setOnEditorActionListener false
        }

    }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }

}