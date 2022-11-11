package com.owl_laugh_at_wasted_time.mytestmobile.presintation.ui.fragments.detail

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.elveum.elementadapter.simpleAdapter
import com.owl_laugh_at_wasted_time.gata.domain.entity.CartItem
import com.owl_laugh_at_wasted_time.mytestmobile.R
import com.owl_laugh_at_wasted_time.mytestmobile.databinding.FragmentDetailsBinding
import com.owl_laugh_at_wasted_time.mytestmobile.databinding.ItemDetailsBinding
import com.owl_laugh_at_wasted_time.mytestmobile.presintation.base.BaseFragment
import com.owl_laugh_at_wasted_time.mytestmobile.presintation.base.viewBinding
import kotlin.random.Random


class DetailsFragment : BaseFragment(R.layout.fragment_details) {

    private val binding by viewBinding(FragmentDetailsBinding::bind)
    private val viewModel by viewModels<DetailsViewModel> { viewModelFactory }
    private var sampleList: List<String> = listOf()
    private lateinit var cartItem: CartItem

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNumberOfItemsInTheCart()
        initViewPager(binding.viewPager)
        setPageTransformer()
        setObservers()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.textViewCard.setOnClickListener {
            findNavController().navigate(
                DetailsFragmentDirections
                    .actionDetailsFragmentToCartFragment()
            )
        }

        binding.buttonAddToCart.setOnClickListener {
            if (!(cartItem.isCart)) {
                addItemInTheCart()
                numberOfItemsInTheCart = getNumberOfItemsInTheCart()
                binding.imageCart.visibility = View.INVISIBLE
                binding.textViewCard.text = getNumberOfItemsInTheCart().toString()
                cartItem.isCart = true
                viewModel.add(cartItem)
            }
        }
    }

    private fun setObservers() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            cartItem =
                CartItem(
                    name = it.title,
                    image = it.images[0],
                    prise = it.price,
                    priseTotal = it.price,
                    id = Random(Long.MAX_VALUE).toString()
                )
            sampleList = it.images
            val adapter = createViewPagerAdapter()
            infiniteViewPager.adapter = adapter
            val listString =
                listOf(it.images.last()) + it.images + it.images + listOf(it.images.first())
            adapter.submitList(listString)
            onInfinitePageChangeCallback(sampleList.size + 2)
        }
    }

    private fun setNumberOfItemsInTheCart() {
        if (getNumberOfItemsInTheCart() != EMPTY_CART) {
            binding.imageCart.visibility = View.INVISIBLE
            binding.textViewCard.text = getNumberOfItemsInTheCart().toString()
        } else {
            binding.imageCart.visibility = View.VISIBLE
            binding.textViewCard.text = ""
        }
    }

    private fun createViewPagerAdapter() = simpleAdapter<String, ItemDetailsBinding> {
        areContentsSame = { oldItem, newItem -> oldItem == newItem }
        bind { item ->
            Glide.with(ivDetails)
                .load(item)
                .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.NONE)
                .placeholder(R.drawable.bg_item_placeholder)
                .error(R.drawable.bottom_sheet_background)
                .into(ivDetails)
        }
    }

}