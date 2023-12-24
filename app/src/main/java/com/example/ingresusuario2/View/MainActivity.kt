package com.example.ingresusuario2.View

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.ingresusuario2.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {

    //Creamos una instancia de fusedlocation para acceder a los servicios de ubicacion de google.
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    //Guardamos la ubicacion en un objeto string.
    lateinit var ubicacionString: String

    //Instanciamos el objeto viewBinding
    private lateinit var mainBinding: ActivityMainBinding

     val MY_PERMISSIONS_REQUEST_LOCATION = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Configramos el objeto viewBinding
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)


        getLocation()
    }

    fun getLocation(){
        //Configuramos el objeto fusedlocation que utilizaremos.
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                MY_PERMISSIONS_REQUEST_LOCATION
            )
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->

            if (location != null) {
                val longitud = location.longitude
                val latitud = location.latitude

                ubicacionString = " ${latitud} ${longitud}"
                Log.e("Ubicacion","la ubicacion es ${ubicacionString}")
            } else {

                Log.e("Ubicacion","La ubicacion es nula ${ubicacionString}")

            }

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                // Verificar si el usuario concedió los permisos
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation()
                } else {
                    // Permisos no concedidos, puedes mostrar un mensaje o tomar otra acción
                    Log.e("Ubicacion FALLO","ni idea porque.")
                }
            }
        }
    }

}