package pl.duotravel.duotravel_v14

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.duotravel.duotravel_v14.adapter.ImageAdapter
import pl.duotravel.duotravel_v14.db.ZdjeciaDatabase
import pl.duotravel.duotravel_v14.model.Zdjecia

class ZdjeciaActivity : AppCompatActivity() {

    private lateinit var imageList: MutableList<Uri>
    private lateinit var adapter: ImageAdapter
    private lateinit var db: ZdjeciaDatabase

    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageUri = result.data?.data
                imageUri?.let {
                    saveImageToDatabase(it)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zdjecia)

        imageList = mutableListOf()

        setupViews()
        setupDatabase()
        setupRecyclerView()

        // Obserwuj zmiany w bazie danych
        db.zdjeciaDao().getAllImages().observe(this, Observer { images ->
            images?.let {
                imageList.clear()
                imageList.addAll(it.map { image -> Uri.parse(image.uri) })
                adapter.notifyDataSetChanged()
                updateCardViewVisibility()
            }
        })
    }

    private fun setupViews() {
        val fabAddPhoto: FloatingActionButton = findViewById(R.id.fabAddPhoto)

        fabAddPhoto.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            getContent.launch(gallery)
        }
    }

    private fun setupDatabase() {
        db = ZdjeciaDatabase.getDatabase(applicationContext)
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        val cardView: CardView = findViewById(R.id.cardViewPhoto)

        adapter = ImageAdapter(imageList, cardView)
        recyclerView.adapter = adapter
    }

    private fun saveImageToDatabase(uri: Uri) {
        CoroutineScope(Dispatchers.IO).launch {
            val imageEntity = Zdjecia(uri = uri.toString())
            db.zdjeciaDao().insertImage(imageEntity)
        }
    }

    private fun updateCardViewVisibility() {
        val cardView: CardView = findViewById(R.id.cardViewPhoto)
        if (imageList.isEmpty()) {
            cardView.visibility = View.VISIBLE
        } else {
            cardView.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("ZdjeciaActivity", "onResume called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ZdjeciaActivity", "onDestroy called")
    }
}
