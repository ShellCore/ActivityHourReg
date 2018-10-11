package com.shell.android.registropraxis.ui.main.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import com.shell.android.registropraxis.R
import com.shell.android.registropraxis.ui.home.ui.HomeFragment
import com.shell.android.registropraxis.ui.userdata.ui.UserDataFragment
import com.shell.android.shellcorebaselibrary.CustomToolbarActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : CustomToolbarActivity(), View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadToolbar(toolbarMain as Toolbar)
        setNavDrawer()
        fragmentTransaction(HomeFragment())
        navView.menu.getItem(0).isChecked = true
    }

    override fun onClick(p0: View?) {
        startActivity(Intent(this, UserDataFragment::class.java))
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.navHome -> fragmentTransaction(HomeFragment())
            R.id.navUserData -> fragmentTransaction(UserDataFragment())
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun setNavDrawer() {
        val toggle = ActionBarDrawerToggle(this, drawerLayout, _toolbar, R.string.open_drawer, R.string.close_drawer)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }

    private fun fragmentTransaction(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }
}
