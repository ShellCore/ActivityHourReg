package com.shell.android.registropraxis.ui.foop1

import com.raizlabs.android.dbflow.sql.language.SQLite
import com.shell.android.registropraxis.db.models.Foop1Data
import com.shell.android.registropraxis.rest.DriveService
import com.shell.android.registropraxis.ui.foop1.events.Foop1DataEvent
import org.greenrobot.eventbus.EventBus

class Foop1RepositoryImpl (

        val eventBus: EventBus,
        val service: DriveService

) : Foop1Repository {

    override fun loadFoop1Data() {
        var foop1Data = SQLite.select()
                .from(Foop1Data::class.java)
                .querySingle()

        if (foop1Data == null) {
            foop1Data = Foop1Data()
        }

        post(Foop1DataEvent.LOAD_SUCCESS, foop1Data = foop1Data)
    }

    override fun saveFoop1Data(foop1Data: Foop1Data) {
        if (foop1Data.save()) {
            post(Foop1DataEvent.SAVE_SUCCESS, "Los datos del archivo se almacenaron correctamente")
        } else {
            post(Foop1DataEvent.SAVE_ERROR, "Hubo un error al guardar los datos del archivo")
        }
    }

    override fun cleanFoop1Data(foop1Data: Foop1Data) {
        if (foop1Data.delete()) {
            post(Foop1DataEvent.CLEAN_SUCCESS, "Los datos del archivo han sido borrados", Foop1Data())
        } else {
            post(Foop1DataEvent.CLEAN_ERROR, "Los datos del archivo no se pudieron borrar")
        }
    }

    override fun generateFoop1Pdf() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun post(eventType: Int, message: String = "", foop1Data: Foop1Data? = null) {
        val event = Foop1DataEvent()

        event.apply {
            this.eventType = eventType
            this.message = message
            this.foop1Data = foop1Data
        }

        eventBus.post(event)
    }
}