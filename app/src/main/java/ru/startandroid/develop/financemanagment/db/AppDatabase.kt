package ru.startandroid.develop.financemanagment.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.startandroid.develop.financemanagment.models.TransactionStoryEntity

@Database(
    entities = [
        TransactionStoryEntity::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dbDao(): RoomDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun instance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Finance"
                ).build()
                Companion.instance = instance
                instance
            }

        }
    }
}