package com.owl_laugh_at_wasted_time.mytestmobile.view.fragments.mainscreen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.elveum.elementadapter.SimpleBindingAdapter
import com.owl_laugh_at_wasted_time.mytestmobile.R
import com.owl_laugh_at_wasted_time.mytestmobile.databinding.FragmentMainScreenBinding
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.BestSellerItem
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.CategoryItem
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.HotSales
import com.owl_laugh_at_wasted_time.mytestmobile.view.delegates.*
import com.owl_laugh_at_wasted_time.mytestmobile.view.fragments.base.BaseFragment
import com.owl_laugh_at_wasted_time.mytestmobile.viewmodel.mainscreem.MainScreenViewModel

class MainScreenFragment : BaseFragment(R.layout.fragment_main_screen), OnClickCategory,
    OnClickItemBrand, OnClickPriseRange {

    private val binding by viewBinding(FragmentMainScreenBinding::bind)
    private val viewModel by viewModels<MainScreenViewModel> { viewModelFactory }
    private var sampleList: List<HotSales> = listOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val priseAdapter = createPriseRangeAdapter(this)
        val brandAdapter = createBrandAdapter(this)
        val adapter =
            createCategoryAdapter(this, requireContext())
        (binding.rvCategory.itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false
        val bestAdapter =
            createBestSellerAdapter(requireContext(), findNavController())
        binding.rvPrise.adapter = priseAdapter
        binding.rvBrand.adapter = brandAdapter
        binding.rvCategory.adapter = adapter
        binding.rvBestSeller.adapter = bestAdapter
        initViewPager(binding.hotViewPager)
        setObservers(adapter, bestAdapter, brandAdapter, priseAdapter)
        setOnClickListeners()
    }

    private fun setObservers(
        adapter: SimpleBindingAdapter<CategoryItem>,
        bestAdapter: SimpleBindingAdapter<BestSellerItem>,
        brandAdapter: SimpleBindingAdapter<BestSellerItem>,
        priseAdapter: SimpleBindingAdapter<String>
    ) {

        viewModel.priseRange.observe(viewLifecycleOwner){
            priseAdapter.submitList(it)
        }

        viewModel.categoryList.observe(viewLifecycleOwner) {
            adapter.submitList(it.toList())
        }

        viewModel.bestsellerList.observe(viewLifecycleOwner) {
            bestAdapter.submitList(it)
            brandAdapter.submitList(it)
        }

        viewModel.hotSales.observe(viewLifecycleOwner) {
            sampleList = it
            val hotAdapter = createMainScreenViewPagerAdapter()
            infiniteViewPager.adapter = hotAdapter
            val list = listOf(sampleList.last()) + sampleList + listOf(sampleList.first())
            hotAdapter.submitList(list)
            onInfinitePageChangeCallback(sampleList.size + 2)
        }
    }

    private fun setOnClickListeners() {

        binding.spinner2.setOnClickListener {
            binding.rvPrise.visibility = View.VISIBLE
        }

        binding.spinner.setOnClickListener {
            binding.rvBrand.visibility = View.VISIBLE
        }

        binding.bottomButon.setOnClickListener {
            findNavController()
                .navigate(MainScreenFragmentDirections.actionMainScreenFragmentToDetailsFragment())
        }

        binding.tvFilterOptions.setOnClickListener {
            binding.group.visibility = View.INVISIBLE
            binding.bottomCl.visibility = View.VISIBLE
        }

        binding.tvDone.setOnClickListener {
            binding.group.visibility = View.VISIBLE
            binding.bottomCl.visibility = View.INVISIBLE
        }
    }

    override fun onClickCategoryItem(item: CategoryItem) {
        viewModel.updateCategory(item)
    }

    override fun onClickBrandItem(item: BestSellerItem) {
        binding.spinner.text = item.name
        binding.rvBrand.visibility = View.GONE
    }

    override fun onClickPriseRange(item: String) {
        binding.spinner2.text = item
        binding.rvPrise.visibility = View.GONE
    }

}
