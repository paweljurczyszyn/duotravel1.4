package pl.duotravel.duotravel_v14.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pl.duotravel.duotravel_v14.model.Pinezki

@Database(entities = [Pinezki::class], version = 1)
abstract class PinezkiDatabase : RoomDatabase() {

    abstract fun pinezkiDao(): PinezkiDao

    companion object {
        @Volatile
        private var INSTANCE: PinezkiDatabase? = null

        fun getDatabase(context: Context): PinezkiDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PinezkiDatabase::class.java,
                    "pin-db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

