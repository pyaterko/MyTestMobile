package com.owl_laugh_at_wasted_time.mytestmobile.di

import com.owl_laugh_at_wasted_time.api.DefaultApiWorker
import com.owl_laugh_at_wasted_time.api.MobileApiWorker
import com.owl_laugh_at_wasted_time.gata.domain.repository.CartItemsRepository
import com.owl_laugh_at_wasted_time.mytestmobile.data.CartItemsRepositoryImpl
import com.owl_laugh_at_wasted_time.mytestmobile.data.DataSource
import com.owl_laugh_at_wasted_time.mytestmobile.domain.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindRemoteRepository(impl: DataSource): RemoteRepository

    @Singleton
    @Binds
    fun bindCartItemsRepository(impl: CartItemsRepositoryImpl): CartItemsRepository

}