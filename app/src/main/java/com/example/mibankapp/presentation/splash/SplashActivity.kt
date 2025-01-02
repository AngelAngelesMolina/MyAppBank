package com.example.mibankapp.presentation.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.mibankapp.R
import com.example.mibankapp.common.utils.navigateTo
import com.example.mibankapp.databinding.ActivitySplashBinding
import com.example.mibankapp.presentation.client_detail.HomeActivity
import com.example.mibankapp.presentation.client_detail.viewmodel.HomeViewModel
import com.example.mibankapp.presentation.client_login.LoginActivity
import com.example.mibankapp.presentation.splash.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivitySplashBinding;
    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupListener()
        setupObservers()

        mBinding.lottieAn.playAnimation()

    }

    private fun setupObservers() {
        splashViewModel.isLoggedIn.observe(this, Observer{ isLoggedIn ->
            if (isLoggedIn) {
                navigateTo(HomeActivity::class.java, finishCurrent = true)
            } else {
                navigateTo(LoginActivity::class.java, finishCurrent = true)
            }
        })
    }

    private fun setupListener() {
        mBinding.lottieAn.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                splashViewModel.checkUserLoginStatus(getString(R.string.sp_token_key))
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }
        })

    }
}