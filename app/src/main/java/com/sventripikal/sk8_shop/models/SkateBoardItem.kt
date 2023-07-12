package com.sventripikal.sk8_shop.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class SkateBoardItem(
    var itemName: String,
    var brand: String,
    var category: String,
    var quantity: String,
): Parcelable