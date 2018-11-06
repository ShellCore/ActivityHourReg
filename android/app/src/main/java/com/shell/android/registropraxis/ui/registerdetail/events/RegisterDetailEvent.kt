package com.shell.android.registropraxis.ui.registerdetail.events

import com.shell.android.registropraxis.db.models.Day
import com.shell.android.shellcorebaselibrary.base.events.BaseEvent

class RegisterDetailEvent(

        var days: List<Day>? = null
) : BaseEvent() {

    companion object {
        const val LOAD_SUCCESS = 1
        const val LOAD_ERROR = 2
        const val SAVE_SUCCESS = 3
        const val SAVE_ERROR = 4
        const val DELETE_SUCCESS = 5
        const val DELETE_ERROR = 6
    }
}
