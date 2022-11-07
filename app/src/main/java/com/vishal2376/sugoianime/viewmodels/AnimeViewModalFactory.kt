package com.vishal2376.sugoianime.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vishal2376.sugoianime.repository.AnimeRepository

class AnimeViewModalFactory(private val repository: AnimeRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimeViewModel(repository) as T
    }
}