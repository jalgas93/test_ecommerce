package uz.shop.feature_cart.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import uz.shop.feature_cart.databinding.ViewholderCartBinding
import uz.shop.feature_detail.presentation.helper.ChangeNumberItemsListener
import uz.shop.feature_detail.presentation.helper.ManagmentCart
import uz.shop.feature_home.data.model.itemModel.ItemsModel

class CartAdapter(
    private val listItemSelected: ArrayList<ItemsModel>,
    context: Context,
    var changeNumberItemListener: ChangeNumberItemsListener? = null,

) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(val binding: ViewholderCartBinding) : RecyclerView.ViewHolder(binding.root)
    private val managementCart = ManagmentCart(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listItemSelected.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItemSelected[position]
        holder.binding.titleText.text = item.title
        holder.binding.freeEachItem.text = "$${item.price}"
        holder.binding.totalEachItem.text = "$${Math.round(item.numberInCart * item.price)}"
        holder.binding.number.text = item.numberInCart.toString()

        Glide.with(holder.itemView.context).load(item.picUrl[0])
            .apply(RequestOptions().transform(CenterCrop())).into(holder.binding.pic)

        holder.binding.plusCartBtn.setOnClickListener {
            managementCart.plusItem(listItemSelected, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemListener?.onChanged()
                }
            })
        }
        holder.binding.minusCartBtn.setOnClickListener {
            managementCart.minusItem(
                listItemSelected,
                position,
                object : ChangeNumberItemsListener {
                    override fun onChanged() {
                        notifyDataSetChanged()
                        changeNumberItemListener?.onChanged()
                    }
                })
        }
    }
}