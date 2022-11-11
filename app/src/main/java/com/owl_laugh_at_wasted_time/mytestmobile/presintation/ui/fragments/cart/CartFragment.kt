package com.owl_laugh_at_wasted_time.mytestmobile.presintation.ui.fragments.cart

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.owl_laugh_at_wasted_time.gata.domain.entity.CartItem
import com.owl_laugh_at_wasted_time.mytestmobile.R
import com.owl_laugh_at_wasted_time.mytestmobile.databinding.FragmentCartBinding
import com.owl_laugh_at_wasted_time.mytestmobile.presintation.base.BaseFragment
import com.owl_laugh_at_wasted_time.mytestmobile.presintation.base.viewBinding

class CartFragment : BaseFragment(R.layout.fragment_cart) {

    private val binding by viewBinding(FragmentCartBinding::bind)
    private val viewModel by viewModels<CartViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        numberOfItemsInTheCart = getNumberOfItemsInTheCart()
        val adapter = CartRVAdapter()
        binding.rvCart.adapter = adapter
        setOnClickListeners(adapter)

        viewModel.listCartItems.observe(viewLifecycleOwner) {
            (binding.rvCart.itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false
            adapter.list = it
            binding.textTotal.text = viewModel.priseTotal(it).toString()
        }
    }

    private fun setOnClickListeners(adapter: CartRVAdapter) {
        adapter.ontvMinClickListener = {
            val cartItem = it.tag as CartItem
            if (cartItem.amount > 1 && numberOfItemsInTheCart > 1) {
                deleteItemInTheCart()
                numberOfItemsInTheCart = getNumberOfItemsInTheCart()
                viewModel.add(
                    cartItem.copy(
                        priseTotal = cartItem.priseTotal - cartItem.prise,
                        amount = cartItem.amount - 1
                    )
                )
            }
        }

        adapter.ontvMaxClickListener = {
            val cartItem = it.tag as CartItem
            addItemInTheCart()
            numberOfItemsInTheCart = getNumberOfItemsInTheCart()
            viewModel.add(
                cartItem.copy(
                    priseTotal = cartItem.priseTotal + cartItem.prise,
                    amount = cartItem.amount + 1
                )
            )
        }

        adapter.ontvDeleteClickListener = {
            val cartItem = it.tag as CartItem
            deleteItemInTheCart(cartItem.amount)
            numberOfItemsInTheCart = getNumberOfItemsInTheCart()
            viewModel.delete(cartItem.id)
        }

        binding.tvBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}

