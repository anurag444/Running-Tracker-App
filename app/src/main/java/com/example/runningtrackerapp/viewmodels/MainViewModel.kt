package com.example.runningtrackerapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.runningtrackerapp.repository.MainRepository

class MainViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
): ViewModel() {
}