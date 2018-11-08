package com.shell.android.registropraxis.ui.clientdata

import com.raizlabs.android.dbflow.sql.language.SQLite
import com.shell.android.registropraxis.db.models.ClientData
import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.ui.clientdata.events.ClientDataEvent

class ClientDataRepositoryImpl(

        val eventBus: EventBus

) : ClientDataRepository {

    override fun loadClientData() {
        var clientData = SQLite.select()
                .from(ClientData::class.java)
                .querySingle()

        if (clientData == null) {
            clientData = ClientData()
        }
        post(ClientDataEvent.LOAD_SUCCESS, clientData = clientData)
    }

    override fun saveClientData(clientData: ClientData) {
        if (clientData.save()) {
            post(ClientDataEvent.SAVE_SUCCESS, "Los datos del cliente se almacenaron correctamente", clientData)
        } else {
            post(ClientDataEvent.SAVE_ERROR, "Hubo un error al guardar los datos del cliente")
        }
    }

    override fun cleanClientData(clientData: ClientData) {
        if (clientData.delete()) {
            post(ClientDataEvent.CLEAN_SUCCESS, "Los datos del cliente han sido borrados", ClientData())
        } else {
            post(ClientDataEvent.CLEAN_ERROR, "Los datos del usuario no se pudieron borrar")
        }
    }

    private fun post(eventType: Int, message: String = "", clientData: ClientData? = null) {
        val event = ClientDataEvent()
        event.apply {
            this.eventType = eventType
            this.message = message
            this.clientData = clientData
        }
        eventBus.post(event)
    }
}