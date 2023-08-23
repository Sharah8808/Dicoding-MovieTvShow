package com.jetpack.submission.movietvshow.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.jetpack.submission.movietvshow.adapter.FavoriteMovieAdapter
import com.jetpack.submission.movietvshow.databinding.ActivityFavoriteMovieBinding
import com.jetpack.submission.movietvshow.model.MovieModel
import com.jetpack.submission.movietvshow.utils.ClickCallbackMovie
import com.jetpack.submission.movietvshow.utils.Types
import com.jetpack.submission.movietvshow.viewModel.FavoriteMovieVM
import com.jetpack.submission.movietvshow.viewModel.ViewModelFactory

class FavoriteMovieActivity : AppCompatActivity(), ClickCallbackMovie {
    private lateinit var binding : ActivityFavoriteMovieBinding
    private lateinit var adapter : FavoriteMovieAdapter
    private lateinit var viewModel: FavoriteMovieVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[FavoriteMovieVM::class.java]

        adapter = FavoriteMovieAdapter(this@FavoriteMovieActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getFavorite()

    }

    private fun getFavorite(){
        viewModel.getFavoriteMovie().observe(this, { movies ->
            adapter.submitList(movies)
            adapter.notifyDataSetChanged()
        })

        with(binding){
            rvFavMovie.layoutManager = GridLayoutManager(this@FavoriteMovieActivity, 2)
            rvFavMovie.setHasFixedSize(true)
            rvFavMovie.adapter = adapter
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onShareClickMovie(entity: MovieModel) {
        startActivity(
            Intent(this, DetailActivity::class.java)
            .putExtra(DetailActivity.EXTRA_DATA, entity.id)
            .putExtra(DetailActivity.EXTRA_TYPE, Types.TYPE_MOVIE))
    }
}