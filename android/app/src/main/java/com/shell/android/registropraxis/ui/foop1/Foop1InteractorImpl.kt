package com.shell.android.registropraxis.ui.foop1

import com.shell.android.registropraxis.db.models.Foop1Data
import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.ui.foop1.events.Foop1DataEvent

class Foop1InteractorImpl(

        val eventBus: EventBus,
        val repository: Foop1Repository

) : Foop1Interactor {

    override fun loadSavedFoop1Data() {
        repository.loadFoop1Data()
    }

    override fun saveFoop1Data(foop1Data: Foop1Data) {
        if (foop1Data.job.isEmpty()
                || foop1Data.department.isEmpty()) {
            post(Foop1DataEvent.SAVE_ERROR, "Se requiere que el Puesto y la Clave de Asignación tengan valores válidos")
        } else {
            repository.saveFoop1Data(foop1Data)
        }
    }

    override fun cleanFoop1Data(foop1Data: Foop1Data) {
        repository.cleanFoop1Data(foop1Data)
    }

    override fun generateFoop1Pdf() {
        repository.generateFoop1Pdf()
    }

    private fun post(eventType: Int, message: String) {
        val event = Foop1DataEvent()
        event.eventType = eventType
        event.message = message
        eventBus.post(event)
    }
}