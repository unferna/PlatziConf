package dev.fernandoflorez.platziconf.view.ui.fragments


import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

import dev.fernandoflorez.platziconf.R
import dev.fernandoflorez.platziconf.model.Location

class LocationFragment: Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private lateinit var localLocation: Location

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        localLocation = Location()
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val zoom = 16f
        val centerMap = LatLng(localLocation.latitude, localLocation.longitude)
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap, zoom))

        val markerOptions = MarkerOptions()
        markerOptions.position(centerMap)
        markerOptions.title("Platzi Conf 2020")

        val bitmap = context?.applicationContext?.let { ContextCompat.getDrawable(it, R.drawable.logo_platzi) } as BitmapDrawable
        val smallMarker = Bitmap.createScaledBitmap(bitmap.bitmap, 150, 150, false)

        markerOptions.icon( BitmapDescriptorFactory.fromBitmap(smallMarker) )
        googleMap?.addMarker( markerOptions )
        googleMap?.setOnMarkerClickListener(this)

        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.custom_map))
    }

    override fun onMarkerClick(target: Marker?): Boolean {
        val bundle = bundleOf("location" to localLocation)
        findNavController().navigate(R.id.locationDetailsDialogFragment, bundle)
        return true
    }
}
