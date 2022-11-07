package com.vishal2376.sugoianime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.vishal2376.sugoianime.viewmodels.AnimeViewModalFactory
import com.vishal2376.sugoianime.viewmodels.AnimeViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //testing
        val repository = (application as AnimeApplication).repository
        val viewModel = ViewModelProvider(this,AnimeViewModalFactory(repository))[AnimeViewModel::class.java]

        viewModel.popularAnime.observe(this){
            Log.e("MainActivity",it.toString())
        }

    }
}