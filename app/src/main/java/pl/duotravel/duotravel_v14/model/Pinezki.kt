package pl.duotravel.duotravel_v14.model

import android.icu.text.CaseMap.Title
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pinezki(
@PrimaryKey(autoGenerate = true) val id: Long = 0,
val latitude: Double,
val longitude: Double,
val titlePinezki: String
)