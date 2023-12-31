package com.sventripikal.sk8_shop.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sventripikal.sk8_shop.Priority
import com.sventripikal.sk8_shop.TAG
import com.sventripikal.sk8_shop.listOfBoards
import com.sventripikal.sk8_shop.models.SkateBoardItem
import com.sventripikal.sk8_shop.timber


private const val MESSAGE_INIT = "[ViewModel] INIT"
private const val MESSAGE_CLEARED = "[ViewModel] ON-CLEARED"

class ApplicationViewModel : ViewModel() {

    /**
     * LOGIN FRAGMENT
     */
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


    /**
     * GREETING FRAGMENT
     */
    // user Welcome greeting
    private val _welcomeGreeting = MutableLiveData<String>()
    val welcomeGreeting: LiveData<String>
        get() = _welcomeGreeting

    // assign greeting value - lambda
    val updateGreeting: (String) -> Unit = { user ->
        _welcomeGreeting.value = "Hello ${user.replaceFirstChar{it.uppercase()}}, Welcome to SK8-Shop!"
    }


    /**
     * LISTINGS FRAGMENT
     */
    // list of items
    private val _itemsList = MutableLiveData(listOfBoards)
    val itemsList: LiveData<MutableList<SkateBoardItem>>
        get() = _itemsList


    /**
     * DETAILS FRAGMENT
     */
    // empty mutable list to add updates to ItemList
    private val mutableList = mutableListOf<SkateBoardItem>()

    // add new item
    fun addItemToList(item: SkateBoardItem){

        // log
        timber(TAG, "$item", Priority.INFO)

        // mutable list block
        mutableList.apply {
            // clear mutable list
            clear()
            // add new item
            add(item)
            // add all from LiveData list
            addAll(_itemsList.value!!)
            //update live data list
            updateItemList()
            // log
            timber(TAG, "List Updated", Priority.DEBUG)
        }
    }

    // update livedata list with new mutable list
    private fun updateItemList() {
        _itemsList.value = mutableList.toMutableList()
    }



    init {  // log
        timber(TAG, MESSAGE_INIT, Priority.DEBUG)
    }



    // Lifecycle method
    override fun onCleared() {
        super.onCleared()
        timber(TAG, MESSAGE_CLEARED, Priority.ERROR)
    }
}