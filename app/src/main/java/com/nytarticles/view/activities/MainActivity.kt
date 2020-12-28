package com.nytarticles.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nytarticles.R
import com.nytarticles.view.fragments.ArticlesFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        addFragment(ArticlesFragment(), null)
    }

    fun addFragment(fragment: Fragment, args: Bundle?) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        args?.let {
            fragment.arguments = it
        }
        fragmentTransaction.replace(R.id.container, fragment, fragment::class.java.simpleName)
        fragmentTransaction.addToBackStack(fragment::class.java.simpleName)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            super.onBackPressed()
        } else {
            finish()
        }
    }

}
