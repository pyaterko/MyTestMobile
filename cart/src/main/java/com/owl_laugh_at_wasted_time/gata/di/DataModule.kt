package com.owl_laugh_at_wasted_time.gata.di

import android.content.Context
import androidx.room.Room
import com.owl_laugh_at_wasted_time.gata.database.CartsDataBase
import com.owl_laugh_at_wasted_time.gata.dao.CartItemsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface DataModule {

    companion object {

        @Singleton
        @Provides
        fun provideDictionaryDao(
            context: Context
        ): CartItemsDao {
            return Room.databaseBuilder(
                context,
                CartsDataBase::class.java,
                "dictionarydatabase.db"
            )
                .fallbackToDestructiveMigration()
                .build()
                .cartsDao()
        }
    }
}