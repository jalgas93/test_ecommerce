package uz.shop.feature_home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.shop.feature_home.data.model.brandModel.BrandModel
import uz.shop.feature_home.data.model.itemModel.ItemsModel

import uz.shop.feature_home.data.model.sliderModel.SliderModel
import javax.inject.Inject

@HiltViewModel
class SliderViewModel @Inject constructor() : ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance();
    private val _banner = MutableLiveData<List<SliderModel>>();
    private val _brand = MutableLiveData<MutableList<BrandModel>>();
    private val _popular = MutableLiveData<MutableList<ItemsModel>>();
    private val _selectedItem = MutableLiveData<ItemsModel>()


    val brand: LiveData<MutableList<BrandModel>> = _brand;
    val banner: LiveData<List<SliderModel>> = _banner;
    val popular: MutableLiveData<MutableList<ItemsModel>> = _popular;
    val selectedItem: LiveData<ItemsModel> = _selectedItem

    init {
        loadBanners()
        loadBrand()
        loadPopular()
    }

    fun loadBanners() {
        val Ref = firebaseDatabase.getReference("Banner")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(SliderModel::class.java)
                    if (list != null) {
                        lists.add(list);
                    }
                }
                _banner.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun loadBrand() {
        val Ref = firebaseDatabase.getReference("Category")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<BrandModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(BrandModel::class.java)
                    if (list != null) {
                        lists.add(list);
                    }
                }
                _brand.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun loadPopular() {
        val Ref = firebaseDatabase.getReference("Items")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(ItemsModel::class.java)
                    if (list != null) {
                        lists.add(list);
                    }
                }
                _popular.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}