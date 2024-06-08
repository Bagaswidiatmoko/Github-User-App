package com.dicoding.githubuser.favorite

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubuser.R
import com.dicoding.githubuser.data.local.DatabaseHelper
import com.dicoding.githubuser.databinding.ActivityFavoriteBinding
import com.dicoding.githubuser.detail.DetailActivity
import com.dicoding.githubuser.ui.UserAdapter

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val adapter by lazy {
        UserAdapter { user ->
            Intent(this, DetailActivity::class.java).apply {
                putExtra("item", user)
                startActivity(this)
            }
        }
    }

    private val viewModel by viewModels<FavoriteViewModel> {
        FavoriteViewModelFactory(DatabaseHelper(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.rvFavorite.layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.adapter = adapter
    }

    override fun onResume() {
        viewModel.getUserFavorite().observe(this) {
            if(it.isEmpty()) {
                binding.favoriteEmpty.visibility = View.VISIBLE
                binding.rvFavorite.visibility = View.GONE
            } else {
                binding.favoriteEmpty.visibility = View.GONE
                binding.rvFavorite.visibility = View.VISIBLE
                adapter.setData(it)
            }
        }
        super.onResume()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}