package pl.duotravel.duotravel_v14.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "PHOTOS", indices = [Index(value = ["uri"], unique = true)])
data class Zdjecia(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val uri: String
)
