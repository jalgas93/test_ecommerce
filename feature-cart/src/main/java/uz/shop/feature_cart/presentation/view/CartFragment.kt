package uz.shop.feature_cart.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import uz.shop.feature_cart.R
import uz.shop.feature_cart.databinding.FragmentCartBinding
import uz.shop.feature_cart.domain.navigation.CartNavigation
import uz.shop.feature_cart.presentation.adapter.CartAdapter
import uz.shop.feature_detail.presentation.helper.ChangeNumberItemsListener
import uz.shop.feature_detail.presentation.helper.ManagmentCart
import uz.shop.feature_home.databinding.FragmentDashboardBinding
import uz.shop.feature_home.domain.navigation.NavigationList
import javax.inject.Inject
@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax: Double = 0.0

    @Inject
    lateinit var navigation: CartNavigation
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        managmentCart = ManagmentCart(requireActivity())
        setVariable()
        initCartList()
        calculateCart()
    }

    private fun setVariable() {
        binding.backCartBtn.setOnClickListener {
            navigation.backCart()
        }
    }

    private fun initCartList() {
        binding.viewCart.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.viewCart.adapter = CartAdapter(
            managmentCart.getListCart(),
            context = requireActivity(),
            object : ChangeNumberItemsListener {
                override fun onChanged() {
                    calculateCart()
                }

            },
        )
        with(binding) {
            emptyTxt.visibility = if (managmentCart.getListCart().isEmpty())
                View.VISIBLE else View.GONE
            scrollView2.visibility = if (managmentCart.getListCart().isEmpty())
                View.GONE else View.VISIBLE
        }
    }

    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 10.0
        tax = Math.round((managmentCart.getTotalFee() * percentTax) * 100) / 100.0
        val total = Math.round((managmentCart.getTotalFee() * tax + delivery) * 100) / 100
        val itemTotal = Math.round(managmentCart.getTotalFee() * 100) / 100

        with(binding) {
            totalFreeTxt.text = "$$itemTotal"
            taxTxt.text = "$$tax"
            deliveryTxt.text = "$$delivery"
            totalTxt.text = "$$total"
        }
        binding.button.setOnClickListener {
            navigation.onTapCart()
        }
    }


}