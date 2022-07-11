package com.example.dogcataloge

import android.app.Application
import androidx.room.Room
import com.example.dogcataloge.room.DogsDB

class DogsApplication: Application() {
    companion object {
        lateinit var database: DogsDB
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this,
        DogsDB::class.java,
        "DogsDB")
            .build()
    }
}