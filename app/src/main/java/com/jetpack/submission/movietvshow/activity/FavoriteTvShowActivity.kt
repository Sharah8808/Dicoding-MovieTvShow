package com.jetpack.submission.movietvshow.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.jetpack.submission.movietvshow.adapter.FavoriteTvShowAdapter
import com.jetpack.submission.movietvshow.databinding.ActivityFavoriteTvShowBinding
import com.jetpack.submission.movietvshow.model.TvShowModel
import com.jetpack.submission.movietvshow.utils.ClickCallbackTvShow
import com.jetpack.submission.movietvshow.utils.Types
import com.jetpack.submission.movietvshow.viewModel.FavoriteTvShowVM
import com.jetpack.submission.movietvshow.viewModel.ViewModelFactory

class FavoriteTvShowActivity : AppCompatActivity(), ClickCallbackTvShow {
    private lateinit var binding : ActivityFavoriteTvShowBinding
    private lateinit var adapter: FavoriteTvShowAdapter
    private lateinit var viewModel: FavoriteTvShowVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[FavoriteTvShowVM::class.java]

        adapter = FavoriteTvShowAdapter(this@FavoriteTvShowActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getFavorite()
    }

    private fun getFavorite(){
        viewModel.getFavoriteTvSHow().observe(this, {tvShow ->
            adapter.submitList(tvShow)
            adapter.notifyDataSetChanged()
        })

        with(binding){
            rvFavTvShow.layoutManager = GridLayoutManager(this@FavoriteTvShowActivity, 2)
            rvFavTvShow.setHasFixedSize(true)
            rvFavTvShow.adapter = adapter
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onShareClickTvShow(entity: TvShowModel) {
        startActivity(
            Intent(this, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_DATA, entity.id)
                .putExtra(DetailActivity.EXTRA_TYPE, Types.TYPE_TV_SHOW))
    }
}