package com.sventripikal.sk8_shop.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sventripikal.sk8_shop.Priority
import com.sventripikal.sk8_shop.TAG
import com.sventripikal.sk8_shop.timber


private const val MESSAGE_INIT = "[ViewModel] INIT"
private const val MESSAGE_CLEARED = "[ViewModel] ON-CLEARED"

class ApplicationViewModel : ViewModel() {

    // user login userName
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName

    // user login password
    private val _userPassword = MutableLiveData<String>()
    val userPassword: LiveData<String>
        get() = _userPassword

    // user Welcome greeting
    private val _welcomeGreeting = MutableLiveData<String>()
    val welcomeGreeting: LiveData<String>
        get() = _welcomeGreeting

    // assign userName value - lambda
    val updateUserName: (String) -> Unit = {
        _userName.value = it
    }

    // assign userPassword value - lambda
    val updateUserPassword: (String) -> Unit = {
        _userPassword.value = it
    }

    // assign greeting value - lambda
    val updateGreeting: (String) -> Unit = { user ->
        _welcomeGreeting.value = "Hello ${user.replaceFirstChar{it.uppercase()}}, Welcome to SK8-Shop!"
    }


    init {  // log
        timber(TAG, MESSAGE_INIT, Priority.VERBOSE)
    }


    // Lifecycle method
    override fun onCleared() {
        super.onCleared()
        timber(TAG, MESSAGE_CLEARED, Priority.ERROR)
    }
}