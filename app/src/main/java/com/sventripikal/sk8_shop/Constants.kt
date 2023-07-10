package com.sventripikal.sk8_shop

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