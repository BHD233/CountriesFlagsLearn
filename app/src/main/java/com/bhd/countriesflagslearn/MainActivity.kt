package com.bhd.countriesflagslearn

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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

        //check permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
                101)
        }
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
