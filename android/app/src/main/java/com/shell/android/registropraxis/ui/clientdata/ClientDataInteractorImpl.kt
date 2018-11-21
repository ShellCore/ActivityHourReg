package com.shell.android.registropraxis.ui.clientdata

import com.shell.android.registropraxis.db.models.ClientData
import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.ui.clientdata.events.ClientDataEvent
import com.shell.android.registropraxis.ui.userdata.events.UserDataEvent

class ClientDataInteractorImpl(

        val eventBus: EventBus,
        val repository: ClientDataRepository

) : ClientDataInteractor {

    override fun loadSavedClientData() {
        repository.loadClientData()
    }

    override fun saveClientData(clientData: ClientData) {
        if (clientData.contact.isEmpty()
                || clientData.client.isEmpty()
                || clientData.company.isEmpty()) {
            post(UserDataEvent.SAVE_ERROR, "Se requiere que el Nombre de Contacto, Solicitante y Compañía tengan valores válidos")
        } else {
            repository.saveClientData(clientData)
        }
    }

    override fun cleanClientData(clientData: ClientData) {
        repository.cleanClientData(clientData)
    }

    private fun post(eventType: Int, message: String) {
        val event = ClientDataEvent()
        event.eventType = eventType
        event.message = message
        eventBus.post(event)
    }
}