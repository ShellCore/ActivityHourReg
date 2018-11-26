package com.shell.android.registropraxis.ui.foop1

import com.raizlabs.android.dbflow.sql.language.SQLite
import com.shell.android.registropraxis.db.models.ClientData
import com.shell.android.registropraxis.db.models.Foop1Data
import com.shell.android.registropraxis.db.models.UserData
import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.rest.DriveService
import com.shell.android.registropraxis.rest.base.BaseCallback
import com.shell.android.registropraxis.rest.services.postfoop1.PostFoop1Request
import com.shell.android.registropraxis.rest.services.postfoop1.PostFoop1Response
import com.shell.android.registropraxis.ui.foop1.events.Foop1DataEvent

class Foop1RepositoryImpl(

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
            post(Foop1DataEvent.SAVE_SUCCESS, "Los datos del archivo se almacenaron correctamente", foop1Data)
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
        val user = SQLite.select()
                .from(UserData::class.java)
                .querySingle()
        val client = SQLite.select()
                .from(ClientData::class.java)
                .querySingle()
        val foop1 = SQLite.select()
                .from(Foop1Data::class.java)
                .querySingle()

        if (client == null
                || user == null
                || foop1 == null) {
            post(Foop1DataEvent.POST_FOOP1_ERROR, "Required data not found")
        } else {
            val request = PostFoop1Request(user, client, foop1)
            service.postFoop1(request)
                    .enqueue(object: BaseCallback<PostFoop1Response>() {
                        override fun onSuccess(response: PostFoop1Response) {
                            post(Foop1DataEvent.POST_FOOP1_SUCCESS, response.message)
                        }

                        override fun onError(errorMessage: String) {
                            post(Foop1DataEvent.POST_FOOP1_ERROR, errorMessage)
                        }
                    })
        }
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