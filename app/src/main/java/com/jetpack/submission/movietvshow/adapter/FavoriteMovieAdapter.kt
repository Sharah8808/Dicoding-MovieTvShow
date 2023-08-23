package com.jetpack.submission.movietvshow.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jetpack.submission.movietvshow.R
import com.jetpack.submission.movietvshow.databinding.ListFavoriteBinding
import com.jetpack.submission.movietvshow.model.MovieModel
import com.jetpack.submission.movietvshow.utils.ClickCallbackMovie
import com.jetpack.submission.movietvshow.utils.Poster

class FavoriteMovieAdapter(private val callback: ClickCallbackMovie) : PagedListAdapter<MovieModel, FavoriteMovieAdapter.FavoriteViewHolder>(DIFF_CALLBACK) {

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = ListFavoriteBinding.bind(itemView)

        fun bind(movie : MovieModel){
            with(binding){
                tvTitleFav.text = movie.title
                movie.poster.let { poster ->
                    with(Poster){
                        setImage(itemView.context, API_IMAGE + IMAGE_SIZE + poster, imgPoster)
                    }
                }

                itemCons.setOnClickListener {
                    callback.onShareClickMovie(movie)
                }
            }
        }
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieModel>(){
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteMovieAdapter.FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_favorite, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteMovieAdapter.FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }
}