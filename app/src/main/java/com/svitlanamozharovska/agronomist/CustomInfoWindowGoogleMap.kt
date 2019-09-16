package com.svitlanamozharovska.agronomist

import android.app.Activity
import android.content.Context
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_marker_info_window.view.*

class CustomInfoWindowGoogleMap(val context: Context) : GoogleMap.InfoWindowAdapter {

    override fun getInfoContents(p0: Marker?): View {

        val mInfoView = (context as Activity).layoutInflater.inflate(R.layout.custom_marker_info_window, null)
        val mInfoWindow: InfoWindowData? = p0?.tag as InfoWindowData?

        mInfoView.descriptionInfoTV.text = mInfoWindow?.description
        Picasso.get().load("http://app.crazyagro.com/admin/img/${mInfoWindow?.url}").error(R.color.colorWhite)
            .into(mInfoView.infoIV)


        return mInfoView
    }

    override fun getInfoWindow(p0: Marker?): View? {
        return null
    }
}