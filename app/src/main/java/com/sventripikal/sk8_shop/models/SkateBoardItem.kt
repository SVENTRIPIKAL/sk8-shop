package com.sventripikal.sk8_shop.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


enum class Brand { ELEMENT, TOYMACHINE, DARKSTAR }
enum class Category { DECKS, WHEELS, TRUCKS }

@Parcelize
data class SkateBoardItem(
    var itemName: String,   // string
    var brand: Brand,       // enum
    var category: Category, // enum
    var quantity: Int,      // number
): Parcelable