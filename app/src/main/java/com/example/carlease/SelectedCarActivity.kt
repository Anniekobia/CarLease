package com.example.carlease

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SelectedCarActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var backArrow: ImageView
    lateinit var dateRange: TextView
    lateinit var location: TextView

    lateinit var carImage: ImageView
    lateinit var carColor: TextView
    lateinit var model: TextView
    lateinit var num0fDoors: TextView
    lateinit var num0fSeaters: TextView
    lateinit var totalCharge : TextView
    private var chargePerDay: Int = 0
    private var days: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_car)

        backArrow = findViewById(R.id.navbar_icon)
        backArrow.setOnClickListener(this)
        dateRange = findViewById(R.id.calendatTxt)
        location = findViewById(R.id.locationTxt)
        totalCharge = findViewById(R.id.totalCharge)

        carImage = findViewById(R.id.carImage)
        carColor = findViewById(R.id.carColor)
        model = findViewById(R.id.carModel)
        num0fDoors = findViewById(R.id.numOfDoors)
        num0fSeaters = findViewById(R.id.peopleTxt)

        val car = intent.getBundleExtra("Car")
        setValues(car)
    }

    fun setValues(car: Bundle) {
        carColor.text = car.getString("color")
        carImage.setImageResource(car.getInt("image"))
        model.text = car.getString("model")
        num0fSeaters.text = car.getInt("seaters").toString() + " Seaters"
        num0fDoors.text = car.getInt("doors").toString() + " Doors"

        chargePerDay = car.getInt("charge")
        days = car.getInt("days")
        totalCharge.text = "$"+(chargePerDay * days).toString()
        dateRange.text = car.getString("departure")+" - "+ car.getString("return")
        location.text = car.getString("location")
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.navbar_icon -> {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }


}
