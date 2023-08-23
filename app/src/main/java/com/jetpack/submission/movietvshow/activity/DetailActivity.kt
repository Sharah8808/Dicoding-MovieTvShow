package com.jetpack.submission.movietvshow.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.jetpack.submission.movietvshow.R
import com.jetpack.submission.movietvshow.core.status.Status
import com.jetpack.submission.movietvshow.databinding.ActivityDetailBinding
import com.jetpack.submission.movietvshow.model.MovieModel
import com.jetpack.submission.movietvshow.model.TvShowModel
import com.jetpack.submission.movietvshow.utils.Poster
import com.jetpack.submission.movietvshow.utils.Types.TYPE_MOVIE
import com.jetpack.submission.movietvshow.viewModel.DetailViewModel
import com.jetpack.submission.movietvshow.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        intentProcess()
        detailLoading()
    }

    private fun intentProcess(){
        val extras = intent.extras
        if (extras != null) {
            val entityID = extras.getInt(EXTRA_DATA)
            val type = extras.getInt(EXTRA_TYPE)

            if (type == TYPE_MOVIE) {
                viewModel.getDetailMovie(entityID)
                setFavorite(type)                                       //type movie = 1
                viewModel.getMovie().observe(this, { movies ->
                    if (movies != null){
                        when(movies.status){
                            Status.LOADING -> detailLoading()
                            Status.SUCCESS -> {
                                if (movies.data != null){
                                    showDetail()
                                    populateCourseMovie(movies.data)
                                }
                            }
                            Status.ERROR -> {
                                showDetail()
                                Toast.makeText(applicationContext, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                            }
                            else -> Toast.makeText(applicationContext, "Nothing shows up", Toast.LENGTH_SHORT).show()
                        }
                    }

                })
                detailBinding.favFloatButton.setOnClickListener {
                    viewModel.setFavoriteMovie()
                }
            } else {
                viewModel.getDetailTv(entityID)
                setFavorite(type)                                       //type tv show = 2
                viewModel.getTvShow().observe(this, { tvShows ->
                    if (tvShows != null){
                        when(tvShows.status){
                            Status.LOADING -> detailLoading()
                            Status.SUCCESS -> {
                                if (tvShows.data != null){
                                    showDetail()
                                    populateCourseTvShow(tvShows.data)
                                }
                            }
                            Status.ERROR -> {
                                showDetail()
                                Toast.makeText(applicationContext, "Terjad Kesalahan", Toast.LENGTH_SHORT).show()
                            }
                            else -> Toast.makeText(applicationContext, "Nothing shows up", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
                detailBinding.favFloatButton.setOnClickListener {
                    viewModel.setFavoriteTvShow()
                }
            }
        }
    }

    private fun detailLoading(){
        with(detailBinding){
            tvTitle.visibility = View.GONE
            tvRelease.visibility = View.GONE
            tvGenre.visibility = View.GONE
            tvRating.visibility = View.GONE
            tvLanguage.visibility = View.GONE
            tvSynopsis.visibility = View.GONE
            favFloatButton.visibility = View.GONE
            imgMainPoster.visibility = View.GONE
            imgBgPoster.visibility = View.GONE
        }
    }

    private fun showDetail(){
        with(detailBinding){
            tvTitle.visibility = View.VISIBLE
            tvRelease.visibility = View.VISIBLE
            tvGenre.visibility = View.VISIBLE
            tvRating.visibility = View.VISIBLE
            tvLanguage.visibility = View.VISIBLE
            tvSynopsis.visibility = View.VISIBLE
            favFloatButton.visibility = View.VISIBLE
            imgMainPoster.visibility = View.VISIBLE
            imgBgPoster.visibility = View.VISIBLE
        }
    }

    private fun populateCourseMovie(entity: MovieModel) {
        detailBinding.apply {
            tvTitle.text = entity.title
            tvGenre.text = entity.genre
            tvRelease.text = entity.release_date
            tvLanguage.text = entity.language
            tvSynopsis.text = entity.synopsis
            tvRating.text = entity.rating.toString()
        }

       entity.poster.let {
           with(Poster){
               setImage(this@DetailActivity, API_IMAGE + IMAGE_SIZE + it, img_bg_poster)
           }
       }

        entity.poster.let {
            with(Poster){
                setImage(this@DetailActivity, API_IMAGE + IMAGE_SIZE + it, img_main_poster)
            }
        }
    }

    private fun populateCourseTvShow(entity: TvShowModel) {
        detailBinding.apply {
            tvTitle.text = entity.title
            tvGenre.text = entity.genre
            tvRelease.text = entity.release_date
            tvLanguage.text = entity.language
            tvSynopsis.text = entity.synopsis
            tvRating.text = entity.rating.toString()
        }

        entity.poster.let {
            with(Poster){
                setImage(this@DetailActivity, API_IMAGE + IMAGE_SIZE + it, img_bg_poster)
            }
        }

        entity.poster.let {
            with(Poster){
                setImage(this@DetailActivity, API_IMAGE + IMAGE_SIZE + it, img_main_poster)
            }
        }
    }

    private fun setFavoriteButton(state : Boolean){
        if (state){
            detailBinding.favFloatButton.setImageResource(R.drawable.ic_baseline_favorite_red_24)
        } else {
            detailBinding.favFloatButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun setFavorite(type : Int){
        if(type == 1){
            viewModel.getMovie().observe(this, { movie ->
                if (movie != null){
                    when(movie.status){
                        Status.LOADING -> detailLoading()
                        Status.SUCCESS -> {
                            showDetail()
                            setFavoriteButton(movie.data?.favorite!!)
                        }
                        Status.ERROR -> {
                            showDetail()
                            Toast.makeText(this, "Terjadi kesalahan type 1", Toast.LENGTH_SHORT).show()
                        }
                        else -> Toast.makeText(applicationContext, "Nothing shows up", Toast.LENGTH_SHORT).show()

                    }

                }
            })
        } else if(type == 2){
            viewModel.getTvShow().observe(this, { tvShow ->
                when(tvShow.status){
                    Status.LOADING -> detailLoading()
                    Status.SUCCESS -> {
                        showDetail()
                        setFavoriteButton(tvShow.data?.favorite!!)
                    }
                    Status.ERROR -> {
                        showDetail()
                        Toast.makeText(this, "Terjadi kesalahan type 2", Toast.LENGTH_SHORT).show()
                    }
                    else -> Toast.makeText(applicationContext, "Nothing shows up", Toast.LENGTH_SHORT).show()
                }
            })
        }
        else{
            Toast.makeText(this, "Terjadi kesalahan none type found", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }
}