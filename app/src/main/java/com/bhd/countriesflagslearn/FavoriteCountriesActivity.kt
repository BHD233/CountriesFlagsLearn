package com.bhd.countriesflagslearn

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity

class FavoriteCountriesActivity : AppCompatActivity() {
    val favoriteCountriesFileName = "ListFavoriteCountries.txt"
    var listFavoriteCountries = ListCountries()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        listFavoriteCountries.getCountriesLocal(favoriteCountriesFileName)
        getInterface()
    }

    override fun onResume() {
        //in case list was changed
        listFavoriteCountries = ListCountries()
        listFavoriteCountries.getCountriesLocal(favoriteCountriesFileName)

        getInterface()

        super.onResume()
    }

    fun getInterface(){
        val gridview = findViewById<GridView>(R.id.favoriteGridView)

        val adapter = LearnGridViewAdapter(this, listFavoriteCountries)

        gridview.adapter = adapter

        //start new activity when clicked on one employee
        gridview.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, CountryDetailActivity::class.java)

            intent.putExtra("data", listFavoriteCountries)
            intent.putExtra("pos", position)

            startActivityForResult(intent, 0)
        }
    }

    fun onBackButtonClicked(view: View){
        finish()
    }
}
