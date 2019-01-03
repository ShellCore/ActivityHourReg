package com.shell.android.registropraxis.ui.foop3list.events

import com.shell.android.registropraxis.db.models.Foop3Data
import com.shell.android.shellcorebaselibrary.base.events.BaseEvent

class Foop3ListEvent (

        var registers: List<Foop3Data>? = null

) : BaseEvent() {

    companion object {
        const val LOAD_SUCCESS = 1
        const val LOAD_ERROR = 2
        const val SAVE_SUCCESS = 3
        const val SAVE_ERROR = 4
        const val DELETE_SUCCESS = 5
        const val DELETE_ERROR = 6
        const val CLEAN_SUCCESS = 7
        const val POST_FOOP3_SUCCESS = 8
        const val POST_FOOP3_ERROR = 9
    }
}