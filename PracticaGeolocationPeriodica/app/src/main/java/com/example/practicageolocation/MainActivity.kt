package com.example.practicageolocation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.practicageolocation.ui.theme.PracticaGeolocationTheme
import com.example.practicageolocation.ui.vm.LocationPageViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberUpdatedMarkerState

class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback


    private lateinit var vm: LocationPageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        vm = LocationPageViewModel()

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                for (location in locationResult.locations) {
                    vm.locationAcquired(location.latitude, location.longitude)
                }
            }
        }
        enableEdgeToEdge()
        setContent {
            PracticaGeolocationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LocationPage(
                        modifier = Modifier.padding(innerPadding),
                        startLocationUpdates = {
                            startLocationUpdates()
                        },
                        vm,
                        stopLocationUpdates = {
                            stopLocationUpdates()
                        }
                    )
                }
            }
        }
    }

    fun stopLocationUpdates() {
        vm.stopRequestingLocationUpdates()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    fun startLocationUpdates() {
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
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1
            )

            return
        }
        vm.startRequestingLocationUpdates()
        fusedLocationClient.requestLocationUpdates(
            LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY,
                5000L
            ).apply {
                setMinUpdateIntervalMillis(5000L)
                setMaxUpdateDelayMillis(100L)
                setWaitForAccurateLocation(false)
            }.build(),
            locationCallback,
            Looper.getMainLooper()
        )
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String?>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
        if (requestCode == 1) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                startLocationUpdates()
            }
        }
    }
}

@Composable
fun LocationPage(
    modifier: Modifier = Modifier,
    startLocationUpdates: () -> Unit,
    vm: LocationPageViewModel,
    stopLocationUpdates: () -> Unit
) {
    // Para activar solo cuando tenga permisos de ubicación activados
    val hasLocationPermissions = ActivityCompat.checkSelfPermission(
        LocalContext.current,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
        LocalContext.current,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED


    val state = vm.state.collectAsState()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(
                -17.783475, -63.182023
            ), 10f
        )
    }
//    LaunchedEffect(state.value.requestLocationUpdates) {
//
//    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Aquí se mostrará la última ubicación obtenida:")
        Text("Latitud: ${state.value.lastLocation?.latitude}")
        Text("Longitud: ${state.value.lastLocation?.longitude}")
        Row{
            Button(
                enabled = !state.value.requestLocationUpdates,
                onClick = {
                    startLocationUpdates()
                }
            ) {
                Text("Escuchar ubicación")
            }
            Button(
                enabled = state.value.requestLocationUpdates,
                onClick = {
                    stopLocationUpdates()
                }
            ) {
                Text("Detener ubicación")
            }
        }

        if (hasLocationPermissions) {
            GoogleMap(
                modifier = modifier
                    .weight(1f)
                    .fillMaxWidth(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(
                    isMyLocationEnabled = true //hasLocationPermissions si es que no tienen un if antes.
                ),
                uiSettings = MapUiSettings(
                    zoomControlsEnabled = true,
                    myLocationButtonEnabled = true
                )
            ) {
                Polyline(
                    points = state.value.allLocations,
                    color = Color.Blue,
                    width = 8f
                )
                for (location in state.value.allLocations) {
                    Marker(
                        state = rememberUpdatedMarkerState(position = location),
                        title = "Ubicación obtenida",
                        snippet = "Lat: ${location.latitude}, Lng: ${location.longitude}"
                    )
                }
            }
        } else {
            Text("Permisos de ubicación no otorgados. Por favor, otórgalos para ver el mapa.")
        }
    }

}
