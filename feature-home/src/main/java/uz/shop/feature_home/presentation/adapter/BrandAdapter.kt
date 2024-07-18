package uz.shop.feature_home.presentation.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.shop.feature_home.R
import uz.shop.feature_home.data.model.brandModel.BrandModel
import uz.shop.feature_home.databinding.ViewholderBrandBinding

class BrandAdapter(val items: MutableList<BrandModel>) :
    RecyclerView.Adapter<BrandAdapter.ViewHolder>() {
    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context

    class ViewHolder(val binding: ViewholderBrandBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandAdapter.ViewHolder {
        context = parent.context
        val binding = ViewholderBrandBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: BrandAdapter.ViewHolder, position: Int) {
        val items = items[position]
        holder.binding.title.text = items.title

        Glide.with(holder.itemView.context).load(items.picUrl).into(holder.binding.pic)

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        holder.binding.title.setTextColor(context.resources.getColor(uz.shop.assets.R.color.white))
        if (selectedPosition == position) {
            holder.binding.pic.setBackgroundResource(0)
            holder.binding.mainLayout.setBackgroundResource(uz.shop.assets.R.drawable.purple_button_bg)
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(context.getColor(uz.shop.assets.R.color.white))
            )
            holder.binding.title.visibility = View.VISIBLE
        } else {
            holder.binding.pic.setBackgroundResource(uz.shop.assets.R.drawable.grey_bg)
            holder.binding.mainLayout.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(context.getColor(uz.shop.assets.R.color.black))
            )
            holder.binding.title.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = items.size
}