package com.bhd.countriesflagslearn

import android.os.Environment
import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import kotlinx.android.parcel.Parcelize
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.lang.Exception

@Parcelize
class ListCountries(var listCountries: MutableList<Country> = arrayListOf<Country>()) : Parcelable {
    fun getCountriesOnline(filename: String){
        var okhttp = OkHttpClient()
        var jsonString = ""

        val request = okhttp3.Request.Builder()
            .url("https://raw.githubusercontent.com/BHD233/TestKotlin/master/countries")
            .build()

        okhttp.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle this
                val a = 1
            }

            override fun onResponse(call: Call, response: okhttp3.Response) {
                // Handle this\
                jsonString = response.body().string()

                val jsonObject = JSONObject(jsonString)

                val arrayCountries = jsonObject.getJSONArray("country")

                for (i in 0..arrayCountries.length() - 1){
                    val curCountryJson = arrayCountries.getJSONObject(i)

                    var curCountry = Country()
                    curCountry.name = curCountryJson.getString("name")
                    curCountry.code = curCountryJson.getString("code")
                    curCountry.flag = "https://www.countryflags.io/" + curCountry.code + "/flat/64.png"

                    listCountries.add(curCountry)
                }
            }
        })

        //wait for get all list
        while(listCountries.size == 0){

        }

        //write to file
        val path = Environment.getExternalStorageDirectory().toString()

        val file = File(path, filename)

        file.writeText(jsonString)
    }

    fun getCountriesLocal(fileName: String) : Boolean{
        //read file
        val path = Environment.getExternalStorageDirectory().toString()

        val file = File(path, fileName)

        var jsonString : String
        try {
            jsonString = file.readText()
        } catch (e: Exception){
            //cannot read file
            return false
        }

        val jsonObject = JSONObject(jsonString)

        val arrayCountries = jsonObject.getJSONArray("country")

        for (i in 0..arrayCountries.length() - 1){
            val curCountryJson = arrayCountries.getJSONObject(i)

            var curCountry = Country()
            curCountry.name = curCountryJson.getString("name")
            curCountry.code = curCountryJson.getString("code")
            curCountry.flag = "https://www.countryflags.io/" + curCountry.code + "/flat/64.png"

            listCountries.add(curCountry)
        }

        return true
    }

    fun writeToFile(fileName: String){
        val path = Environment.getExternalStorageDirectory().toString()

        val file = File(path, "ListFavoriteCountries.txt")

        val gson = Gson()

        var jsonString = gson.toJson(listCountries)

        jsonString = "{country:" + jsonString + "}"

        file.writeText(jsonString)
    }
}

@Parcelize
data class Country(var name: String = "", var code: String = "", var flag: String = "") : Parcelable {
}

data class Question(var name: String = "", var rightAns: String = "", var wrongAns: String = "")