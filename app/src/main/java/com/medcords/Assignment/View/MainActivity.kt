package com.medcords.Assignment.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kotlin.assigmnet.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide()
        title=resources.getString(R.string.favorites)
        loadFragment(HomeFragment())

        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_fav-> {
                    title=resources.getString(R.string.favorites)
                    loadFragment(FavoriteFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_home-> {
                    title=resources.getString(R.string.home)
                    loadFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_settings-> {
                    title=resources.getString(R.string.settings)
                    loadFragment(SettingsFragment())
                    return@setOnNavigationItemSelectedListener true
                }


            }
            false

        }
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
