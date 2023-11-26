package pl.duotravel.duotravel_v14

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import pl.duotravel.duotravel_v14.databinding.ActivityMainBinding
import pl.duotravel.duotravel_v14.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPrzejdzDoAplikacji.setOnClickListener {
            if (hasStoragePermissions()) {
                // Masz już uprawnienia, możesz kontynuować działanie aplikacji
                val explicitIntent = Intent(application, StronaGlowna::class.java)
                startActivity(explicitIntent)
            } else {
                // Poproś użytkownika o uprawnienia
                requestStoragePermissions()
            }
        }
    }

    private fun hasStoragePermissions(): Boolean {
        return EasyPermissions.hasPermissions(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    private fun requestStoragePermissions() {
        EasyPermissions.requestPermissions(
            this,
            "Aplikacja potrzebuje dostępu do pamięci wewnętrznej.",
            123, // Dowolny unikalny kod żądania uprawnień
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        // Uprawnienia zostały udzielone, możesz kontynuować działanie aplikacji
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }
}



/*
ORYGINALNY KOD
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import pl.duotravel.duotravel_v14.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding   //uruchamia binding w celu edycji tekstu z xml tutaj w kotlinie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)  //root żeby pokazywało całość z kodu xml a nie konkretny tekst
        // Log.d("LIFECYCLE", "onCreate") // polecenie żeby tworzył się log przy otwieraniu aplikacji

        binding.buttonPrzejdzDoAplikacji.setOnClickListener{  // korygowanie przycisku / button
             val explicitIntent = Intent(application, StronaGlowna::class.java) //instrukcja dla przycisku zeby
             startActivity(explicitIntent)                                      //przenosil do strony glownej
        }
    }

 */
