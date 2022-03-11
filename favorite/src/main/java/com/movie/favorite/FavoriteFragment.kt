package com.movie.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.movie.capstone.common.navigateSafe
import com.movie.capstone.di.FavoriteModuleDependencies
import com.movie.core.domain.model.Movie
import com.movie.core.ui.MovieAdapter
import com.movie.favorite.databinding.FragmentFavoriteBinding
import com.movie.favorite.di.DaggerFavoriteComponent
import com.movie.favorite.di.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

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
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieAdapter = MovieAdapter()
        val layoutManager = LinearLayoutManager(context)
        binding.rvMovieFavorite.apply {
            this.layoutManager = layoutManager
            this.adapter = movieAdapter
        }

        movieAdapter.setClickListener(object : MovieAdapter.IClickListener {
            override fun onClickItem(movie: Movie) {
                val direction = FavoriteFragmentDirections.toDetailFavoriteFragment(
                    movie = movie
                )
                findNavController().navigateSafe(direction)
            }
        })

        favoriteViewModel.favorite().observe(viewLifecycleOwner) {
            movieAdapter.differ.submitList(it)
            binding.tvEmpty.visibility =
                if (it.isNotEmpty()) View.GONE else View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvMovieFavorite.adapter = null
        _binding = null
    }
}
