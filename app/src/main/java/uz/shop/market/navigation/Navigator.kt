package uz.shop.market

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import dagger.hilt.android.scopes.ActivityScoped
import uz.shop.feature_home.domain.navigation.NavigationList
import javax.inject.Inject

@ActivityScoped
class Navigator @Inject constructor(
    private val navController: NavController
) : NavigationList {
    override fun openMovie(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)
        Log.d("Click", "HERE")
        navController.navigate(R.id.dashboardFragment)
    }

}