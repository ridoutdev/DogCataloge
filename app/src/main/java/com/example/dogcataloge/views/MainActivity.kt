package com.example.dogcataloge.views
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogcataloge.adapters.AdapterBreed
import com.example.dogcataloge.databinding.ActivityMainBinding
import com.example.dogcataloge.view_models.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterBreed: AdapterBreed
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observer()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observer() {
        mainViewModel.dogs.observe(this, Observer  {
            adapterBreed = AdapterBreed(it)
            binding.dogsText.layoutManager = LinearLayoutManager(this)
            binding.dogsText.adapter = adapterBreed
            adapterBreed.notifyDataSetChanged()
        })
    }
}
