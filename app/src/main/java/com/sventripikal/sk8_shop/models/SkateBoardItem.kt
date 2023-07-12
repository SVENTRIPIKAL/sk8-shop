package com.sventripikal.sk8_shop.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


enum class Category { DECKS }
enum class Brand { ELEMENT, TOYMACHINE, DARKSTAR }

@Parcelize
data class SkateBoardItem(
    var category: Category, // enum
    var brand: Brand,       // enum
    var photo: Int,         // image Resource id
    var itemName: String,   // string
    var quantity: Int,      // number
): Parcelable