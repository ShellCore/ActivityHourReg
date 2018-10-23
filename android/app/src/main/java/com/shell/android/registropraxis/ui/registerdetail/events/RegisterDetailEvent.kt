package com.shell.android.registropraxis.ui.registerdetail.events

import com.shell.android.registropraxis.db.models.Day

class RegisterDetailEvent(

        var eventType: Int = 0,
        var message: String = "",
        var days: List<Day>? = null
) {

    companion object {
        const val LOAD_SUCCESS = 1
        const val LOAD_ERROR = 2
        const val SAVE_SUCCESS = 3
        const val SAVE_ERROR = 4
    }
}
