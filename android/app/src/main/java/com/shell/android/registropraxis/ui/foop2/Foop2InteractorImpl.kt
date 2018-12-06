package com.shell.android.registropraxis.ui.foop2

import com.shell.android.registropraxis.db.models.Foop2Data
import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.ui.foop2.events.Foop2DataEvent

class Foop2InteractorImpl (

        val eventBus: EventBus,
        val repository: Foop2Repository

) : Foop2Interactor {

    override fun loadSavedFoop2Data() {
        repository.loadFoop2Data()
    }

    override fun saveFoop2Data(foop2Data: Foop2Data) {
        if (foop2Data.application.isEmpty()
            || foop2Data.description.isEmpty()
            || foop2Data.descriptionLarge.isEmpty()
            || foop2Data.justification.isEmpty()) {
            post(Foop2DataEvent.SAVE_ERROR, "Se requiere que la Aplicación, la Descripción, la Descripción Larga y la Justificación tengan valores válidos")
        } else {
            repository.saveFoop2Data(foop2Data)
        }
    }

    override fun cleanFoop2Data(foop2Data: Foop2Data) {
        repository.cleanFoop2Data(foop2Data)
    }

    override fun generateFoop2Pdf() {
        repository.generateFoop2Pdf()
    }

    private fun post(eventType: Int, message: String) {
        val event = Foop2DataEvent()
        event.eventType = eventType
        event.message = message
        eventBus.post(event)
    }
}