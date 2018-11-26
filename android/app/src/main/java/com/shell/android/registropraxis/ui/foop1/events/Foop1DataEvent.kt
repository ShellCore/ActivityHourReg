package com.shell.android.registropraxis.ui.foop1.events

import com.shell.android.registropraxis.db.models.Foop1Data
import com.shell.android.shellcorebaselibrary.base.events.BaseEvent

class Foop1DataEvent (

        var foop1Data: Foop1Data? = null

) : BaseEvent() {

    companion object {

        const val LOAD_SUCCESS = 1
        const val LOAD_ERROR = 2
        const val SAVE_SUCCESS = 3
        const val SAVE_ERROR = 4
        const val CLEAN_SUCCESS = 5
        const val CLEAN_ERROR = 6
        const val POST_FOOP1_SUCCESS = 7
        const val POST_FOOP1_ERROR = 8
    }
}
