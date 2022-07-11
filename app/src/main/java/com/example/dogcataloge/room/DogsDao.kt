package com.example.dogcataloge.room

import androidx.room.*
import com.example.dogcataloge.models.Dogs
import com.example.dogcataloge.models.DogsBreeds

@Dao
interface DogsDao {

    @Query("SELECT * FROM dogsList")
    fun getDogs(): List<DogsBreeds>

    @Query("SElECT * FROM puppies")
    fun getObject() : List<Dogs>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDogs(dogList: List<DogsBreeds>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDogObject(dogsObject : List<Dogs>)

    @Delete
    fun deleteBreeds (dog: List<DogsBreeds>)

    @Delete
    fun deleteDog (dog: List<Dogs>)

}