package com.jetpack.submission.movietvshow.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jetpack.submission.movietvshow.R
import com.jetpack.submission.movietvshow.databinding.ListEntityBinding
import com.jetpack.submission.movietvshow.model.MovieModel
import com.jetpack.submission.movietvshow.utils.ClickCallbackMovie
import com.jetpack.submission.movietvshow.utils.Poster

class ListMovieAdapter (private val callback: ClickCallbackMovie) : PagedListAdapter<MovieModel, ListMovieAdapter.ListViewHolder>(DIFF_CALLBACK) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_entity, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ListEntityBinding.bind(itemView)
        fun bind(list: MovieModel) {
            with(binding) {
                tvTitle.text = list.title
                list.poster.let {
                    with(Poster){
                        setImage(itemView.context, API_IMAGE + IMAGE_SIZE + it, imgPoster)
                    }
                }

                itemCons.setOnClickListener {
                    callback.onShareClickMovie(list)
                }
            }
        }
    }
}
