package com.shell.android.registropraxis.ui.home

import com.shell.android.registropraxis.db.models.Day

interface HomeInteractor {

    fun loadActualDay()
    fun saveActualDay(day: Day)

}
