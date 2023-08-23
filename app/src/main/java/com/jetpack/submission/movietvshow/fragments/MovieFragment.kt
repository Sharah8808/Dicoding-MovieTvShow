package com.jetpack.submission.movietvshow.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.jetpack.submission.movietvshow.activity.DetailActivity
import com.jetpack.submission.movietvshow.activity.FavoriteMovieActivity
import com.jetpack.submission.movietvshow.adapter.ListMovieAdapter
import com.jetpack.submission.movietvshow.core.status.Status
import com.jetpack.submission.movietvshow.databinding.FragmentMovieBinding
import com.jetpack.submission.movietvshow.model.MovieModel
import com.jetpack.submission.movietvshow.utils.ClickCallbackMovie
import com.jetpack.submission.movietvshow.utils.Types.TYPE_MOVIE
import com.jetpack.submission.movietvshow.viewModel.ListViewModel
import com.jetpack.submission.movietvshow.viewModel.ViewModelFactory

class MovieFragment : Fragment(), ClickCallbackMovie {
    private lateinit var viewModel: ListViewModel
    private lateinit var movieBinding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        movieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)

        return movieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[ListViewModel::class.java]


            val movieAdapter = ListMovieAdapter(this@MovieFragment)
            movieBinding.progressbar.visibility = View.VISIBLE
            viewModel.getMovieList().observe(viewLifecycleOwner, { movies ->
                if (movies != null){
                    when(movies.status){
                        Status.LOADING -> movieBinding.progressbar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            movieBinding.progressbar.visibility = View.INVISIBLE
                            movieAdapter.submitList(movies.data)
                            movieAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            movieBinding.progressbar.visibility = View.INVISIBLE
                            Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                        }
                        else -> Toast.makeText(context, "Nothing shows up", Toast.LENGTH_SHORT).show()
                    }
                }
            })

            with(movieBinding.rvMovie) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                this.adapter = movieAdapter
            }
            movieBinding.fabFavoriteMovie.setOnClickListener {
                startActivity(Intent(view.context, FavoriteMovieActivity::class.java))
            }
        }
    }

    override fun onShareClickMovie(entity: MovieModel) {
        startActivity(Intent(context, DetailActivity::class.java)
            .putExtra(DetailActivity.EXTRA_DATA, entity.id)
            .putExtra(DetailActivity.EXTRA_TYPE, TYPE_MOVIE))
    }

}