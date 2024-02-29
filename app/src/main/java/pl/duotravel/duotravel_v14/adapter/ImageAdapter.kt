package pl.duotravel.duotravel_v14.adapter

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.duotravel.duotravel_v14.R
import pl.duotravel.duotravel_v14.ZdjeciaActivity
import pl.duotravel.duotravel_v14.db.ZdjeciaDao
import pl.duotravel.duotravel_v14.model.Zdjecia
import java.io.File
import java.io.IOException

class ImageAdapter(private val imageList: MutableList<Uri>, private val cardView: CardView) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
            val imageUri = imageList[position]

            // Ustawianie klikalności kafelka
            holder.itemView.setOnClickListener {
                Log.d("ImageAdapter", "Clicked on image at position $position, imageUri: $imageUri")
                if (isImageAvailable(imageUri)) {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setDataAndType(imageUri, "image/*")
                    holder.itemView.context.startActivity(intent)
                } else {
                    // Zdjęcie nie jest dostępne, usuwamy je z listy
                    imageList.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, imageList.size)

                    Toast.makeText(
                        cardView.context,
                        "Zdjęcie nie jest już dostępne w galerii",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            Glide.with(holder.imageView.context)
                .load(imageUri)
                .centerCrop()
                .into(holder.imageView)
        }

    private fun isImageAvailable(imageUri: Uri): Boolean {
        return try {
            val contentResolver = cardView.context.contentResolver
            val inputStream = contentResolver.openInputStream(imageUri)
            inputStream?.close()
            true
        } catch (e: IOException) {
            false
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun getItemId(position: Int): Long {
        return imageList[position].hashCode().toLong()
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}



