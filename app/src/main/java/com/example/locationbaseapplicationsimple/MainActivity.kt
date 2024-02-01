package com.example.locationbaseapplicationsimple


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : Activity() {

    private lateinit var btnShowLocation: Button
    private lateinit var btnsetLocation: TextView
    private val REQUEST_CODE_PERMISSION = 2
    private val mPermission = Manifest.permission.ACCESS_FINE_LOCATION

    // GPSTracker class
    private lateinit var gps: GPSTracker

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            if (ContextCompat.checkSelfPermission(this, mPermission)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(mPermission),
                    REQUEST_CODE_PERMISSION
                )
                // If any permission above not allowed by user, this condition will execute every time,
                // else your else part will work
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        btnShowLocation = findViewById(R.id.button)
        btnsetLocation = findViewById(R.id.setMapText)

        // Show location button click event
        btnShowLocation.setOnClickListener(View.OnClickListener {
            // Create class object
            gps = GPSTracker(this)

            // Check if GPS is enabled
            if (gps.canGetLocation()) {
                val latitude = gps.getLatitude()
                val longitude = gps.getLongitude()

                // \n is for a new line
               btnsetLocation.text = "your location parameters are: \n Latitude: " + latitude + " , \n longitude: " + longitude
                Toast.makeText(
                    applicationContext,

                    "Your Location is - \nLat: $latitude\nLong: $longitude",


                    Toast.LENGTH_LONG
                ).show()
            } else {
                // Can't get location
                // GPS or Network is not enabled
                // Ask the user to enable GPS/network in settings
                gps.showSettingsAlert()
            }
        })
    }
}




/*
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermmision()
    }
    fun checkPermmision()
    {
        if(Build.VERSION.SDK_INT>=23)
        {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),MY_PERMISSIONS_REQUEST_LOCATION_CODE)
                return
            }
        }
        //GetUserLocation()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode)
        {
            MY_PERMISSIONS_REQUEST_LOCATION_CODE->{
                if (grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    //GetUserLocation()
                }
                else
                {
                    Toast.makeText(this,"Permisiion not granted", Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object{
        private const val MY_PERMISSIONS_REQUEST_LOCATION_CODE=123
    }




}*/