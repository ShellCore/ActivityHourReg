package com.shell.android.registropraxis.ui.userdata

import com.shell.android.registropraxis.db.models.UserData
import com.shell.android.registropraxis.ui.userdata.events.UserDataEvent


interface UserDataPresenter {

    fun onCreate()
    fun onDestroy()

    fun loadSavedUserData()
    fun saveUserData(userData: UserData)
    fun cleanUserData(userData: UserData)

    fun onEventMainThread(event: UserDataEvent)
}
