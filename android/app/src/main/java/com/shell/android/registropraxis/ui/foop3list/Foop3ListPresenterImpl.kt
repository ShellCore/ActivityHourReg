package com.shell.android.registropraxis.ui.foop3list

import com.shell.android.registropraxis.db.models.Foop3Data
import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.ui.foop3list.events.Foop3ListEvent
import com.shell.android.registropraxis.ui.foop3list.ui.Foop3ListView
import org.greenrobot.eventbus.Subscribe

class Foop3ListPresenterImpl (

        val eventBus: EventBus,
        var view: Foop3ListView?,
        val interactor: Foop3ListInteractor

) : Foop3ListPresenter {

    override fun onCreate() {
        eventBus.register(this)
    }

    override fun onDestroy() {
        eventBus.unregister(this)
        view = null
    }

    override fun loadRegisterFoop3Data() {
        view?.apply {
            showProgressbar()
            interactor.loadRegisterFoop3Data()
        }
    }

    override fun saveFoop3Data(foop3Data: Foop3Data) {
        view?.apply {
            showProgressbar()
            interactor.saveFoop3Data(foop3Data)
        }
    }

    override fun deleteFoop3Data(foop3Data: Foop3Data) {
        view?.apply {
            showProgressbar()
            interactor.deleteFoop3Data(foop3Data)
        }
    }

    override fun generateFoop3Pdf() {
        view?.apply {
            showProgressbar()
            interactor.generateFoop3Pdf()
        }
    }

    override fun cleanRegisters() {
        view?.apply {
            showProgressbar()
            interactor.cleanRegisters()
        }
    }

    @Subscribe
    override fun onEventMainThread(event: Foop3ListEvent) {
        view?.apply {
            hideProgressbar()
            when (event.eventType) {
                Foop3ListEvent.LOAD_SUCCESS -> {
                    updateRegisterList(event.registers!!)
                }

                Foop3ListEvent.SAVE_SUCCESS,
                Foop3ListEvent.DELETE_SUCCESS,
                Foop3ListEvent.CLEAN_SUCCESS -> {
                    loadRegisterFoop3Data()
                }

                Foop3ListEvent.POST_FOOP3_SUCCESS-> {
                    downloadPdf(event.message)
                }

                Foop3ListEvent.LOAD_ERROR,
                Foop3ListEvent.SAVE_ERROR,
                Foop3ListEvent.DELETE_ERROR,
                Foop3ListEvent.POST_FOOP3_ERROR -> {
                    showMessage(event.message)
                }
            }
        }
    }
}