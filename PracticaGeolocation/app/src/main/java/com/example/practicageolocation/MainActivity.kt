package com.example.practicageolocation

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.practicageolocation.ui.theme.PracticaGeolocationTheme
import com.example.practicageolocation.ui.vm.LocationPageViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberUpdatedMarkerState

class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var vm: LocationPageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        vm = LocationPageViewModel()
        enableEdgeToEdge()
        setContent {
            PracticaGeolocationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LocationPage(
                        modifier = Modifier.padding(innerPadding),
                        getLocation = {
                            getLastLocation()
                        },
                        vm
                    )
                }
            }
        }
    }

    fun getLastLocation() {
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
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude
//                    Toast.makeText(
//                        this,
//                        "Latitud: $latitude\nLongitud: $longitude",
//                        Toast.LENGTH_SHORT
//                    ).show()
                    vm.setLocation(latitude.toString(), longitude.toString())
                }
            }
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
                getLastLocation()
            }
        }
    }
}

@Composable
fun LocationPage(
    modifier: Modifier = Modifier,
    getLocation: () -> Unit,
    vm: LocationPageViewModel
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
    val currentLocationMarkerState = rememberUpdatedMarkerState(
        position = LatLng(
            state.value.latitude.toDoubleOrNull() ?: 0.0,
            state.value.longitude.toDoubleOrNull() ?: 0.0
        )
    )
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(
                state.value.latitude.toDoubleOrNull() ?: 0.0,
                state.value.longitude.toDoubleOrNull() ?: 0.0
            ), 10f
        )
    }
    LaunchedEffect(state.value.latitude) {
        if (hasLocationPermissions){
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        state.value.latitude.toDoubleOrNull() ?: 0.0,
                        state.value.longitude.toDoubleOrNull() ?: 0.0
                    ), 15f
                ),
                durationMs = 1000
            )
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Aquí se mostrará la ubicación del usuario")
        Text("Latitud: ${state.value.latitude}")
        Text("Longitud: ${state.value.longitude}")
        Button(
            onClick = {
                getLocation()
            }
        ) {
            Text("Obtener ubicación")
        }

        if(hasLocationPermissions){
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
                Marker(
                    state = currentLocationMarkerState,
                    title = "Ubicación actual",
                    snippet = "Marcador de la ubicación actual del usuario"
                )
            }
        }else{
            Text("Permisos de ubicación no otorgados. Por favor, otórgalos para ver el mapa.")
        }
    }

}
