package com.owl_laugh_at_wasted_time.mytestmobile.di

import androidx.lifecycle.ViewModel
import com.owl_laugh_at_wasted_time.mytestmobile.presintation.ui.fragments.cart.CartViewModel
import com.owl_laugh_at_wasted_time.mytestmobile.presintation.ui.fragments.detail.DetailsViewModel
import com.owl_laugh_at_wasted_time.mytestmobile.presintation.ui.fragments.mainscreen.MainScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    fun bindMainScreenViewModel(viewModel: MainScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    fun bindCartViewModel(viewModel: CartViewModel): ViewModel

}