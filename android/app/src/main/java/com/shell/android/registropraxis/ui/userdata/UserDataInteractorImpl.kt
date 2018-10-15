package com.shell.android.registropraxis.ui.userdata

import com.shell.android.registropraxis.db.models.UserData
import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.ui.userdata.events.UserDataEvent

class UserDataInteractorImpl(val eventBus: EventBus, val repository: UserDataRepository) : UserDataInteractor {

    override fun loadSavedUserData() {
        repository.loadUserData()
    }

    override fun saveUserData(userData: UserData) {
        if (userData.name.isEmpty() || userData.userId.isEmpty()) {
            post("Se requiere que el nombre y el id tengan valores válidos")
        } else {
            repository.saveUserData(userData)
        }
    }

    override fun cleanUserData(userData: UserData) {
        repository.cleanUserData(userData)
    }

    private fun post(message: String) {
        val event = UserDataEvent()
        event.eventType = UserDataEvent.SAVE_ERROR
        event.message = message
        eventBus.post(event)
    }
}