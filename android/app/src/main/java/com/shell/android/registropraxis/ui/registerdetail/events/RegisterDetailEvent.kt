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
    }
}
