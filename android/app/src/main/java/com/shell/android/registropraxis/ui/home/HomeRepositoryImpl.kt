package com.shell.android.registropraxis.ui.home

import android.util.Log
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.shell.android.registropraxis.db.models.Day
import com.shell.android.registropraxis.db.models.Day_Table
import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.ui.home.events.HomeEvent
import java.util.*

class HomeRepositoryImpl(val eventBus: EventBus) : HomeRepository {

    companion object {
        const val TAG = "HomeRepositoryImpl"
    }

    override fun loadActualDay() {
        var actualDay = SQLite.select()
                .from(Day::class.java)
                .where(Day_Table.day.eq(Date()))
                .querySingle()

        if (actualDay != null) {
            post(HomeEvent.LOAD_SUCCESS, day = actualDay)
        } else {
            post(HomeEvent.LOAD_SUCCESS, day = Day())
        }
    }

    override fun saveDay(day: Day) {
        if (day.save()) {
            post(HomeEvent.SAVE_SUCCESS, day = day, message = "La hora quedó registrada correctamente")
            Log.i(TAG, "$day")
        } else {
            post(HomeEvent.SAVE_ERROR, "No se almacenó correctamente el registro de horas")
        }
    }

    private fun post(eventType: Int = 0, message: String = "", day: Day? = null) {
        val event = HomeEvent()
        event.apply {
            this.eventType = eventType
            this.message = message
            this.day = day
        }
        eventBus.post(event)
    }
}