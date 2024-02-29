package pl.duotravel.duotravel_v14.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pl.duotravel.duotravel_v14.model.Zdjecia

@Dao
interface ZdjeciaDao {
        @Query("SELECT * FROM PHOTOS")
        fun getAllImages(): LiveData<List<Zdjecia>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertImage(image: Zdjecia)


        @Delete
        suspend fun deleteImage(image: Zdjecia)
}