package com.movie.capstone.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.movie.capstone.R
import com.movie.capstone.common.navigateSafe
import com.movie.capstone.databinding.FragmentHomeBinding
import com.movie.core.data.Resource
import com.movie.core.ui.MovieAdapter
import com.jakewharton.rxbinding2.widget.RxSearchView
import com.movie.core.domain.model.Movie
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()
    private val disposables = CompositeDisposable()
    private var movieAdapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter()
        val layoutManager = LinearLayoutManager(context)
        binding.rvMovie.apply {
            this.layoutManager = layoutManager
            this.adapter = movieAdapter
        }

        movieAdapter?.setClickListener(object : MovieAdapter.IClickListener {
            override fun onClickItem(movie: Movie) {
                val direction = HomeFragmentDirections.toDetailMovieFragment(
                    movie = movie
                )
                findNavController().navigateSafe(direction)
            }
        })

        fetchData()
    }

    private fun fetchData() {
        homeViewModel.movie().observe(viewLifecycleOwner) {
            if (it != null) when (it) {
                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    movieAdapter?.differ?.submitList(it.data)
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.viewError.root.visibility = View.VISIBLE
                    binding.viewError.tvError.text =
                        it.message ?: getString(R.string.something_wrong)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_item, menu)
        super.onCreateOptionsMenu(menu, inflater)
        val item = menu.findItem(R.id.search)
        val searchView = item?.actionView as SearchView
        setupSearch(searchView)
    }

    private fun setupSearch(item: SearchView) {
        val disposable = RxSearchView.queryTextChanges(item)
            .skip(1)
            .map { it.toString() }
            .throttleLast(100, TimeUnit.MILLISECONDS)
            .debounce(100, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { searchText: String? ->
                filterData(searchText)
            }
        disposables.add(disposable)

        item.setOnCloseListener {
            false
        }
    }

    private fun filterData(query: String? = null) {
        homeViewModel.searchMovie(query ?: "").observe(viewLifecycleOwner) {
            movieAdapter?.differ?.submitList(it)
            if (it.isNotEmpty()){
                binding.viewError.root.visibility = View.GONE
                binding.viewError.tvError.visibility = View.GONE
            }else{
                binding.viewError.root.visibility = View.VISIBLE
                binding.viewError.tvError.visibility = View.VISIBLE
                binding.viewError.tvError.text = getString(R.string.title_empty_search_data)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_favorite -> {
                val direction = HomeFragmentDirections.toFavoriteFragment()
                findNavController().navigateSafe(direction)
                true
            }
            R.id.menu_setting ->{
                val direction = HomeFragmentDirections.toSettingFragment()
                findNavController().navigateSafe(direction)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        movieAdapter = null
        disposables.clear()
        binding.rvMovie.adapter = null
        _binding = null
    }
}
