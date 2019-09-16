package com.svitlanamozharovska.agronomist

import android.graphics.BitmapFactory
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.svitlanamozharovska.agronomist.databinding.MapFragmentBinding
import kotlinx.android.synthetic.main.map_fragment.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_fragment.*


class MapFragment : BaseFragment<MapFragmentBinding, MapViewModel>(), OnMapReadyCallback {

    override fun getLayoutId(): Int = R.layout.map_fragment

    override fun getViewModelClass(): Class<MapViewModel> = MapViewModel::class.java

    private lateinit var lastLocation: Location

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private val KEY_CAMERA_POSITION = "camera_position"
    private val KEY_LOCATION = "location"

    private lateinit var googleMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(view.context)


    }

    override fun onMapReady(map: GoogleMap?) {
        Log.d("Google map", "onMapReady")
        if (map != null) {
            googleMap = map
            googleMap.uiSettings.isMyLocationButtonEnabled = false
            googleMap.isMyLocationEnabled = true
            //map.uiSettings.isZoomControlsEnabled = true
            //map.setOnMarkerClickListener(this)

            getCurrentLocation()

            viewModel.callWebService()

            viewModel.visibility.observe(this, Observer {
                Log.d("retrofit", "visibility")

                if (viewModel.accountMutableList.value != null) {

                    viewModel.accountMutableList.value!!.forEach {
                        val latLng = LatLng(it.latitude, it.longitude)
                        addNewMarker(latLng, it.description, it.iconUrl)

                    }
                }


            })


            val myPlace = LatLng(40.73, -73.99)  // this is New York
            googleMap.addMarker(MarkerOptions().position(myPlace).title("My Favorite City"))
            //map.moveCamera(CameraUpdateFactory.newLatLngZoom(myPlace, 20f))
        }


    }

    private fun addNewMarker(latLng: LatLng, description: String, url: String) {
        val markerOptions = MarkerOptions()
        markerOptions.position(latLng)
            .title("Location Details").icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(resources,
                R.drawable.ic_tabbar_my_product_list_selected)))

        val info = InfoWindowData(description, url)

        //markerOptions.icon(BitmapDescriptorFactory.fromBitmap(
        //        BitmapFactory.decodeResource(resources, R.mipmap.ic_user_location)))

        val customInfoWindow = this.context?.let { CustomInfoWindowGoogleMap(it) }

        googleMap.setInfoWindowAdapter(customInfoWindow)

        val marker = googleMap.addMarker(markerOptions)
        marker.tag = info
        //googleMap.addMarker(MarkerOptions().position(latLng).title(string))

    }

    private fun getCurrentLocation() {
        this.activity?.let {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener(it) { location ->
                if (location != null) {
                    lastLocation = location
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 13f))
                }

            }
        }
    }


    override fun onResume() {
        mapView.onResume()
        super.onResume()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onLowMemory() {
        mapView.onLowMemory()
        super.onLowMemory()
    }


}