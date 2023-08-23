package com.jetpack.submission.movietvshow.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jetpack.submission.movietvshow.R
import com.jetpack.submission.movietvshow.databinding.ListFavoriteBinding
import com.jetpack.submission.movietvshow.model.TvShowModel
import com.jetpack.submission.movietvshow.utils.ClickCallbackTvShow
import com.jetpack.submission.movietvshow.utils.Poster

class FavoriteTvShowAdapter(private val callback: ClickCallbackTvShow) : PagedListAdapter<TvShowModel, FavoriteTvShowAdapter.FavoriteViewHolder>(DIFF_CALLBACK) {
    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowModel>(){
            override fun areItemsTheSame(oldItem: TvShowModel, newItem: TvShowModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowModel, newItem: TvShowModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val binding = ListFavoriteBinding.bind(itemView)
        fun bind(tvshow : TvShowModel){
            with(binding){
                tvTitleFav.text = tvshow.title
                tvshow.poster.let { poster ->
                    with(Poster){
                        setImage(itemView.context, API_IMAGE + IMAGE_SIZE + poster, imgPoster)
                    }
                }

                itemCons.setOnClickListener {
                    callback.onShareClickTvShow(tvshow)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteTvShowAdapter.FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_favorite, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteTvShowAdapter.FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }
}