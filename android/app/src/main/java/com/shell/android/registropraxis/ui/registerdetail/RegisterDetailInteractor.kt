package com.shell.android.registropraxis.ui.registerdetail

import com.shell.android.registropraxis.db.models.Day

interface RegisterDetailInteractor {

    fun loadRegisterMonth()
    fun saveRegister(day: Day)
    fun deleteRegister(day: Day)
}
