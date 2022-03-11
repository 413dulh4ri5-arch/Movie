package com.movie.favorite.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.movie.capstone.R
import com.movie.capstone.detail.DetailMovieFragmentArgs
import com.movie.capstone.di.FavoriteModuleDependencies
import com.movie.core.domain.model.Movie
import com.movie.core.helper.DateHelper
import com.movie.favorite.databinding.FragmentDetailFavoriteBinding
import com.movie.favorite.di.DaggerFavoriteComponent
import com.movie.favorite.di.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class DetailFavoriteFragment : Fragment() {

    private var _binding: FragmentDetailFavoriteBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory

    private val detailFavoriteViewModel: DetailFavoriteViewModel by viewModels {
        factory
    }

    private val args by navArgs<DetailMovieFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerFavoriteComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailFavoriteBinding.inflate(inflater, container, false)
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
            Glide.with(this@DetailFavoriteFragment)
                .load(it.posterPath)
                .into(binding.ivDetailImage)

            var statusFavorite = it.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener { _ ->
                statusFavorite = !statusFavorite
                detailFavoriteViewModel.setFavoriteMovie(it, statusFavorite)
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

