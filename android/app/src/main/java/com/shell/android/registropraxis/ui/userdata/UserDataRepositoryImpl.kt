package com.shell.android.registropraxis.ui.userdata

import com.raizlabs.android.dbflow.sql.language.SQLite
import com.shell.android.registropraxis.db.models.UserData
import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.ui.userdata.events.UserDataEvent

class UserDataRepositoryImpl(val eventBus: EventBus) : UserDataRepository {

    override fun loadUserData() {
        var userData = SQLite.select()
                .from(UserData::class.java)
                .querySingle()

        if (userData == null) {
            userData = UserData()
        }
        post(UserDataEvent.LOAD_SUCCESS, userData = userData)
    }

    override fun saveUserData(userData: UserData) {
        if (userData.save()) {
            post(UserDataEvent.SAVE_SUCCESS, "Los datos del usuario se almacenaron correctamente", userData)
        } else {
            post(UserDataEvent.SAVE_ERROR, "Hubo un error al guardar los datos del usuario")
        }
    }

    override fun cleanUserData(userData: UserData) {
        if (userData.delete()) {
            post(UserDataEvent.CLEAN_SUCCESS, "Los datos del usuario han sido borrados", UserData())
        } else {
            post(UserDataEvent.CLEAN_ERROR, "Los datos del usuario no se pudieron borrar")
        }

    }

    private fun post(eventType: Int, message: String = "", userData: UserData? = null) {
        val event = UserDataEvent()
        event.apply {
            this.eventType = eventType
            this.message = message
            this.userData = userData
        }
        eventBus.post(event)
    }
}