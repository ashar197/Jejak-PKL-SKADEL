package com.skadel.jejakpklskadel.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _welcomeGreeting = MutableLiveData<String>().apply {
        value = "Selamat Datang,"
    }
    val welcomeGreeting: LiveData<String> = _welcomeGreeting

    private val _userName = MutableLiveData<String>().apply {
        value = "Rekan PKL SKADEL"
    }
    val userName: LiveData<String> = _userName

}