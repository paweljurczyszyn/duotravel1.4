package pl.duotravel.duotravel_v14

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import pl.duotravel.duotravel_v14.databinding.ActivityMainBinding
import pl.duotravel.duotravel_v14.databinding.ActivityMapyBinding
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapyBinding  //uruchamia binding w celu edycji tekstu z xml tutaj w kotlinie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapyBinding.inflate(layoutInflater)
        setContentView(binding.root)  //root żeby pokazywało całość z kodu xml a nie konkretny tekst
    // Log.d("LIFECYCLE", "onCreate") // polecenie żeby tworzył się log przy otwieraniu aplikacji
    }


 /*   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_mapy)

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapa) as? SupportMapFragment
        mapFragment?.getMapAsync { this }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val sydney = LatLng(-33.852, 151.211)
        googleMap.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney")
        )
    }

  */
}