package com.movie.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.movie.capstone.di.FavoriteModuleDependencies
import com.movie.settings.databinding.FragmentSettingBinding
import com.movie.settings.di.DaggerSettingComponent
import com.movie.settings.di.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory

    private val settingViewModel: SettingViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerSettingComponent.builder()
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
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sbMovie.isChecked = settingViewModel.movieSetting()
        binding.sbFavorite.isChecked = settingViewModel.favoriteSetting()
        setupMovieSwitch()
        setupFavoriteSwitch()
    }

    private fun setupMovieSwitch(){
        binding.sbMovie.setOnCheckedChangeListener{ _, isChecked ->
            settingViewModel.changeMovieSetting(isChecked)
        }
    }

    private fun setupFavoriteSwitch(){
        binding.sbFavorite.setOnCheckedChangeListener{ _, isChecked ->
            settingViewModel.changeFavoriteSetting(isChecked)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
