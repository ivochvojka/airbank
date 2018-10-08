package com.emu.android.ui

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.TextView
import cz.airbank.airbankapplication.R
import dagger.android.support.DaggerAppCompatActivity

/**
 * Base class for activities.
 *
 * @author Ivo Chvojka
 */

abstract class BaseActivity : DaggerAppCompatActivity() {
    abstract val layoutRes: Int // Set to -1 if set content view manually

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (layoutRes > 0) setContentView(layoutRes)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            else -> return false
        }
        return true
    }

    protected fun setupFragment(fragment: Fragment) {
        val manager = supportFragmentManager
        val current = manager.findFragmentById(R.id.container)
        if (current == null) {
            manager.beginTransaction().replace(R.id.container, fragment).commit()
        }
    }

    protected open fun setupActionBar(@StringRes titleResId: Int, displayHomeAsUpEnabled: Boolean) {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar?.let {
            setSupportActionBar(it)
            supportActionBar?.run {
                setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)
                setDisplayShowTitleEnabled(false)
            }
        }
        findViewById<TextView>(R.id.txt_toolbar_title).text = getString(titleResId)
    }

}