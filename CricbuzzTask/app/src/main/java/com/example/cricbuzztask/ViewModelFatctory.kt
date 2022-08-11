package com.example.cricbuzztask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cricbuzztask.db.MyRepo


class ViewModelFatctory (private val repo: MyRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyViewModel(repo) as T
    }
}