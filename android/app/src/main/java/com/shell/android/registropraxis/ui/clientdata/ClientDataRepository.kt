package com.shell.android.registropraxis.ui.clientdata

import com.shell.android.registropraxis.db.models.ClientData

interface ClientDataRepository {

    fun loadClientData()
    fun saveClientData(clientData: ClientData)
    fun cleanClientData(clientData: ClientData)

}
