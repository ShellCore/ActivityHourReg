package com.shell.android.registropraxis.ui.clientdata

import com.shell.android.registropraxis.db.models.ClientData
import com.shell.android.registropraxis.ui.clientdata.events.ClientDataEvent

interface ClientDataPresenter {

    fun onCreate()
    fun onDestroy()
    fun loadSavedClientData()
    fun saveClientData(clientData: ClientData)
    fun cleanClientData(clientData: ClientData)

    fun onEventMainThread(event: ClientDataEvent)

}
