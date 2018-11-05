package com.shell.android.registropraxis.ui.clientdata

import com.shell.android.registropraxis.db.models.ClientData
import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.ui.clientdata.events.ClientDataEvent
import com.shell.android.registropraxis.ui.clientdata.ui.ClientDataView
import org.greenrobot.eventbus.Subscribe

class ClientDataPresenterImpl(

        val eventBus: EventBus,
        var view: ClientDataView?,
        val interactor: ClientDataInteractor

) : ClientDataPresenter {

    override fun onCreate() {
        eventBus.register(this)
    }

    override fun onDestroy() {
        eventBus.unregister(this)
        view = null
    }

    override fun loadSavedClientData() {
        view?.apply {
            showProgressbar()
            interactor.loadSavedClientData()
        }
    }

    override fun saveClientData(clientData: ClientData) {
        view?.apply {
            showProgressbar()
            interactor.saveClientData(clientData)
        }
    }

    override fun cleanClientData(clientData: ClientData) {
        view?.apply {
            showProgressbar()
            interactor.cleanClientData(clientData)
        }
    }

    @Subscribe
    override fun onEventMainThread(event: ClientDataEvent) {
        view?.hideProgressbar()

        when (event.eventType) {
            ClientDataEvent.LOAD_SUCCESS -> view?.loadData(event.clientData!!)
            ClientDataEvent.SAVE_SUCCESS -> {
                view?.loadData(event.clientData!!)
                view?.showMessage(event.message)
            }
            ClientDataEvent.CLEAN_SUCCESS -> view?.loadData(ClientData())
            ClientDataEvent.LOAD_ERROR,
            ClientDataEvent.SAVE_ERROR,
            ClientDataEvent.CLEAN_ERROR -> view?.showMessage(event.message)
        }
    }
}