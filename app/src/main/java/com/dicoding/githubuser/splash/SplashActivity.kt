package com.dicoding.githubuser.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.dicoding.githubuser.R
import com.dicoding.githubuser.data.local.SettingPreferences
import com.dicoding.githubuser.data.local.dataStore
import com.dicoding.githubuser.databinding.ActivitySplashBinding
import com.dicoding.githubuser.ui.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val preference: SettingPreferences by lazy { SettingPreferences.getInstance(application.dataStore) }
    private val viewModel: SplashViewModel by viewModels { SplashViewModel.Factory(preference) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObserver()
    }

    private fun setupObserver() {
        viewModel.themeSetting.observe(this) {
            it.getContentIfNotHandled()?.let { isDarkMode ->
                handleTheme(isDarkMode)
                goToMainActivity()
            }
        }
    }

    private fun handleTheme(isDarkmode: Boolean) {
        val theme = if (isDarkmode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(theme)
    }

    private fun goToMainActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }, 3000L)
    }
}