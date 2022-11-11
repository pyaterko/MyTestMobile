package com.owl_laugh_at_wasted_time.mytestmobile.presintation.ui.fragments.mainscreen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.elveum.elementadapter.SimpleBindingAdapter
import com.elveum.elementadapter.simpleAdapter
import com.owl_laugh_at_wasted_time.mytestmobile.R
import com.owl_laugh_at_wasted_time.mytestmobile.databinding.FragmentMainScreenBinding
import com.owl_laugh_at_wasted_time.mytestmobile.databinding.ItemBestSellerBinding
import com.owl_laugh_at_wasted_time.mytestmobile.databinding.ItemCategoryBinding
import com.owl_laugh_at_wasted_time.mytestmobile.databinding.ItemPageBinding
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.BestSellerItem
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.CategoryItem
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.HotSales
import com.owl_laugh_at_wasted_time.mytestmobile.presintation.base.BaseFragment
import com.owl_laugh_at_wasted_time.mytestmobile.presintation.base.viewBinding

class MainScreenFragment : BaseFragment(R.layout.fragment_main_screen) {

    private val binding by viewBinding(FragmentMainScreenBinding::bind)
    private val viewModel by viewModels<MainScreenViewModel> { viewModelFactory }
    private var sampleList: List<HotSales> = listOf()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = createCategoryAdapter()
        (binding.rvCategory.itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false
        val bestAdapter = createBestSellerAdapter()
        binding.rvCategory.adapter = adapter
        binding.rvBestSeller.adapter = bestAdapter
        initViewPager(binding.hotViewPager)
        setObservers(adapter, bestAdapter)
        setOnClickListeners()
    }

    private fun setObservers(
        adapter: SimpleBindingAdapter<CategoryItem>,
        bestAdapter: SimpleBindingAdapter<BestSellerItem>
    ) {
        viewModel.categoryList.observe(viewLifecycleOwner) {
            adapter.submitList(it.toList())
        }

        viewModel.bestsellerList.observe(viewLifecycleOwner) {
            bestAdapter.submitList(it)
        }

        viewModel.hotSales.observe(viewLifecycleOwner) {
            sampleList = it
            val hotAdapter = createViewPagerAdapter()
            infiniteViewPager.adapter = hotAdapter
            val list = listOf(sampleList.last()) + sampleList + listOf(sampleList.first())
            hotAdapter.submitList(list)
            onInfinitePageChangeCallback(sampleList.size + 2)
        }
    }

    private fun createViewPagerAdapter() =
        simpleAdapter<HotSales, ItemPageBinding> {

            areContentsSame = { oldItem, newItem -> oldItem == newItem }
            bind { item ->
                if (!(item.new)) {
                    imHeard.visibility = View.GONE
                }
                Glide.with(imageHotSales)
                    .load(item.image)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.bg_item_placeholder)
                    .error(R.drawable.bottom_sheet_background)
                    .into(imageHotSales)
            }
        }

    private fun createBestSellerAdapter() =
        simpleAdapter<BestSellerItem, ItemBestSellerBinding> {

            areContentsSame = { oldItem, newItem -> oldItem == newItem }
            bind { item ->
                if (item.addToFavorite) {
                    imHeard.setBackground(requireContext().getDrawable(R.drawable.ic_vectorheard))
                } else {
                    imHeard.setBackground(requireContext().getDrawable(R.drawable.ic_h_white))
                }
                prise.text = item.prise.toString()
                discaundPrise.text = item.discountedPrice.toString()
                tvName.text = item.name

                Glide.with(imageViewThin)
                    .load(item.image)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.bg_item_placeholder)
                    .error(R.drawable.bottom_sheet_background)
                    .into(imageViewThin)
            }
            listeners {
                root.onClick {
                    findNavController().navigate(
                        MainScreenFragmentDirections
                            .actionMainScreenFragmentToDetailsFragment()
                    )
                }
            }
        }

    private fun createCategoryAdapter() =
        simpleAdapter<CategoryItem, ItemCategoryBinding> {

            bind { item ->
                if (item.state) {
                    imageViewFon.setBackground(requireContext().getDrawable(item.fon))
                    imageViewImage.setBackground(requireContext().getDrawable(item.imageW))
                    textViewName.setText(item.name)
                    textViewName.setTextColor(R.color.sapphire)
                } else {
                    imageViewFon.setBackground(requireContext().getDrawable(item.fonW))
                    imageViewImage.setBackground(requireContext().getDrawable(item.image))
                    textViewName.setText(item.name)
                    textViewName.setTextColor(R.color.black)
                }
            }
            listeners {
                root.onClick {
                    viewModel.updateCategory(it)
                }
            }
        }


    private fun setOnClickListeners() {

        binding.bottomButon.setOnClickListener {
            findNavController()
                .navigate(MainScreenFragmentDirections.actionMainScreenFragmentToDetailsFragment())
        }

        binding.tvFilterOptions.setOnClickListener {
            binding.group.visibility = View.INVISIBLE
            binding.bottomCl.visibility = View.VISIBLE
        }

        binding.bottomCl.setOnClickListener {
            binding.group.visibility = View.VISIBLE
            binding.bottomCl.visibility = View.INVISIBLE
        }
    }

}
