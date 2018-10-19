package com.shell.android.registropraxis.ui.registerdetail

class RegisterDetailInteractorImpl(

        private val repository: RegisterDetailRepository

) : RegisterDetailInteractor {

    override fun loadRegisterMonth() {
        repository.loadRegisterMonth()
    }
}