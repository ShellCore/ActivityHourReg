package com.shell.android.registropraxis.ui.registerdetail

import com.shell.android.registropraxis.ui.registerdetail.events.RegisterDetailEvent

interface RegisterDetailPresenter {

    fun onCreate()
    fun onDestroy()

    fun loadRegisterMonth()

    fun onEventMainThread(event: RegisterDetailEvent)
}
