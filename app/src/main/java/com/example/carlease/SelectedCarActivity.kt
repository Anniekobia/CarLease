package com.example.carlease

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS


class SelectedCarActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var backArrow: ImageView
    lateinit var calendarIcon: ImageView
    lateinit var carImage: ImageView
    lateinit var carColor: TextView
    lateinit var model: TextView
    lateinit var num0fDoors: TextView
    lateinit var num0fSeaters: TextView
    private var chargePerDay: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_car)

        backArrow = findViewById(R.id.navbar_icon)
        backArrow.setOnClickListener(this)
        calendarIcon = findViewById(R.id.calendarIcon)
        backArrow.setOnClickListener(this)

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
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.navbar_icon -> {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.calendarIcon -> {
                getDate(v);
            }
        }
    }

    private fun getDate(v: View) {
//        val cal = Calendar.getInstance()
//        val y = cal.get(Calendar.YEAR)
//        val m = cal.get(Calendar.MONTH)
//        val d = cal.get(Calendar.DAY_OF_MONTH)
//
//        val datepickerdialog:DatePickerDialog = DatePickerDialog(applicationContext, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//        }, y, m, d)
//
//        datepickerdialog.show()
    }

}
