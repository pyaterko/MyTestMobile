package com.owl_laugh_at_wasted_time.mytestmobile.data

import com.owl_laugh_at_wasted_time.mytestmobile.R
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.CategoryItem
import javax.inject.Inject

class Categorys @Inject constructor() {

    fun updateCategory(categoryItem: CategoryItem): List<CategoryItem> {
        val categoryItems = arrayListOf(
            CategoryItem(
                0,
                R.drawable.circle,
                R.drawable.circle_white,
                R.drawable.ic_phone,
                R.drawable.ic_phone_white,
                "Phones",
                false
            ),
            CategoryItem(
                1,
                R.drawable.circle,
                R.drawable.circle_white,
                R.drawable.ic_computer,
                R.drawable.ic_computer_white,
                "Computer",
                false
            ),
            CategoryItem(
                2,
                R.drawable.circle,
                R.drawable.circle_white,
                R.drawable.ic_heath,
                R.drawable.ic_heath_white,
                "Health",
                false
            ),
            CategoryItem(
                3,
                R.drawable.circle,
                R.drawable.circle_white,
                R.drawable.ic_books,
                R.drawable.ic_books_white,
                "Books",
                false
            ),
            CategoryItem(
                4,
                R.drawable.circle,
                R.drawable.circle_white,
                R.drawable.ic_books,
                R.drawable.ic_books_white,
                "",
                false
            )
        )
        val index = categoryItem.id
        if (index != -1) {
            categoryItems[index] = categoryItem.copy(state = true)
        }
        return categoryItems
    }
}