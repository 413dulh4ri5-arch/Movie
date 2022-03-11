package com.movie.favorite.di

import android.content.Context
import com.movie.capstone.di.FavoriteModuleDependencies
import com.movie.favorite.detail.DetailFavoriteFragment
import com.movie.favorite.FavoriteFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {

    fun inject(favoriteFragment: FavoriteFragment)

    fun inject(detailFavoriteFragment: DetailFavoriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }

}
