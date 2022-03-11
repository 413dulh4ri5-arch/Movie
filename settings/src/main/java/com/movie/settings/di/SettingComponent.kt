package com.movie.settings.di

import android.content.Context
import com.movie.capstone.di.FavoriteModuleDependencies
import com.movie.settings.SettingFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface SettingComponent {

    fun inject(settingFragment: SettingFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): SettingComponent
    }

}
