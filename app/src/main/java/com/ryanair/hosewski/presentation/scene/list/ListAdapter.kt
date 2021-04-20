package com.ryanair.hosewski.presentation.scene.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.ryanair.hosewski.databinding.ItemFlightBinding
import com.ryanair.hosewski.domain.model.Flight

class ListAdapter(private val liveData: LiveData<List<Flight>>,
                  owner: LifecycleOwner,
                  priceLimit: Int,
                  private val onItemClicked: (Flight) -> Unit): RecyclerView.Adapter<ListAdapter.ItemHolder>() {

    private val items = mutableListOf<Flight>()

    init {
        liveData.observe(owner, Observer {
            items.clear()
            items.addAll(it.filter { item -> item.price < priceLimit.toDouble() } )
            notifyDataSetChanged()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(ItemFlightBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(items[position], onItemClicked)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updatePrice(price: Int) {
        items.clear()
        items.addAll(liveData.value?.filter { it.price < price.toDouble() } ?: emptyList())
        notifyDataSetChanged()
    }

    class ItemHolder(private val binding: ItemFlightBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Flight, listener: (Flight) -> Unit) = with(binding.root) {
            binding.flightNumber.text = item.number
            binding.flightDate.text = item.date.toLocaleString()
            binding.flightDuration.text = item.duration
            binding.flightPrice.text = "${item.price.toString()} ${item.currency}"
            setOnClickListener { listener(item) }
        }
    }
}