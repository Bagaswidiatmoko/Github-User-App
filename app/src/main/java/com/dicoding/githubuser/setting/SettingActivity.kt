package com.dicoding.githubuser.setting

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.CompoundButton
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubuser.R
import com.dicoding.githubuser.data.local.SettingPreferences
import com.dicoding.githubuser.data.local.dataStore
import com.dicoding.githubuser.databinding.ActivitySettingBinding
import com.dicoding.githubuser.detail.DetailActivity
import com.dicoding.githubuser.favorite.FavoriteActivity
import com.dicoding.githubuser.ui.MainActivity
import com.dicoding.githubuser.ui.UserAdapter

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding
    private val preferences: SettingPreferences by lazy { SettingPreferences.getInstance(application.dataStore) }
    private val viewModel: SettingViewModel by viewModels { SettingViewModel.Factory(preferences) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.getThemeSettings().observe(this@SettingActivity) { isDarkModeActive: Boolean ->
            if(isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode((AppCompatDelegate.MODE_NIGHT_YES))
                binding.switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode((AppCompatDelegate.MODE_NIGHT_NO))
                binding.switchTheme.isChecked = false
            }

        }

        binding.switchTheme.setOnCheckedChangeListener {_, isChecked ->
            viewModel.saveThemeSetting(isChecked)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}