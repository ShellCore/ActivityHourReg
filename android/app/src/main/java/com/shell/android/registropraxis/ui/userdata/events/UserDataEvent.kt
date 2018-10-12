package com.shell.android.registropraxis.ui.userdata.events

import com.shell.android.registropraxis.db.models.UserData

class UserDataEvent (
        var eventType: Int,
        var message: String,
        var userData: UserData) {

    companion object {
        const val LOAD_SUCCESS = 1
        const val LOAD_ERROR = 2
        const val SAVE_SUCCESS = 3
        const val SAVE_ERROR = 4
        const val CLEAN_SUCCESS = 5
        const val CLEAN_ERROR = 6
    }
}
