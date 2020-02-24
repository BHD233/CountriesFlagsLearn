package com.bhd.countriesflagslearn

import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import org.json.JSONObject
import java.io.File
import java.lang.Exception

class CountryDetailActivity : AppCompatActivity() {
    var listCountries = ListCountries()
    var pos: Int = 0
    var listFavoriteContries = ListCountries()
    val heartSize = 200
    val hearFilledSize = 100

    val favoriteCountriesFileName = "ListFavoriteCountries.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        listCountries = intent.getParcelableExtra<ListCountries>("data")
        pos = intent.getIntExtra("pos", 0)

        listFavoriteContries.getCountriesLocal(favoriteCountriesFileName)

        getInterface()
    }

    fun getInterface(){
        val textView = findViewById<TextView>(R.id.contryName)
        textView.text = listCountries.listCountries[pos].name

        val imageView = findViewById<ImageView>(R.id.flagImage)
        Glide.with(this).load(listCountries.listCountries[pos].flag).into(imageView)

        //get loveButton img
        getLoveButtonImg()
    }

    fun onNextButtonClicked(view: View){
        if (pos < listCountries.listCountries.size - 1) {
            pos++

            getInterface()
        }
    }

    fun onPreviousButtonClicked(view: View){
        if (pos > 0){
            pos--

            getInterface()
        }
    }

    fun getLoveButtonImg(){
        val imageButton = findViewById<ImageButton>(R.id.loveButton)

        if (listFavoriteContries.listCountries.contains(listCountries.listCountries[pos])){
            Glide.with(this).load(R.drawable.heart_filled).override(hearFilledSize, hearFilledSize).into(imageButton)

        } else{
            Glide.with(this).load(R.drawable.heart).override(heartSize, heartSize).into(imageButton)
        }
    }

    fun editFavoriteList() {
        if (listFavoriteContries.listCountries.contains(listCountries.listCountries[pos])) {
            listFavoriteContries.listCountries.remove(listCountries.listCountries[pos])
        } else {
            listFavoriteContries.listCountries.add(listCountries.listCountries[pos])
        }
    }

    fun onFavorieButtonClicked(view: View){
        editFavoriteList()

        getLoveButtonImg()

        listFavoriteContries.writeToFile(favoriteCountriesFileName)
    }

    fun onBackButtonClicked(view: View){
        finish()
    }
}
