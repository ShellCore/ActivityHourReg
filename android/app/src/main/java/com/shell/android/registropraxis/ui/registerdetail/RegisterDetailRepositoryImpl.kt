package com.shell.android.registropraxis.ui.registerdetail

import com.raizlabs.android.dbflow.kotlinextensions.from
import com.raizlabs.android.dbflow.sql.language.Delete
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.shell.android.registropraxis.db.models.ClientData
import com.shell.android.registropraxis.db.models.Day
import com.shell.android.registropraxis.db.models.UserData
import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.rest.DriveService
import com.shell.android.registropraxis.rest.base.BaseCallback
import com.shell.android.registropraxis.rest.services.postassistance.PostAssistanceRequest
import com.shell.android.registropraxis.rest.services.postassistance.PostAssistanceResponse
import com.shell.android.registropraxis.ui.registerdetail.events.RegisterDetailEvent
import com.shell.android.shellcorebaselibrary.utils.getBeginMonth
import java.util.*
import kotlin.collections.ArrayList

class RegisterDetailRepositoryImpl(

        val eventBus: EventBus,
        val service: DriveService

) : RegisterDetailRepository {

    override fun loadRegisterMonth() {
        val days = SQLite.select()
                .from(Day::class)
                .queryList()
        if (days.size > 0) {
            days.keepActualMonthRegisters()
            post(RegisterDetailEvent.LOAD_SUCCESS, days = days)
        } else {
            post(RegisterDetailEvent.LOAD_SUCCESS, days = ArrayList())
        }
    }

    override fun saveRegister(day: Day) {
        if (day.save()) {
            post(RegisterDetailEvent.SAVE_SUCCESS)
        } else {
            post(RegisterDetailEvent.SAVE_ERROR, "No se pudo actualizar el registro correctamente")
        }
    }

    override fun deleteRegister(day: Day) {
        if (day.delete()) {
            post(RegisterDetailEvent.DELETE_SUCCESS)
        } else {
            post(RegisterDetailEvent.DELETE_ERROR, "No se pudo eliminar el registro correctamente")
        }
    }

    override fun cleanRegisters() {
        Delete.table(Day::class.java)
        post(RegisterDetailEvent.CLEAN_SUCCESS)
    }

    override fun generateRegisterPdf() {
        val client = SQLite.select()
                .from(ClientData::class.java)
                .querySingle()
        val user = SQLite.select()
                .from(UserData::class.java)
                .querySingle()
        val month = SQLite.select()
                .from(Day::class.java)
                .queryList()


        if (month.isNotEmpty()) {
            month.keepActualMonthRegisters()
        }

        if (client == null
                || user == null
                || month.isEmpty()) {
            post(RegisterDetailEvent.POST_ASSISTANCE_ERROR, "Required data not found")
        } else {
            val request = PostAssistanceRequest(user, client, month)
            service.postAssistance(request)
                    .enqueue(object : BaseCallback<PostAssistanceResponse>() {

                        override fun onSuccess(response: PostAssistanceResponse) {
                            post(RegisterDetailEvent.POST_ASSISTANCE_SUCCESS, response.message)
                        }

                        override fun onError(errorMessage: String) {
                            post(RegisterDetailEvent.POST_ASSISTANCE_ERROR, errorMessage)
                        }
                    })
        }
    }

    private fun post(eventType: Int = 0, message: String = "", days: List<Day>? = null) {
        val event = RegisterDetailEvent()
        event.apply {
            this.eventType = eventType
            this.message = message
            this.days = days
        }
        eventBus.post(event)
    }

    private fun MutableList<Day>.keepActualMonthRegisters() {
        val iterator = this.iterator()
        while (iterator.hasNext()) {
            val day = iterator.next()
            if (!day.day.getBeginMonth().equals(Date().getBeginMonth())) {
                iterator.remove()
            }
        }
    }
}