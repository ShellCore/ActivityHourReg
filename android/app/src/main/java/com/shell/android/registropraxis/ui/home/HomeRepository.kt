package com.shell.android.registropraxis.ui.home

import com.shell.android.registropraxis.db.models.Day

interface HomeRepository {

    fun loadActualDay()
    fun saveDay(day: Day)

}
