package uz.shop.market

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import uz.shop.feature_home.domain.navigation.NavigationList


@Module
@InstallIn(ActivityComponent::class)
object NavigationModule {
    @Provides
    fun navController(activity: FragmentActivity): NavController? {
        return NavHostFragment.findNavController(activity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!)

    }

    @Module
    @InstallIn(ActivityComponent::class)
    abstract class NavigationListModule {
        @Binds
        abstract fun click(navigator: Navigator): NavigationList
    }
}