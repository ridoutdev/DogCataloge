package com.example.dogcataloge.room
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dogcataloge.models.Dogs
import com.example.dogcataloge.models.DogsBreeds

@Database(entities = [DogsBreeds::class, Dogs::class], version = 5)
abstract class DogsDB: RoomDatabase() {
    abstract val dogDao : DogsDao
}

private lateinit var INSTANCE: DogsDB

fun getDb(context: Context): DogsDB {
    synchronized(DogsDB::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext, DogsDB::class.java,
            "dogsDBRoom")
                .fallbackToDestructiveMigration()
                .build()
        }
        return INSTANCE
    }
}