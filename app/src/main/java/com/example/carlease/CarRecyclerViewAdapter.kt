package com.example.carlease

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.milliseconds

@ExperimentalTime
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
            extras.putString("location",car.deals[0].pickupReturnLocation)

            val parser =  SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US)
            val formatter = SimpleDateFormat("d MMM",Locale.US)
            val formattedDate = formatter.format(parser.parse(car.deals[0].departureDate.toString()))
            val formattedDate1 = formatter.format(parser.parse(car.deals[0].returnDate.toString()))
            extras.putString("departure",formattedDate)
            extras.putString("return",formattedDate1)

            val cal = Calendar.getInstance()
            val cal2 = Calendar.getInstance()
            cal.time = car.deals[0].departureDate
            cal2.time = car.deals[0].returnDate
            val days = daysDiff2(cal2,cal)
            extras.putInt("days",days)

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

    @ExperimentalTime
    fun daysDiff2(c1: Calendar, c2: Calendar): Int {
        val diffInMillis = c1.timeInMillis - c2.timeInMillis
        return diffInMillis.milliseconds.toInt(DurationUnit.DAYS)
    }

}