package com.example.dogcataloge.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "puppies")
data class Dogs (
    @PrimaryKey(autoGenerate = true) val id: Int,
    var name: String?,
    var image: String?
        )