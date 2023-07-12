package com.sventripikal.sk8_shop

import com.sventripikal.sk8_shop.models.SkateBoardItem
import timber.log.Timber


const val TAG = "_SVENTRIPIKAL"
const val HELPER_TEXT = "*COMPLETE TEXT FIELD*"

const val DARKSTAR = "DarkStar"
const val ELEMENT = "Element"
const val TOY_MACHINE = "Toy Machine"

const val DECKS = "Decks"
const val WHEELS = "Wheels"
const val TRUCKS = "Trucks"

const val TRUE = true
const val FALSE = false
const val FIVE = "5"

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
        itemName = "Darkstar Skateboards Cameo Wilson New Abnormal Skateboard Deck",
        DARKSTAR,
        DECKS,
        quantity = FIVE
    ),
    SkateBoardItem(
        itemName = "Darkstar Skateboards Collapse Yellow Skateboard Deck Hybrid",
        DARKSTAR,
        DECKS,
        quantity = FIVE
    ),
    SkateBoardItem(
        itemName = "Darkstar Skateboards Greg Lutzka Anthology 2 Purple / Black Skateboard Deck",
        DARKSTAR,
        DECKS,
        quantity = FIVE
    ),
    /**
     * ELEMENT DECKS
     */
    SkateBoardItem(
        itemName = "Element Skateboards Nick Garcia Trip Out Skateboard Deck",
        ELEMENT,
        DECKS,
        quantity = FIVE
    ),
    SkateBoardItem(
        itemName = "Element Skateboards Sascha Daley Future Nature Skateboard Deck",
        ELEMENT,
        DECKS,
        quantity = FIVE
    ),
    SkateBoardItem(
        itemName = "Element Skateboards Landscape Australia Skateboard Deck",
        ELEMENT,
        DECKS,
        quantity = FIVE
    ),
    /**
     * TOYMACHINE DECKS
     */
    SkateBoardItem(
        itemName = "Toy Machine Skateboards Monster Assorted Stains Skateboard Deck",
        TOY_MACHINE,
        DECKS,
        quantity = FIVE
    ),
    SkateBoardItem(
        itemName = "Toy Machine Skateboards Pattern Logo Skateboard Deck",
        TOY_MACHINE,
        DECKS,
        quantity = FIVE
    ),
    SkateBoardItem(
        itemName = "Toy Machine Skateboards Bored Sect Skateboard Deck",
        TOY_MACHINE,
        DECKS,
        quantity = FIVE
    )
)