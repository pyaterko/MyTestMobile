package com.owl_laugh_at_wasted_time.mytestmobile.di

import android.content.Context
import com.owl_laugh_at_wasted_time.api.di.ApiModule
import com.owl_laugh_at_wasted_time.api.di.WorkerModule
import com.owl_laugh_at_wasted_time.gata.di.DataModule
import com.owl_laugh_at_wasted_time.mytestmobile.presentation.activity.MainActivity
import com.owl_laugh_at_wasted_time.mytestmobile.presentation.fragments.cart.CartFragment
import com.owl_laugh_at_wasted_time.mytestmobile.presentation.fragments.detail.DetailsFragment
import com.owl_laugh_at_wasted_time.mytestmobile.presentation.fragments.mainscreen.MainScreenFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules =
    [
        ViewModelModule::class,
        RepositoryModule::class,
        ApiModule::class,
        WorkerModule::class,
        DataModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance applicationContext: Context
        ): AppComponent
    }

    fun context(): Context
    fun inject(activity: MainActivity)
    fun inject(fragment: MainScreenFragment)
    fun inject(fragment: DetailsFragment)
    fun inject(fragment: CartFragment)


}