package com.owl_laugh_at_wasted_time.mytestmobile.model

import androidx.lifecycle.Transformations
import com.owl_laugh_at_wasted_time.gata.dao.CartItemsDao
import com.owl_laugh_at_wasted_time.mytestmobile.model.mapper.CartItemMapper
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.CartItem
import com.owl_laugh_at_wasted_time.mytestmobile.domain.repository.CartItemsRepository
import javax.inject.Inject

class CartItemsRepositoryImpl @Inject constructor(
    private val dao: CartItemsDao,
    private val mapper: CartItemMapper
) : CartItemsRepository {

    override fun getAllData() = Transformations.map(
        dao.getAllData()
    ) {
        mapper.mapListDbModelToListEntity(it)
    }

    override suspend fun add(cartItem: CartItem) {
        dao.add(mapper.mapEntityToDbModel(cartItem))
    }

    override suspend fun delete(itemId: String) {
        dao.delete(itemId)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override suspend fun getItemById(itemId: String): CartItem {
        val item = dao.getItemById(itemId)
        return mapper.mapDbModelToEntity(item)
    }
}