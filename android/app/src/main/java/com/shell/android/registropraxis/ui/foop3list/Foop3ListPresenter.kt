package com.shell.android.registropraxis.ui.foop3list

import com.shell.android.registropraxis.db.models.Foop3Data
import com.shell.android.registropraxis.ui.foop3list.events.Foop3ListEvent

interface Foop3ListPresenter {

    fun onCreate()
    fun onDestroy()

    fun loadRegisterFoop3Data()
    fun saveFoop3Data(foop3Data: Foop3Data)
    fun deleteFoop3Data(foop3Data: Foop3Data)

    fun generateFoop3Pdf()
    fun cleanRegisters()

    fun onEventMainThread(event: Foop3ListEvent)
}
