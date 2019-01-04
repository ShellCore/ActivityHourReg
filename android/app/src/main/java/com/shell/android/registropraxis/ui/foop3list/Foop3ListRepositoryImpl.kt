package com.shell.android.registropraxis.ui.foop3list

import com.raizlabs.android.dbflow.sql.language.Delete
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.shell.android.registropraxis.db.models.ClientData
import com.shell.android.registropraxis.db.models.Foop3Data
import com.shell.android.registropraxis.db.models.UserData
import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.rest.DriveService
import com.shell.android.registropraxis.rest.base.BaseCallback
import com.shell.android.registropraxis.rest.services.postfoop3.PostFoop3Request
import com.shell.android.registropraxis.rest.services.postfoop3.PostFoop3Response
import com.shell.android.registropraxis.ui.foop3list.events.Foop3ListEvent

class Foop3ListRepositoryImpl (

        val eventBus: EventBus,
        val service: DriveService

) : Foop3ListRepository {

    override fun loadRegisterFoop3Data() {
        var registers = SQLite.select()
                .from(Foop3Data::class.java)
                .queryList()

        if (registers.size == 0) {
            registers = ArrayList()
        }

        post(Foop3ListEvent.LOAD_SUCCESS, registers = registers)
    }

    override fun saveFoop3Data(foop3Data: Foop3Data) {
        if (foop3Data.save()) {
            post(Foop3ListEvent.SAVE_SUCCESS, "Los datos del archivo se almacenaron correctamente")
        } else {
            post(Foop3ListEvent.SAVE_ERROR, "Hubo un error al guardar los datos del archivo")
        }
    }

    override fun deleteFoop3Data(foop3Data: Foop3Data) {
        if (foop3Data.delete()) {
            post(Foop3ListEvent.DELETE_SUCCESS, "Los datos del archivo han sido borrados")
        } else {
            post(Foop3ListEvent.DELETE_ERROR, "Los datos del archivo no se pudieron borrar")
        }
    }

    override fun generateFoop3Data() {
        val user = SQLite.select()
                .from(UserData::class.java)
                .querySingle()
        val client = SQLite.select()
                .from(ClientData::class.java)
                .querySingle()
        val foop3 = SQLite.select()
                .from(Foop3Data::class.java)
                .queryList()

        if (user == null
                || client == null
                || foop3.size == 0) {
            post(Foop3ListEvent.POST_FOOP3_ERROR, "Required data not found")
        } else {
            val request = PostFoop3Request(user, client, foop3)
            service.postFoop3(request)
                    .enqueue(object : BaseCallback<PostFoop3Response>() {
                        override fun onSuccess(response: PostFoop3Response) {
                            post(Foop3ListEvent.POST_FOOP3_SUCCESS, response.message)
                        }

                        override fun onError(errorMessage: String) {
                            post(Foop3ListEvent.POST_FOOP3_ERROR, errorMessage)
                        }
                    })
        }
    }

    override fun cleanRegisters() {
        Delete.table(Foop3Data::class.java)
        post(Foop3ListEvent.CLEAN_SUCCESS)
    }

    private fun post(eventType: Int, message: String = "", registers : List<Foop3Data>? = null) {
        val event = Foop3ListEvent()
        event.apply {
            this.eventType = eventType
            this.message = message
            this.registers = registers
        }

        eventBus.post(event)
    }
}