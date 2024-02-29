package pl.duotravel.duotravel_v14

import android.content.Context
import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import com.google.android.gms.maps.GoogleMap
import pl.duotravel.duotravel_v14.databinding.ActivityMapyBinding
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.duotravel.duotravel_v14.db.PinezkiDatabase
import pl.duotravel.duotravel_v14.model.Pinezki
import kotlin.coroutines.CoroutineContext

class MapyActivity : AppCompatActivity(), OnMapReadyCallback, CoroutineScope {
    private lateinit var binding: ActivityMapyBinding
    private lateinit var googleMap: GoogleMap
    private val markersList = ArrayList<MarkerOptions>()
    private val markersPrefsKey = "markers"
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        job = Job()

        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment
        mapFragment.getMapAsync(this)

        launch {
            loadMarkersFromDatabase()
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.setOnMapClickListener { latLng -> showMarkerNameDialog(latLng) }
    }

    private fun showMarkerNameDialog(latLng: LatLng) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Wprowadź datę oraz nazwę lokacji")

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        builder.setPositiveButton("Dodaj") { dialog, _ ->
            val markerTitle = input.text.toString()
            val marker = MarkerOptions().position(latLng).title(markerTitle)
            runOnUiThread {
                googleMap.addMarker(marker)  // Dodaje znacznik na wątku głównym
            }
            markersList.add(marker)
            launch {
                saveMarkersToDatabase()
            }

            Toast.makeText(this, "Dodano pinezkę na mapie", Toast.LENGTH_SHORT).show()

            dialog.dismiss()
        }

        builder.setNegativeButton("Anuluj") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }

    private suspend fun saveMarkersToDatabase() {
        withContext(Dispatchers.IO) {
            val database = PinezkiDatabase.getDatabase(this@MapyActivity)
            val dao = database.pinezkiDao()
            for (marker in markersList) {
                val pin = Pinezki(
                    titlePinezki = marker.title.toString(),
                    latitude = marker.position.latitude,
                    longitude = marker.position.longitude)
                dao.insertPin(pin)
            }
        }
    }

    private suspend fun loadMarkersFromDatabase() {
        withContext(Dispatchers.IO) {
            val database = PinezkiDatabase.getDatabase(this@MapyActivity)
            val dao = database.pinezkiDao()
            val pins = dao.getAllPins()
            markersList.clear()
            for (pin in pins) {
                val marker = MarkerOptions().position(LatLng(pin.latitude, pin.longitude))
                    .title(pin.titlePinezki)
                markersList.add(marker)
            }
            runOnUiThread {
                showMarkersOnMap()
            }
        }
    }

    private fun showMarkersOnMap() {
        for (marker in markersList) {
            googleMap.addMarker(marker)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
