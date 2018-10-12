package com.shell.android.registropraxis.ui.userdata

import com.shell.android.registropraxis.db.models.UserData
import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.ui.userdata.events.UserDataEvent
import com.shell.android.registropraxis.ui.userdata.ui.UserDataView

class UserDataPresenterImpl (

        val eventBus: EventBus,
        var view: UserDataView?,
        val interactor: UserDataInteractor


) : UserDataPresenter {

    override fun onCreate() {
        eventBus.register(this)
    }

    override fun onDestroy() {
        eventBus.unregister(this)
        view = null
    }

    override fun loadSavedUserData() {
        view?.apply {
            showProgressbar()
            interactor.loadSavedUserData()
        }
    }

    override fun saveUserData(userData: UserData) {
        view?.apply {
            showProgressbar()
            interactor.saveUserData(userData)
        }
    }

    override fun cleanUserData(userData: UserData) {
        view?.apply {
            showProgressbar()
            interactor.cleanUserData(userData)
        }
    }

    override fun onEventMainThread(event: UserDataEvent) {
        view?.hideProgressbar()

        when(event.eventType) {
            UserDataEvent.LOAD_SUCCESS -> view?.loadData(event.userData)
            UserDataEvent.SAVE_SUCCESS -> {
                view?.loadData(event.userData)
                view?.showMessage(event.message)
            }
            UserDataEvent.CLEAN_SUCCESS -> view?.loadData(UserData())
            UserDataEvent.LOAD_ERROR,
            UserDataEvent.SAVE_ERROR,
            UserDataEvent.CLEAN_ERROR -> {
                view?.showMessage(event.message)
            }
        }
    }
}