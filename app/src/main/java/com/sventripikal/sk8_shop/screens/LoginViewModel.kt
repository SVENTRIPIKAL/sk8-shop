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

    // user login email
    private val _userEmail = MutableLiveData<String>()
    val userEmail: LiveData<String>
        get() = _userEmail

    // user login password
    private val _userPassword = MutableLiveData<String>()
    val userPassword: LiveData<String>
        get() = _userPassword

    // assign userEmail value - lambda
    val updateUserEmail: (String) -> Unit = {
        _userEmail.value = it
    }

    // assign userPassword value - lambda
    val updateUserPassword: (String) -> Unit = {
        _userPassword.value = it
    }

    // check if email & password not null or blank
    fun editFieldsComplete(): Boolean {
        return userEmail.value.isNullOrBlank() != TRUE &&
                userPassword.value.isNullOrBlank() != TRUE
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