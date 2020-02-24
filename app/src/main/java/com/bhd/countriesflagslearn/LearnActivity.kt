package com.bhd.countriesflagslearn

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class LearnActivity : AppCompatActivity() {
    val listCountries = ListCountries()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn)

        if (!listCountries.getCountriesLocal("ListCountries.txt")){
            listCountries.getCountriesOnline("ListCountries.txt")
        }

        getInterface()
    }

    fun getInterface(){
        val gridview = findViewById<GridView>(R.id.learnGridView)

        val adapter = LearnGridViewAdapter(this, listCountries)

        gridview.adapter = adapter

        //start new activity when clicked on one employee
        gridview.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, CountryDetailActivity::class.java)

            intent.putExtra("data", listCountries)
            intent.putExtra("pos", position)

            startActivityForResult(intent, 0)
        }
    }

    fun onBackButtonClicked(view: View){
        finish()
    }
}
