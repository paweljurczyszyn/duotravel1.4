package pl.duotravel.duotravel_v14.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pl.duotravel.duotravel_v14.model.Zdjecia

@Database(entities = [Zdjecia::class], version = 2, exportSchema = false)
abstract class ZdjeciaDatabase : RoomDatabase() {

    abstract fun zdjeciaDao(): ZdjeciaDao

    companion object {
        @Volatile
        private var INSTANCE: ZdjeciaDatabase? = null

        fun getDatabase(context: Context): ZdjeciaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ZdjeciaDatabase::class.java,
                    "zdjecia_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                instance
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}