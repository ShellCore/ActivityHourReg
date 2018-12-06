package com.shell.android.registropraxis.ui.foop2

import com.raizlabs.android.dbflow.sql.language.SQLite
import com.shell.android.registropraxis.db.models.ClientData
import com.shell.android.registropraxis.db.models.Foop2Data
import com.shell.android.registropraxis.db.models.UserData
import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.rest.DriveService
import com.shell.android.registropraxis.rest.base.BaseCallback
import com.shell.android.registropraxis.rest.services.postfoop2.PostFoop2Request
import com.shell.android.registropraxis.rest.services.postfoop2.PostFoop2Response
import com.shell.android.registropraxis.ui.foop2.events.Foop2DataEvent

class Foop2RepositoryImpl (

        val eventBus: EventBus,
        val service: DriveService

) : Foop2Repository {

    override fun loadFoop2Data() {
        var foop2Data = SQLite.select()
                .from(Foop2Data::class.java)
                .querySingle()

        if (foop2Data == null) {
            foop2Data = Foop2Data()
        }

        post(Foop2DataEvent.LOAD_SUCCESS, foop2Data = foop2Data)
    }

    override fun saveFoop2Data(foop2Data: Foop2Data) {
        if (foop2Data.save()) {
            post(Foop2DataEvent.SAVE_SUCCESS, "Los datos del archivo se almacenaron correctamente", foop2Data)
        } else {
            post(Foop2DataEvent.SAVE_ERROR, "Hubo un error al guardar los datos del archivo")
        }
    }

    override fun cleanFoop2Data(foop2Data: Foop2Data) {
        if (foop2Data.delete()) {
            post(Foop2DataEvent.CLEAN_SUCCESS, "Los datos del archivo han sido borrados", foop2Data)
        } else {
            post(Foop2DataEvent.SAVE_ERROR, "Los datos del archivo no se pudieron borrar")
        }
    }

    override fun generateFoop2Pdf() {
        val user = SQLite.select()
                .from(UserData::class.java)
                .querySingle()
        val client = SQLite.select()
                .from(ClientData::class.java)
                .querySingle()
        val foop2 = SQLite.select()
                .from(Foop2Data::class.java)
                .querySingle()

        if (user == null
            || client == null
            || foop2 == null) {
            post(Foop2DataEvent.POST_FOOP2_ERROR, "Required data not found")
        } else {
            val request = PostFoop2Request(user, client, foop2)
            service.postFoop2(request)
                    .enqueue(object : BaseCallback<PostFoop2Response>() {
                        override fun onSuccess(response: PostFoop2Response) {
                            post(Foop2DataEvent.POST_FOOP2_SUCCESS, response.message)
                        }

                        override fun onError(errorMessage: String) {
                            post(Foop2DataEvent.POST_FOOP2_ERROR, errorMessage)
                        }
                    })
        }
    }

    private fun post(eventType: Int, message: String = "", foop2Data: Foop2Data? = null) {
        val event = Foop2DataEvent()
        event.apply {
            this.eventType = eventType
            this.message = message
            this.foop2Data = foop2Data
        }

        eventBus.post(event)
    }
}