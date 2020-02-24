package com.bhd.countriesflagslearn

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onLearnButtonClicked(view: View){
        val intent = Intent(this, LearnActivity::class.java)

        startActivity(intent)
    }

    fun onFavoriteButtonClicked(view: View){
        val intent = Intent(this, FavoriteCountriesActivity::class.java)

        startActivity(intent)
    }

    fun onTestButtonClicked(view: View){
        val intent = Intent(this, TestActivity::class.java)

        startActivity(intent)
    }
}
