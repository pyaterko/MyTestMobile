package com.owl_laugh_at_wasted_time.mytestmobile.model.mapper

import com.owl_laugh_at_wasted_time.gata.entity.CartItemDbModel
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.CartItem
import javax.inject.Inject


class CartItemMapper @Inject constructor() {

    fun mapEntityToDbModel(item: CartItem) = CartItemDbModel(
        id = item.id,
        name = item.name,
        imange = item.image,
        prise = item.prise,
        priseTotal = item.priseTotal,
        amount = item.amount,
        isCart = item.isCart
    )

    fun mapDbModelToEntity(itemDbModel: CartItemDbModel) = CartItem(
        id = itemDbModel.id,
        name = itemDbModel.name,
        image = itemDbModel.imange,
        prise = itemDbModel.prise,
        priseTotal = itemDbModel.priseTotal,
        amount = itemDbModel.amount,
        isCart = itemDbModel.isCart
    )

    fun mapListDbModelToListEntity(list: List<CartItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}
