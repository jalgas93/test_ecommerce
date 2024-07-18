package uz.shop.feature_home.domain.navigation

import uz.shop.feature_home.data.model.itemModel.ItemsModel

interface NavigationList {
    fun openDetail(model: ItemsModel)

    fun openDashboard()
}