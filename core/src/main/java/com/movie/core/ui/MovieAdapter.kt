package com.movie.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.movie.core.R
import com.movie.core.databinding.ItemListMovieBinding
import com.movie.core.domain.model.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var clickListener: IClickListener? = null
    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false))

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMovieBinding.bind(itemView)
        fun bind(data: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.posterPath)
                    .into(ivItemImage)
                tvItemTitle.text = data.originalTitle
                tvItemSubtitle.text = data.originalLanguage
            }

            binding.root.setOnClickListener {
                clickListener?.onClickItem(data)
            }
        }
    }

    interface IClickListener {
        fun onClickItem(movie: Movie)
    }

    fun setClickListener(clickListener: IClickListener) {
        this.clickListener = clickListener
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.movieId == newItem.movieId

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }
}
