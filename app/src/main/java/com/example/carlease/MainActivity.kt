package com.example.carlease

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList
import kotlin.time.ExperimentalTime

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
        recyclerViewAdapter = CarRecyclerViewAdapter(this, carList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerViewAdapter
    }
}
