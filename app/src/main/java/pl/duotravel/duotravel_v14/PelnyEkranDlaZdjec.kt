package pl.duotravel.duotravel_v14

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class PelnyEkranDlaZdjec : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pelny_ekran_dla_zdjec)

        val imageUriString = intent.getStringExtra("imageUri")
        if (imageUriString != null) {
            val imageUri = Uri.parse(imageUriString)
            // Wyświetl zdjęcie na pełnym ekranie
            val imageView: ImageView = findViewById(R.id.pelnyEkranImageView)

            Glide.with(this)
                .load(imageUri)
                .centerInside()
                .into(imageView)
        } else {
            // Obsłuż sytuację, gdy brakuje adresu URI zdjęcia
            Toast.makeText(this, "Brak adresu URI zdjęcia", Toast.LENGTH_SHORT).show()
            finish() // Zakończ aktywność
        }
    }
}

