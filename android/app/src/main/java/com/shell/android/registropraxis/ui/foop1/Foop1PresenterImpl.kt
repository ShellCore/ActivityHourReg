package com.shell.android.registropraxis.ui.foop1

import com.shell.android.registropraxis.db.models.Foop1Data
import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.ui.foop1.events.Foop1DataEvent
import com.shell.android.registropraxis.ui.foop1.ui.Foop1View
import org.greenrobot.eventbus.Subscribe

class Foop1PresenterImpl(

        val eventBus: EventBus,
        var view: Foop1View?,
        val interactor: Foop1Interactor

) : Foop1Presenter {

    override fun onCreate() {
        eventBus.register(this)
    }

    override fun onDestroy() {
        eventBus.unregister(this)
        view = null
    }

    override fun loadSavedFoop1Data() {
        view?.apply {
            showProgreesbar()
            interactor.loadSavedFoop1Data()
        }
    }

    override fun saveFoop1Data(foop1Data: Foop1Data) {
        view?.apply {
            showProgreesbar()
            interactor.saveFoop1Data(foop1Data)
        }
    }

    override fun cleanFoop1Data(foop1Data: Foop1Data) {
        view?.apply {
            showProgreesbar()
            interactor.cleanFoop1Data(foop1Data)
        }
    }

    override fun generateFoop1Pdf() {
        view?.apply {
            showProgreesbar()
            interactor.generateFoop1Pdf()
        }
    }

    @Subscribe
    override fun onEventMainThread(event: Foop1DataEvent) {
        view?.apply {
            hideProgreesbar()
            when (event.eventType) {
                Foop1DataEvent.LOAD_SUCCESS -> {
                    loadData(event.foop1Data!!)
                }
                Foop1DataEvent.SAVE_SUCCESS -> {
                    loadData(event.foop1Data!!)
                    showMessage(event.message)
                }
                Foop1DataEvent.CLEAN_SUCCESS -> {
                    loadData(Foop1Data())
                }
                Foop1DataEvent.POST_FOOP1_SUCCESS -> {
                    downloadPdf(event.message)
                }
                Foop1DataEvent.LOAD_ERROR,
                Foop1DataEvent.SAVE_ERROR,
                Foop1DataEvent.CLEAN_ERROR,
                Foop1DataEvent.POST_FOOP1_ERROR -> {
                    showMessage(event.message)
                }
            }
        }
    }
}