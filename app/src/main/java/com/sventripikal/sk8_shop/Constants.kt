package com.sventripikal.sk8_shop

import com.sventripikal.sk8_shop.models.Brand
import com.sventripikal.sk8_shop.models.Category
import com.sventripikal.sk8_shop.models.SkateBoardItem
import timber.log.Timber


const val TAG = "_SVENTRIPIKAL"
const val HELPER_TEXT = "*COMPLETE TEXT FIELD*"
const val TRUE = true
const val FALSE = false

enum class Priority { ERROR, VERBOSE, DEBUG, INFO }

fun timber(tag: String, message: String, priority: Priority) {
    when (priority) {
        Priority.ERROR -> Timber.tag(tag).e(message)
        Priority.VERBOSE -> Timber.tag(tag).v(message)
        Priority.DEBUG -> Timber.tag(tag).d(message)
        Priority.INFO -> Timber.tag(tag).i(message)
    }
}

val listOfBoards = mutableListOf(
    /**
     * DARKSTAR DECKS
     */
    SkateBoardItem(
        Category.DECKS,
        Brand.DARKSTAR,
        R.drawable.ds2,
        itemName = "Darkstar Skateboards Cameo Wilson New Abnormal Skateboard Deck",
        quantity = 5
    ),
    SkateBoardItem(
        Category.DECKS,
        Brand.DARKSTAR,
        R.drawable.ds3,
        itemName = "Darkstar Skateboards Collapse Yellow Skateboard Deck Hybrid",
        quantity = 5
    ),
    SkateBoardItem(
        Category.DECKS,
        Brand.DARKSTAR,
        R.drawable.ds1,
        itemName = "Darkstar Skateboards Greg Lutzka Anthology 2 Purple / Black Skateboard Deck",
        quantity = 5
    ),
    /**
     * ELEMENT DECKS
     */
    SkateBoardItem(
        Category.DECKS,
        Brand.ELEMENT,
        R.drawable.e2,
        itemName = "Element Skateboards Nick Garcia Trip Out Skateboard Deck",
        quantity = 5
    ),
    SkateBoardItem(
        Category.DECKS,
        Brand.ELEMENT,
        R.drawable.e3,
        itemName = "Element Skateboards Sascha Daley Future Nature Skateboard Deck",
        quantity = 5
    ),
    SkateBoardItem(
        Category.DECKS,
        Brand.ELEMENT,
        R.drawable.e1,
        itemName = "Element Skateboards Landscape Australia Skateboard Deck",
        quantity = 5
    ),
    /**
     * TOYMACHINE DECKS
     */
    SkateBoardItem(
        Category.DECKS,
        Brand.TOYMACHINE,
        R.drawable.tm1,
        itemName = "Toy Machine Skateboards Monster Assorted Stains Skateboard Deck",
        quantity = 5
    ),
    SkateBoardItem(
        Category.DECKS,
        Brand.TOYMACHINE,
        R.drawable.tm2,
        itemName = "Toy Machine Skateboards Pattern Logo Skateboard Deck",
        quantity = 5
    ),
    SkateBoardItem(
        Category.DECKS,
        Brand.TOYMACHINE,
        R.drawable.tm3,
        itemName = "Toy Machine Skateboards Bored Sect Skateboard Deck",
        quantity = 5
    )
)