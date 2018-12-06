package com.shell.android.registropraxis.ui.foop2.events

import com.shell.android.registropraxis.db.models.Foop2Data
import com.shell.android.shellcorebaselibrary.base.events.BaseEvent

class Foop2DataEvent (

        var foop2Data: Foop2Data? = null

) : BaseEvent() {

    companion object {

        const val LOAD_SUCCESS = 1
        const val LOAD_ERROR = 2
        const val SAVE_SUCCESS = 3
        const val SAVE_ERROR = 4
        const val CLEAN_SUCCESS = 5
        const val CLEAN_ERROR = 6
        const val POST_FOOP2_SUCCESS = 7
        const val POST_FOOP2_ERROR = 8
    }
}
