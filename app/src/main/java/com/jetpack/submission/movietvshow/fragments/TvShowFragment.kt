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
import com.jetpack.submission.movietvshow.activity.FavoriteTvShowActivity
import com.jetpack.submission.movietvshow.adapter.ListTvShowAdapter
import com.jetpack.submission.movietvshow.core.status.Status
import com.jetpack.submission.movietvshow.databinding.FragmentTvShowBinding
import com.jetpack.submission.movietvshow.model.TvShowModel
import com.jetpack.submission.movietvshow.utils.ClickCallbackTvShow
import com.jetpack.submission.movietvshow.utils.Types
import com.jetpack.submission.movietvshow.viewModel.ListViewModel
import com.jetpack.submission.movietvshow.viewModel.ViewModelFactory

class TvShowFragment : Fragment(), ClickCallbackTvShow {
    private lateinit var viewModel: ListViewModel
    private lateinit var tvShowBinding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        tvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return tvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[ListViewModel::class.java]

            val tvShowAdapter = ListTvShowAdapter(this@TvShowFragment)
            tvShowBinding.progressbar.visibility = View.VISIBLE
            viewModel.getTvList().observe(viewLifecycleOwner, { tvShows ->
                if (tvShows != null){
                    when(tvShows.status){
                        Status.LOADING -> tvShowBinding.progressbar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            tvShowBinding.progressbar.visibility = View.INVISIBLE
                            tvShowAdapter.submitList(tvShows.data)
                            tvShowAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR ->{
                            tvShowBinding.progressbar.visibility = View.INVISIBLE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                        else -> Toast.makeText(context, "Nothing shows up", Toast.LENGTH_SHORT).show()
                    }
                }
            })

            with(tvShowBinding.rvTvShow) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
            tvShowBinding.fabFavoriteTvShow.setOnClickListener {
                startActivity(Intent(view.context, FavoriteTvShowActivity::class.java))
            }
        }
    }

    override fun onShareClickTvShow(entity: TvShowModel) {
        startActivity(
            Intent(context, DetailActivity::class.java)
            .putExtra(DetailActivity.EXTRA_DATA, entity.id)
            .putExtra(DetailActivity.EXTRA_TYPE, Types.TYPE_TV_SHOW))
    }
}