package com.heyrex.mytourismapp.core.presentation

import java.io.Serializable

abstract class AppEvent(private val isSingleEvent: Boolean = true): Serializable {

    private var hasBeenHandled = false

    fun getContentIfNotHandled(): AppEvent? {
        if(!isSingleEvent) return this
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            this
        }
    }

    fun peekContent() = this
}