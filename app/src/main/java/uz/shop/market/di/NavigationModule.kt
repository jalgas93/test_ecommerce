package uz.shop.market

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import uz.shop.feature_cart.domain.navigation.CartNavigation
import uz.shop.feature_detail.domain.navigation.DetailNavigation
import uz.shop.feature_home.domain.navigation.NavigationList


@Module
@InstallIn(ActivityComponent::class)
object NavigationModule {
    @Provides
    fun navController(activity: FragmentActivity): NavController {
        return NavHostFragment.findNavController(activity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!)

    }

    @Module
    @InstallIn(ActivityComponent::class)
    abstract class NavigationListModule {
        @Binds
        abstract fun bindDashboard(navigator: Navigator): NavigationList

        @Binds
        abstract fun bindDetail(navigator: Navigator): DetailNavigation

        @Binds
        abstract fun bindCart(navigator: Navigator): CartNavigation


    }
}