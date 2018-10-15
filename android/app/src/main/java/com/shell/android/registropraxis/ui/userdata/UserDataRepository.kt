package com.shell.android.registropraxis.ui.userdata

import com.shell.android.registropraxis.db.models.UserData

interface UserDataRepository {

    fun loadUserData()
    fun saveUserData(userData: UserData)
    fun cleanUserData(userData: UserData)

}
