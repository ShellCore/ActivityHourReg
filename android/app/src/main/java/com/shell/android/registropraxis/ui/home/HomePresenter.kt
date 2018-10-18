package com.shell.android.registropraxis.ui.home

import com.shell.android.registropraxis.db.models.Day
import com.shell.android.registropraxis.ui.home.events.HomeEvent

interface HomePresenter {

    fun onCreate()
    fun onDestroy()

    fun loadActualDay()
    fun saveActualDay(day: Day)

    fun onEventMainThread(event: HomeEvent)

}
