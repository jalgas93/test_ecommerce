package uz.shop.feature_home.presentation.view.splash

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import uz.shop.feature_home.databinding.FragmentSplashBinding
import uz.shop.feature_home.domain.navigation.NavigationList
import uz.shop.feature_home.presentation.adapter.PopularAdapter
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding

    @Inject
    lateinit var navigation: NavigationList
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initial()
    }


    private fun initial() {
        binding.startBtn.setOnClickListener {
            Log.i("TAG", "Jalgas")
            navigation.openDashboard()
        }
    }

}