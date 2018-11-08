package com.shell.android.registropraxis.ui.home.events

import com.shell.android.registropraxis.db.models.Day
import com.shell.android.shellcorebaselibrary.base.events.BaseEvent

class HomeEvent(
        var day : Day? = null
) : BaseEvent() {

    companion object {
        const val SAVE_SUCCESS = 1
        const val SAVE_ERROR = 2
        const val LOAD_SUCCESS = 3
        const val LOAD_ERROR = 4
    }
}
