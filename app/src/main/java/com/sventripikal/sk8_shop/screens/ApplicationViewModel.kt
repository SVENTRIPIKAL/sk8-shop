package com.sventripikal.sk8_shop.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sventripikal.sk8_shop.Priority
import com.sventripikal.sk8_shop.TAG
import com.sventripikal.sk8_shop.timber


private const val MESSAGE_CREATED = "VIEW-MODEL CREATED"
private const val MESSAGE_DESTROYED = "VIEW-MODEL DESTROYED"

class ApplicationViewModel: ViewModel() {

    // user login email
    private val _userEmail = MutableLiveData<String>()
    val userEmail: LiveData<String>
        get() = _userEmail

    // user login password
    private val _userPassword = MutableLiveData<String>()
    val userPassword: LiveData<String>
        get() = _userPassword


    // assign userEmail value lambda
    val updateUserEmail: (String) -> Unit = {
        _userEmail.value = it
    }

    // assign userPassword value lambda
    val updateUserPassword: (String) -> Unit = {
        _userPassword.value = it
    }


    init {
        timber(TAG, MESSAGE_CREATED, Priority.VERBOSE)
    }


    override fun onCleared() {
        super.onCleared()
        timber(TAG, MESSAGE_DESTROYED, Priority.ERROR)
    }
}