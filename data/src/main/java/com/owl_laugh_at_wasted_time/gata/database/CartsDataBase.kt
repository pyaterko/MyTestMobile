package com.owl_laugh_at_wasted_time.gata.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.owl_laugh_at_wasted_time.gata.database.dao.CartItemsDao

@Database(entities = [CartItemDbModel::class], version = 1, exportSchema = false)
abstract class CartsDataBase : RoomDatabase() {
    abstract fun cartsDao(): CartItemsDao
}


