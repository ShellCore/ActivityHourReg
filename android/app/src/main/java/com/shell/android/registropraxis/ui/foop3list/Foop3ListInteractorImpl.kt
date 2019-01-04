package com.shell.android.registropraxis.ui.foop3list

import com.shell.android.registropraxis.db.models.Foop3Data
import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.ui.foop3list.events.Foop3ListEvent

class Foop3ListInteractorImpl(

        private val eventBus: EventBus,
        private val repository: Foop3ListRepository

) : Foop3ListInteractor {

    override fun loadRegisterFoop3Data() {

        repository.loadRegisterFoop3Data()
    }

    override fun saveFoop3Data(foop3Data: Foop3Data) {
        repository.saveFoop3Data(foop3Data)
    }

    override fun deleteFoop3Data(foop3Data: Foop3Data) {
        repository.deleteFoop3Data(foop3Data)
    }

    override fun generateFoop3Pdf() {
        repository.generateFoop3Data()
    }

    override fun cleanRegisters() {
        repository.cleanRegisters()
    }

    private fun post(eventType: Int, message: String) {
        val event = Foop3ListEvent()
        event.eventType = eventType
        event.message = message
        eventBus.post(event)
    }
}