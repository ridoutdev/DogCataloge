package com.example.dogcataloge.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dogcataloge.R
import com.example.dogcataloge.databinding.ListDogsBinding
import com.example.dogcataloge.models.Dogs
import com.example.dogcataloge.utils.Utils

class AdapterBreed(private var list: List<Dogs>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListDogsBinding.bind(itemView)

        fun bind(dogs: Dogs) {
            binding.textDogs.text = dogs.name
            Utils.loadImage(dogs.image ?: "", binding.imageDogs)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_dogs, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ViewHolder -> {
                val dogs = list[position]
                holder.bind(dogs)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}