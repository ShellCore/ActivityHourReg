package com.shell.android.registropraxis.ui.foop1

import com.shell.android.registropraxis.db.models.Foop1Data
import com.shell.android.registropraxis.ui.foop1.events.Foop1DataEvent

interface Foop1Presenter {

    fun onCreate()
    fun onDestroy()

    fun loadSavedFoop1Data()
    fun saveFoop1Data(foop1Data: Foop1Data)
    fun cleanFoop1Data(foop1Data: Foop1Data)

    fun generateFoop1Pdf()

    fun onEventMainThread(event: Foop1DataEvent)
}
