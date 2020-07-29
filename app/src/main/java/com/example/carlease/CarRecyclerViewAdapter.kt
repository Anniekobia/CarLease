package com.example.carlease

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class CarRecyclerViewAdapter(private val context: Context?, val carlist: ArrayList<Car>) :
    RecyclerView.Adapter<CarRecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.car_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return carlist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val car : Car = carlist[position]
        holder.rating.setText(car.rating)
        holder.carColor.setText(car.carColor)
        holder.chargePerDay.setText("From $"+car.chargePerDay.toString()+"/day")
        holder.carImage.setImageResource(car.carImage)
        holder.deals.setText(car.deals.size.toString()+" Deals")


        holder.nextScreen.setOnClickListener {
            val extras = Bundle()
            extras.putString("color",car.carColor)
            extras.putInt("image",car.carImage)
            extras.putString("model",car.carModel)
            extras.putInt("doors",car.numOfDoors)
            extras.putInt("seaters",car.numOfseaters)
            extras.putInt("charge",car.chargePerDay)

            val intent = Intent(context, SelectedCarActivity::class.java)
            intent.putExtra("Car",extras)
            context?.startActivity(intent)
        }

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rating: TextView = itemView.findViewById(R.id.ratingText)
        val carColor: TextView = itemView.findViewById(R.id.carColor)
        val chargePerDay: TextView = itemView.findViewById(R.id.chargePerDay)
        val carImage : ImageView = itemView.findViewById(R.id.carImage)
        val deals : TextView = itemView.findViewById(R.id.numberOfDeals)

        val nextScreen : ImageView = itemView.findViewById(R.id.nextIcon)
    }

}