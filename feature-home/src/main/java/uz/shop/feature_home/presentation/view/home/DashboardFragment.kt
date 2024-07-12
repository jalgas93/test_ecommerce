package uz.shop.feature_home.presentation.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import dagger.hilt.android.AndroidEntryPoint
import uz.shop.feature_home.R
import uz.shop.feature_home.data.model.sliderModel.SliderModel
import uz.shop.feature_home.databinding.FragmentDashboardBinding
import uz.shop.feature_home.domain.navigation.NavigationList
import uz.shop.feature_home.presentation.adapter.SliderAdapter
import uz.shop.feature_home.presentation.viewmodel.SliderViewModel
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private val viewModel = SliderViewModel()
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBanner();
    }

    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.banner.observe(viewLifecycleOwner, Observer { items ->
            banners(items)
            binding.progressBarBanner.visibility = View.GONE
            binding.dotIndicator.visibility = View.VISIBLE
        })
    }

    private fun banners(images: List<SliderModel>) {
        binding.viewpagerSlider.adapter = SliderAdapter(images, binding.viewpagerSlider)
        binding.viewpagerSlider.clipToPadding = false
        binding.viewpagerSlider.clipChildren = false
        binding.viewpagerSlider.offscreenPageLimit = 3
        binding.viewpagerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }

        binding.viewpagerSlider.setPageTransformer(compositePageTransformer)
        if (images.size > 1) {
             binding.dotIndicator.attachTo(binding.viewpagerSlider)
        }
    }
}