package com.shell.android.registropraxis.ui.registerdetail

import com.raizlabs.android.dbflow.kotlinextensions.from
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.shell.android.registropraxis.db.models.Day
import com.shell.android.registropraxis.db.models.Day_Table
import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.ui.registerdetail.events.RegisterDetailEvent
import com.shell.android.shellcorebaselibrary.utils.getBeginDay
import com.shell.android.shellcorebaselibrary.utils.getBeginMonth
import java.util.*
import kotlin.collections.ArrayList

class RegisterDetailRepositoryImpl(

        val eventBus: EventBus

) : RegisterDetailRepository {

    override fun loadRegisterMonth() {
        val days = SQLite.select()
                .from(Day::class)
                .queryList()
        if (days.size > 0) {
            for (day in days) {
                if (!day.day.getBeginMonth().equals(Date().getBeginMonth())) {
                    days.remove(day)
                }
            }
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

    private fun post(eventType: Int = 0, message: String = "", days: List<Day>? = null) {
        val event = RegisterDetailEvent()
        event.apply {
            this.eventType = eventType
            this.message = message
            this.days = days
        }
        eventBus.post(event)
    }
}