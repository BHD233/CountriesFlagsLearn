package com.bhd.countriesflagslearn


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide


class LearnGridViewAdapter(private var c: Context, var infor: ListCountries) : BaseAdapter() {

    override fun getCount(): Int   {  return infor.listCountries.size  }
    override fun getItem(i: Int): Any {  return infor.listCountries[i] }
    override fun getItemId(i: Int): Long { return i.toLong()}

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        var view = view
        if (view == null) {
            //inflate layout resource if its null
            view = LayoutInflater.from(c).inflate(R.layout.learn_box, viewGroup, false)
        }

        //reference textviews and imageviews from our layout
        val imageView = view!!.findViewById<ImageView>(R.id.learnImageView)

        Glide.with(view!!).load(infor.listCountries[i].flag).into(imageView)

        return view
    }
}