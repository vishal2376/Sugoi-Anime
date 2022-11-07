package com.vishal2376.sugoianime

import android.app.Application
import com.vishal2376.sugoianime.api.AnimeService
import com.vishal2376.sugoianime.api.RetrofitHelper
import com.vishal2376.sugoianime.repository.AnimeRepository

class AnimeApplication : Application() {

    lateinit var repository: AnimeRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val animeService = RetrofitHelper.getInstance().create(AnimeService::class.java)
        repository = AnimeRepository(animeService)
    }
}