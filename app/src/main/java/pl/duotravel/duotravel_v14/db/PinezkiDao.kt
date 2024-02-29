package pl.duotravel.duotravel_v14.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pl.duotravel.duotravel_v14.model.Pinezki

@Dao
interface PinezkiDao {
    @Query("SELECT * FROM Pinezki")
    fun getAllPins(): List<Pinezki>

    @Insert
    fun insertPin(pin: Pinezki)

    @Delete
    fun deletePin(pin: Pinezki)
}