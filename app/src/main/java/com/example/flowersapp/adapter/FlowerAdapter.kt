package com.example.flowersapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flowersapp.R
import com.example.flowersapp.model.Flowers
import com.example.flowersapp.model.FlowersItem
import com.example.flowersapp.rest.FlowersAPI
import com.squareup.picasso.Picasso

class FlowerAdapter(
    private val flowers: MutableList<FlowersItem> = mutableListOf()
) : RecyclerView.Adapter<FlowersViewHolder>() {


    fun updateFlowers(newFlowers: Flowers) {
        flowers.clear()
        flowers.addAll(newFlowers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowersViewHolder {
        val flowerView =
            LayoutInflater.from(parent.context).inflate(R.layout.flower_item, parent, false)
        return FlowersViewHolder(flowerView)
    }

    override fun onBindViewHolder(holder: FlowersViewHolder, position: Int) {
        val flowerItem = flowers[position]
        holder.bind(flowerItem)
    }

    override fun getItemCount(): Int = flowers.size


}

class FlowersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val flowerName: TextView = itemView.findViewById(R.id.flower_name)
    val flowerCategory: TextView = itemView.findViewById(R.id.flower_category)
    val flowerPrice: TextView = itemView.findViewById(R.id.flower_price)
    val flowerPhoto: ImageView = itemView.findViewById(R.id.flower_photo)

    fun bind(flower: FlowersItem) {
        flowerName.text = flower.name
        flowerCategory.text = flower.category
        flowerPrice.text = flower.price.toString()

        Picasso.get()
            .load(FlowersAPI.PHOTOS_URL + flower.photo)
            .placeholder(R.drawable.ic_baseline_camera_24_loading)
            .error(R.drawable.ic_baseline_broken_image_24_error)
            //.fit()
            .resize(300, 300)
            .into(flowerPhoto)
    }

}

