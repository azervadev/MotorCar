package com.azerva.motorcar.core.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.azerva.motorcar.R
import com.azerva.motorcar.databinding.ItemRowTableBinding
import com.azerva.motorcar.model.CarVo

class MainAdapter (private val listener: OnEventClickListener): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    internal var carsList = mutableListOf<CarVo>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface OnEventClickListener{
        fun onCarClick(car: CarVo)
    }

    inner class ViewHolder (itemVIew : View) : RecyclerView.ViewHolder(itemVIew) {
        private val binding = ItemRowTableBinding.bind(itemVIew)

        fun render(carVo: CarVo) = with(binding){
            rowBrand.text = carVo.mark
            rowModel.text= carVo.model
            rowDate.text = carVo.date
            rowPrice.text = carVo.price

            if (adapterPosition % 2 == 1) {
                rowTable.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.silver_platinum))
            }

            rowTable.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION){
                    listener.onCarClick(carsList[adapterPosition])
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row_table, parent, false))
    }

    override fun getItemCount(): Int = carsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(carsList[position])
    }

}