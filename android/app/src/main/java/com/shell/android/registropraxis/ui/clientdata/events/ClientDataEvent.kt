package com.shell.android.registropraxis.ui.clientdata.events

import com.shell.android.registropraxis.db.models.ClientData
import com.shell.android.shellcorebaselibrary.base.events.BaseEvent

class ClientDataEvent(

        var clientData: ClientData? = null

) : BaseEvent() {

    companion object {

        const val LOAD_SUCCESS = 1
        const val LOAD_ERROR = 2
        const val SAVE_SUCCESS = 3
        const val SAVE_ERROR = 4
        const val CLEAN_SUCCESS = 5
        const val CLEAN_ERROR = 6
    }
}
