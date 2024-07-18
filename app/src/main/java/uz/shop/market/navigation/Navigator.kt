package uz.shop.market

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import dagger.hilt.android.scopes.ActivityScoped
import uz.shop.feature_cart.domain.navigation.CartNavigation
import uz.shop.feature_detail.domain.navigation.DetailNavigation
import uz.shop.feature_home.data.model.itemModel.ItemsModel
import uz.shop.feature_home.domain.navigation.NavigationList
import javax.inject.Inject

@ActivityScoped
class Navigator @Inject constructor(
    private val navController: NavController
) : NavigationList , DetailNavigation, CartNavigation {
    fun openMovie(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)
        Log.d("navigate", "dashboardFragment")
        navController?.navigate(R.id.dashboardFragment)
    }

    override fun openDetail(model:ItemsModel) {
        val bundle = Bundle()
        bundle.putParcelable("model",model)
        Log.d("navigate", "detailFragment")
        navController?.navigate(R.id.detailFragment,bundle)
    }

    override fun openDashboard() {
        Log.d("navigate", "dashboardFragment")
        navController?.navigate(R.id.dashboardFragment)
    }

    override fun back() {
        navController?.popBackStack()
    }

    override fun openCart() {
        Log.d("navigate", "cartFragment")
        navController?.navigate(R.id.cartFragment)
    }

    override fun onTapCart() {
        navController?.navigate(R.id.dashboardFragment)
    }

    override fun backCart() {
        navController?.popBackStack()
    }

}