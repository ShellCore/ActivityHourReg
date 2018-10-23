package com.shell.android.registropraxis.ui.registerdetail

import com.shell.android.registropraxis.db.models.Day

class RegisterDetailInteractorImpl(

        private val repository: RegisterDetailRepository

) : RegisterDetailInteractor {

    override fun loadRegisterMonth() {
        repository.loadRegisterMonth()
    }

    override fun saveRegister(day: Day) {
        repository.saveRegister(day)
    }
}