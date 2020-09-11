package com.example.country

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.country_card.*


class MainActivity : AppCompatActivity() {
    lateinit var myadapter:CustomAdapter
    lateinit var likeBtn:Button
    var countries=ArrayList<model>()
    lateinit var switchViewButton: Button
    lateinit var searchbar:EditText
    lateinit var recycler:RecyclerView
    private var ascending=true
    lateinit var sortBtn:Button
    val adapter = CustomAdapter(countries) // Accessing the Adapter class
    override fun onCreate(savedInstanceState: Bundle?) {
        var viewswitchcount=0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler=findViewById(R.id.recyclerview)

        // Layout for inflating Cards
        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        countries.add(
            model(
                "USA",
                "Washington,DC",
                "US",
                "840",
                "USA",
                "US",
                "1",
                "+38.00-97.00",
                "-124.73 +49.39 -66.95 +24.54",
                R.drawable.flag_usa
            )
        )
        countries.add(
            model(
                "Jamaica",
                "Kingston",
                "JM",
                "388",
                "JAM",
                "JM",
                "1876",
                "+18.15-77.30",
                "+166.00 -29.24 -176.28 -52.61",
                R.drawable.flag_jamaica
            )
        )
        countries.add(
            model(
                "NewZealand",
                "Wellington",
                "NZ",
                "554",
                "NZL",
                "NZ",
                "64",
                "-41.00+174.00",
                "-8.67 +37.09 +11.98 +18.96",
                R.drawable.flag_newzealand
            )
        )
        countries.add(
            model(
                "Bhutan",
                "Thimphu",
                "BT",
                "64",
                "BTN",
                "BT",
                "975",
                "+27.30 +90.30",
                "+88.76 +28.32 +92.13 +26.71",
                R.drawable.flag_bhutan
            )
        )
        countries.add(
            model(
                "Australia",
                "Canberra",
                "AU",
                "36",
                "AUS",
                "AS",
                "61",
                "-27.00 +133.00",
                "+112.91 -10.06 +153.64 -43.64",
                R.drawable.flag_aus
            )
        )
        countries.add(
            model(
                "Bangladesh",
                "Dhaka",
                "BD",
                "50",
                "BGD",
                "BG",
                "880",
                "+24.00 +90.00",
                "+88.03 +26.63 +92.67 +20.74",
                R.drawable.flag_ban
            )
        )
        countries.add(
            model(
                "Ecuador",
                "Quito",
                "EC",
                "218",
                "ECU",
                "EC",
                "593",
                "-2.00 -77.30",
                "-91.66 +1.42 -75.18 -5.00",
                R.drawable.flag_ecuador
            )
        )
        countries.add(
            model(
                "Portugal",
                "Lisbon",
                "PT",
                "620",
                "PRT",
                "PO",
                "351",
                "+39.30 -8.00",
                "-9.50 +42.15 -6.18 +36.98",
                R.drawable.flag_portugal
            )
        )
        countries.add(
            model(
                "Brazil",
                "Brasilia",
                "BR",
                "76",
                "BRA",
                "BR",
                "55",
                "-10.00 -55.00",
                "-73.99 +5.26 -32.39 -33.75",
                R.drawable.flaf_brazil
            )
        )
        countries.add(
            model(
                "Canada",
                "Ottawa",
                "CA",
                "124",
                "CAN",
                "CA",
                "1",
                "+60.00 -95.00",
                "-141.00 +83.11 -52.64 +41.68",
                R.drawable.flag_canada
            )
        )
        countries.add(
            model(
                "China",
                "Beijing",
                "CN",
                "156",
                "CHN",
                "CH",
                "86",
                "+35.00 +105.00",
                "+73.56 +53.56 +134.77 +15.78",
                R.drawable.flag_china
            )
        )
        countries.add(
            model(
                "Denmark",
                "Copenhagen",
                "DK",
                "208",
                "DNK",
                "DA",
                "45",
                "+56.00 +10.00",
                "+8.08 +57.75 +15.16 +54.56",
                R.drawable.flag_denmark
            )
        )
        countries.add(
            model(
                "Chile",
                "Santiago",
                "CL",
                "152",
                "CHL",
                "CI",
                "56",
                "-30.00 -71.00",
                "-109.46 -17.51 -66.42 -55.92",
                R.drawable.flag_chile
            )
        )
        countries.add(
            model(
                "India",
                "Delhi",
                "IN",
                "356",
                "IND",
                "IN",
                "91",
                "+20.00 +77.00",
                "+68.19 +35.50 +97.40 +6.75",
                R.drawable.flag_india
            )
        )
        countries.add(
            model(
                "Russia",
                "Moscow",
                "RU",
                "643",
                "RUS",
                "RS",
                "7",
                "+60.00 +100.00",
                "+19.25 +81.86 -169.05 +41.19",
                R.drawable.flag_russia
            )
        )
        countries.add(
            model(
                "Bermuda",
                "Hamilton",
                "BM",
                "60",
                "BMU",
                "BD",
                "1441",
                "+32.20 -64.45",
                "-64.90 +32.38 -64.65 +32.25",
                R.drawable.flag_ber
            )
        )
        countries.add(
            model(
                "Italy",
                "Rome",
                "IT",
                "380",
                "ITA",
                "IT",
                "39",
                "+42.50 +12.50",
                "+6.61 +47.10 +18.51 +36.65",
                R.drawable.flag_italy
            )
        )
        countries.add(
            model(
                "Japan",
                "Tokyo",
                "JP",
                "392",
                "JPN",
                "JA",
                "81",
                "+36.00 +138.00",
                "+122.94 +45.52 +145.82 +24.25",
                R.drawable.flag_japan
            )
        )
        countries.add(
            model(
                "Zimbabwe",
                "Harare",
                "ZW",
                "716",
                "ZWE",
                "ZI",
                "263",
                "+1.00 +38.00",
                "+25.24 -15.61 +33.06 -22.42",
                R.drawable.flag_zimbabwe
            )
        )
        countries.add(
            model(
                "Switzerland",
                "Bern",
                "CH",
                "756",
                "CHE",
                "SZ",
                "41",
                "+47.00+8.00",
                "+5.96 +47.81 +10.49 +45.83",
                R.drawable.flag_switzerland
            )
        )
        searchbar=findViewById(R.id.search_bar)
        searchbar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                filter(editable.toString())
            }
          }
        )
        recycler.adapter=adapter
        //Switching between ListView and GridView
        switchViewButton=findViewById(R.id.switchView)
        switchViewButton.setOnClickListener {
            viewswitchcount++
            if (viewswitchcount%2==0)
            {
                recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            }
            else
            {
                recycler.layoutManager = GridLayoutManager(this, 2)
                viewswitchcount=1
            }
        }

        //Sorting the countries based on country names
        sortBtn=findViewById(R.id.sortCards)
        sortBtn.setOnClickListener {
            sortCards(ascending)
            ascending=!ascending
        }
    }
    // Function for sorting
    private fun sortCards(asc:Boolean){
        if(asc){
            countries.sortBy{it.country_name  }
            recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        }
        else{
            countries.reverse()
            recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        }
    }

    // Searching Function
    private fun filter(text: String) {
        val filterdNames: ArrayList<model> = ArrayList()
        for (s in countries) {
            if (s.country_name.toLowerCase().contains(text.toLowerCase())) {
                filterdNames.add(s)
            }
            else{
                print("No Matches Found")
            }
        }
        adapter?.filterList(filterdNames)
    }
}