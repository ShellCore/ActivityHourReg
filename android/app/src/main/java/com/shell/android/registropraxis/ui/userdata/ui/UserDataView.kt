package com.shell.android.registropraxis.ui.userdata.ui

import com.shell.android.registropraxis.db.models.UserData

interface UserDataView {

    fun showProgressbar()
    fun hideProgressbar()
    fun showMessage(message: String)

    fun loadData(userData: UserData)
    fun cleanData()
    fun saveData()
}