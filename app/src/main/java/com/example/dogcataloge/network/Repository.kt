package com.example.dogcataloge.network

import android.util.Log
import com.example.dogcataloge.models.Dogs
import com.example.dogcataloge.models.DogsBreeds
import com.example.dogcataloge.room.DogsDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

class Repository(private val database: DogsDB) {

    suspend fun getDogsList(): List<DogsBreeds>{
        return withContext(Dispatchers.IO) {
            val dogListString : String = service.getListDogs()
            val dogList = parseDogBreedResult(dogListString)

            database.dogDao.insertDogs(dogList)
            database.dogDao.getDogs()
//            database.dogDao.deleteBreeds(dogList)
//            dogList
        }
    }

    private fun parseDogBreedResult(dogListString: String) : List<DogsBreeds>{
        val dogJsonObject = JSONObject(dogListString)
        val messagesDogBreeds = dogJsonObject.getJSONObject("message")

        val arrayJson : JSONArray = messagesDogBreeds.names() as JSONArray
        val statusDogsBreeds = dogJsonObject.getString("status")
        Log.d("BREEDNAMES", arrayJson.toString())

        val lista = mutableListOf<DogsBreeds>()

        for (i in 0 until arrayJson.length()) {
            val dogs = DogsBreeds(0, arrayJson.getString(i), statusDogsBreeds)
            lista.add(dogs)
        }

        return lista
    }

    suspend fun getImagesList() : List<Dogs> {
        return withContext(Dispatchers.IO) {
//            val imgUrl = mutableListOf<Dogs>()
            var imageList : List<Dogs>? = null

            for (i in 0 until breedNames.size){
                val imageString = service.getImages(breedNames[i])
                 imageList = parseImagesDogsResult(imageString)
            }

            database.dogDao.getObject()
            //imageList!!
        }
    }

    private fun parseImagesDogsResult(imageString: String): List<Dogs> {
        val imageJsonObject = JSONObject(imageString)
        val urlList = imageJsonObject.getString("message")
        Log.d("URLLIST" , urlList)

        val dogsList = mutableListOf<Dogs>()
        for (i in breedNames.indices) {
            val dogs = Dogs(0, breedNames[i], urlList)
            dogsList.add(dogs)
        }

        database.dogDao.insertDogObject(dogsList)
//        database.dogDao.deleteDog(dogsList)
        return dogsList
    }

    val breedNames = mutableListOf("affenpinscher","african","airedale","akita","appenzeller",
        "australian","basenji","beagle","bluetick","borzoi","bouvier","boxer","brabancon",
        "briard","buhund","bulldog","bullterrier","cattledog","chihuahua","chow","clumber",
        "cockapoo","collie","coonhound","corgi","cotondetulear","dachshund","dalmatian",
        "dane","deerhound","dhole","dingo","doberman","elkhound","entlebucher","eskimo",
        "finnish","frise","germanshepherd","greyhound","groenendael","havanese","hound",
        "husky","keeshond","kelpie","komondor","kuvasz","labradoodle","labrador","leonberg",
        "lhasa","malamute","malinois","maltese","mastiff","mexicanhairless","mix","mountain",
        "newfoundland","otterhound","ovcharka","papillon","pekinese","pembroke","pinscher",
        "pitbull","pointer","pomeranian","poodle","pug","puggle","pyrenees","redbone","retriever",
        "ridgeback","rottweiler","saluki","samoyed","schipperke","schnauzer","setter","sharpei",
        "sheepdog","shiba","shihtzu","spaniel","springer","stbernard","terrier","tervuren","vizsla",
        "waterdog","weimaraner","whippet","wolfhound")
}