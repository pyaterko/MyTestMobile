package com.owl_laugh_at_wasted_time.gata.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.owl_laugh_at_wasted_time.gata.entity.CartItemDbModel


@Dao
interface CartItemsDao {

    @Query("SELECT * FROM cart_table")
    fun getAllData(): LiveData<List<CartItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(itemDbModel: CartItemDbModel)

    @Query("DELETE FROM cart_table WHERE id=:itemId")
    suspend fun delete(itemId: String)

    @Query("DELETE FROM cart_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM cart_table WHERE id=:itemId LIMIT 1")
    suspend fun getItemById(itemId: String): CartItemDbModel

}