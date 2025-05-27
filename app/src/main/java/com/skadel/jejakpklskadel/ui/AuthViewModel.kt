package com.skadel.jejakpklskadel.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth

    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?> = _currentUser

    private val _logoutComplete = MutableLiveData<Boolean>()
    val logoutComplete: LiveData<Boolean> = _logoutComplete

    init {
        _currentUser.value = auth.currentUser
        auth.addAuthStateListener { firebaseAuth ->
            _currentUser.value = firebaseAuth.currentUser
        }
    }

    fun fetchCurrentUser() {
        _currentUser.value = auth.currentUser

    }

    fun logoutUser() {
        auth.signOut()
        _logoutComplete.value = true
        Log.d("ProfileViewModel", "User logged out")
}
fun onLogOutNavigationComplete() {
    _logoutComplete.value = false
}

}