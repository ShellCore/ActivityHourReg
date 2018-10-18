package com.shell.android.registropraxis.ui.home.events

import com.shell.android.registropraxis.db.models.Day

class HomeEvent(
        var eventType: Int = 0,
        var message: String = "",
        var day : Day? = null
) {

    companion object {
        const val SAVE_SUCCESS = 1
        const val SAVE_ERROR = 2
        const val LOAD_SUCCESS = 3
        const val LOAD_ERROR = 4
    }
}
