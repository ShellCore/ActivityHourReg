package com.shell.android.registropraxis.ui.registerdetail

import com.shell.android.registropraxis.db.models.Day

interface RegisterDetailRepository {

    fun loadRegisterMonth()
    fun saveRegister(day: Day)
    fun deleteRegister(day: Day)
}
