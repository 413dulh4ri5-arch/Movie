package com.movie.capstone.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.movie.capstone.R
import com.movie.capstone.databinding.DetailMovieFragmentBinding
import com.movie.core.domain.model.Movie
import com.movie.core.helper.DateHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {

    private var _binding: DetailMovieFragmentBinding? = null
    private val binding get() = _binding!!

    private val detailMovieViewModel: DetailMovieViewModel by viewModels()

    private val args by navArgs<DetailMovieFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailMovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showDetailMovie(args.movie)
    }

    private fun showDetailMovie(movie: Movie?) {
        movie?.let {
            binding.tvDetailTitle.text = it.originalTitle
            binding.tvDetailReleaseDate.text = DateHelper.convertDate(it.releaseDate)
            binding.tvDetailOverview.text = it.overview
            Glide.with(this@DetailMovieFragment)
                .load(it.posterPath)
                .into(binding.ivDetailImage)

            var statusFavorite = it.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener { _ ->
                statusFavorite = !statusFavorite
                detailMovieViewModel.setFavoriteMovie(it, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(context?.let { ContextCompat.getDrawable(it, R.drawable.ic_favorite_white) })
        } else {
            binding.fab.setImageDrawable(context?.let { ContextCompat.getDrawable(it, R.drawable.ic_not_favorite_white) })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
