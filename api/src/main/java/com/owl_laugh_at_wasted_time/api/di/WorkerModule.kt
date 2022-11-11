package com.owl_laugh_at_wasted_time.api.di

import com.owl_laugh_at_wasted_time.api.DefaultApiWorker
import com.owl_laugh_at_wasted_time.api.MobileApiWorker
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface WorkerModule {

    @Singleton
    @Binds
    fun bindWorker(impl: DefaultApiWorker): MobileApiWorker

}