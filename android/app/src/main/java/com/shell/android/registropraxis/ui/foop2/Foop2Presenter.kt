package com.shell.android.registropraxis.ui.foop2

import com.shell.android.registropraxis.db.models.Foop2Data
import com.shell.android.registropraxis.ui.foop2.events.Foop2DataEvent

interface Foop2Presenter {

    fun onCreate()
    fun onDestroy()

    fun loadSavedFoop2Data()
    fun saveFoop2Data(foop2Data: Foop2Data)
    fun cleanFoop2Data(foop2Data: Foop2Data)

    fun generateFoop2Pdf()

    fun onEventMainThread(event: Foop2DataEvent)
}
