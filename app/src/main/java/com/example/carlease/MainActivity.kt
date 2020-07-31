package com.example.carlease

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.milliseconds

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    val carList: ArrayList<Car> = arrayListOf<Car>(
        Car(
            "Audi A4 Sports",
            "Orange Audi",
            R.drawable.orange_sports,
            4,
            5,
            "4.7",
            true,
            true,
            199,
            "Florida, USA",
            arrayListOf(
                Deal("John Smith", 150, "Miami, 786 FL", Date(2020, 8, 16), Date(2020, 8, 19)),
                Deal("John Smith", 150, "Miami, 786 FL", Date(2020, 8, 16), Date(2020, 8, 16)),
                Deal("John Smith", 150, "Miami, 786 FL", Date(2020, 8, 16), Date(2020, 8, 16)),
                Deal("John Smith", 150, "Miami, 786 FL", Date(2020, 8, 16), Date(2020, 8, 16)),
                Deal("John Smith", 150, "Miami, 786 FL", Date(2020, 8, 16), Date(2020, 8, 16)),
                Deal("John Smith", 150, "Miami, 786 FL", Date(2020, 8, 16), Date(2020, 8, 16)),
                Deal("Sam Brody", 100, "Miami, 786 FL", Date(2020, 9, 15), Date(2020, 10, 16))
            )
        ),
        Car(
            "Kia Cerato",
            "Blue Kia Cerato",
            R.drawable.blue_car,
            4,
            5,
            "4.5",
            false,
            true,
            100,
            "Florida, USA",
            arrayListOf(
                Deal("John Smith", 150, "Miami, 786 FL", Date(2020, 8, 23), Date(2020, 9, 6)),
                Deal("Sam Brody", 100, "Miami, 786 FL", Date(2020, 9, 15), Date(2020, 10, 16))
            )
        ),
        Car(
            "Tesla",
            "Red Tesla",
            R.drawable.sports_car_4484890,
            4,
            5,
            "4.8",
            true,
            true,
            200,
            "Florida, USA",
            arrayListOf(
                Deal("John Smith", 150, "Miami, 786 FL", Date(2020, 9, 1), Date(2020, 9, 7)),
                Deal("Sam Brody", 100, "Miami, 786 FL", Date(2020, 9, 15), Date(2020, 10, 16)),
                Deal("Ann Kibatha", 150, "Miami, 786 FL", Date(2020, 8, 16), Date(2020, 8, 16)),
                Deal("Mary mMartha", 100, "Miami, 786 FL", Date(2020, 9, 15), Date(2020, 10, 16))
            )
        )
    )

    lateinit var recyclerView: RecyclerView

    @ExperimentalTime
    lateinit var recyclerViewAdapter: CarRecyclerViewAdapter
    lateinit var results: TextView

    @ExperimentalTime
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Replaced applicationContext with (this) because Application context wrap the whole application but this is only for the activity
        //This was throwing an error on Recyclerview

        results = findViewById(R.id.results_txtview)
        results.setText(carList.size.toString() + " Results")
        recyclerView = findViewById(R.id.recyclerView)
        recyclerViewAdapter = CarRecyclerViewAdapter({ car: Car -> listItemClicked(car) }, carList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerViewAdapter
    }

    @ExperimentalTime
    private fun listItemClicked(car: Car) {
        val extras = Bundle()
        extras.putString("color", car.carColor)
        extras.putInt("image", car.carImage)
        extras.putString("model", car.carModel)
        extras.putInt("doors", car.numOfDoors)
        extras.putInt("seaters", car.numOfseaters)
        extras.putInt("charge", car.chargePerDay)
        extras.putString("location", car.deals[0].pickupReturnLocation)

        val parser = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US)
        val formatter = SimpleDateFormat("d MMM", Locale.US)
        val formattedDate =
            formatter.format(parser.parse(car.deals[0].departureDate.toString()))
        val formattedDate1 = formatter.format(parser.parse(car.deals[0].returnDate.toString()))
        extras.putString("departure", formattedDate)
        extras.putString("return", formattedDate1)

        val cal = Calendar.getInstance()
        val cal2 = Calendar.getInstance()
        cal.time = car.deals[0].departureDate
        cal2.time = car.deals[0].returnDate
        val days = daysDiff2(cal2, cal)
        extras.putInt("days", days)

        val intent = Intent(this, SelectedCarActivity::class.java)
        intent.putExtra("Car", extras)
        startActivity(intent)
    }

    @ExperimentalTime
    private fun daysDiff2(c1: Calendar, c2: Calendar): Int {
        val diffInMillis = c1.timeInMillis - c2.timeInMillis
        return diffInMillis.milliseconds.toInt(DurationUnit.DAYS)
    }
}
