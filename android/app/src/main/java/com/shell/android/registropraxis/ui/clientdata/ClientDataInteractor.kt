package com.shell.android.registropraxis.ui.clientdata

import com.shell.android.registropraxis.db.models.ClientData

interface ClientDataInteractor {

    fun loadSavedClientData()
    fun saveClientData(clientData: ClientData)
    fun cleanClientData(clientData: ClientData)

}
