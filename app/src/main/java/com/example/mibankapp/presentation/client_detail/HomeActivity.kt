package com.example.mibankapp.presentation.client_detail

import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.mibankapp.R
import com.example.mibankapp.common.LoaderUtils
import com.example.mibankapp.common.utils.navigateTo
import com.example.mibankapp.databinding.ActivityHomeBinding
import com.example.mibankapp.presentation.client_detail.home.HomeFragment
import com.example.mibankapp.presentation.client_detail.support.SupportFragment
import com.example.mibankapp.presentation.client_detail.transfers.TransfersFragment
import com.example.mibankapp.presentation.client_detail.viewmodel.HomeViewModel
import com.example.mibankapp.presentation.client_login.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class HomeActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityHomeBinding
    private lateinit var drawerLayout: DrawerLayout
    private val homeViewModel: HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        drawerLayout = mBinding.drawerLayout
        ViewCompat.setOnApplyWindowInsetsListener(mBinding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        replaceFragment(HomeFragment())
        setupToolBar()
        setupNav()
    }

    private fun setupNav() {
        mBinding.bottomNavigation.setOnItemSelectedListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            when (it.itemId) {
                R.id.homeFragment -> {
                    if (currentFragment !is HomeFragment) {
                        replaceFragment(HomeFragment())
                    }
                }
                R.id.transferenciasFragment -> {
                    if (currentFragment !is TransfersFragment) {
                        replaceFragment(TransfersFragment())
                    }
                }
                R.id.soporteFragment -> {
                    if (currentFragment !is SupportFragment) {
                        replaceFragment(SupportFragment())
                    }
                }
                else -> {}
            }
            true
        }
    }

    private fun setupToolBar() {
        val navigationView: NavigationView = findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.closeSession -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    LoaderUtils.showAlert(this, getString(R.string.menu_close_session),true, getString(R.string.menu_close_session_confirmation)){
                        homeViewModel.removeUserData(getString(R.string.sp_token_key)){
                            navigateTo(LoginActivity::class.java, finishCurrent = true)
                        }
                    }
                }
                R.id.account -> {
                    mBinding.bottomNavigation.selectedItemId = R.id.transferenciasFragment
                    drawerLayout.closeDrawer(GravityCompat.START)
                }

                else -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
            }
            true
        }
        setSupportActionBar(mBinding.topbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // TODO: obtener user de sp
        supportActionBar?.title = "Bienvenido usuarioDev!"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_hamburger)
        mBinding.topbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

    }


    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(
            R.anim.slide_in_up,
            R.anim.slide_out_down,
            R.anim.slide_in_down,
            R.anim.slide_out_up
        )
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
        fragmentTransaction.commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        } else {
            super.onSupportNavigateUp()
        }
    }
}