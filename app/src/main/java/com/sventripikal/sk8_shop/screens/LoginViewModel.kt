package com.sventripikal.sk8_shop.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sventripikal.sk8_shop.Priority
import com.sventripikal.sk8_shop.TAG
import com.sventripikal.sk8_shop.TRUE
import com.sventripikal.sk8_shop.timber


private const val MESSAGE_INIT = "[ViewModel] INIT"
private const val MESSAGE_CLEARED = "[ViewModel] ON-CLEARED"

class LoginViewModel: ViewModel() {

    // user login userName
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName

    // user login password
    private val _userPassword = MutableLiveData<String>()
    val userPassword: LiveData<String>
        get() = _userPassword

    // assign userName value - lambda
    val updateUserName: (String) -> Unit = {
        _userName.value = it
    }

    // assign userPassword value - lambda
    val updateUserPassword: (String) -> Unit = {
        _userPassword.value = it
    }

    // check if userName & password not null or blank
    fun editFieldsComplete(): Boolean {
        return userName.value.isNullOrBlank() != TRUE &&
                userPassword.value.isNullOrBlank() != TRUE
    }

    fun greetUser(): String {
        return "Hi $userName! Welcome to Sk8-Shop!"
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