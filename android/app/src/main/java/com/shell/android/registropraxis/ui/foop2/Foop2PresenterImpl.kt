package com.shell.android.registropraxis.ui.foop2

import com.shell.android.registropraxis.db.models.Foop2Data
import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.ui.foop2.events.Foop2DataEvent
import com.shell.android.registropraxis.ui.foop2.ui.Foop2View
import org.greenrobot.eventbus.Subscribe

class Foop2PresenterImpl (

        val eventBus: EventBus,
        var view: Foop2View?,
        val interactor: Foop2Interactor

) : Foop2Presenter {

    override fun onCreate() {
        eventBus.register(this)
    }

    override fun onDestroy() {
        eventBus.unregister(this)
        view = null
    }

    override fun loadSavedFoop2Data() {
        view?.apply {
            showProgressBar()
            interactor.loadSavedFoop2Data()
        }
    }

    override fun saveFoop2Data(foop2Data: Foop2Data) {
        view?.apply {
            showProgressBar()
            interactor.saveFoop2Data(foop2Data)
        }
    }

    override fun cleanFoop2Data(foop2Data: Foop2Data) {
        view?.apply {
            showProgressBar()
            interactor.cleanFoop2Data(foop2Data)
        }
    }

    override fun generateFoop2Pdf() {
        view?.apply {
            showProgressBar()
            interactor.generateFoop2Pdf()
        }
    }

    @Subscribe
    override fun onEventMainThread(event: Foop2DataEvent) {
        view?.apply {
            hideProgreesbar()
            when (event.eventType) {
                Foop2DataEvent.LOAD_SUCCESS -> {
                    loadData(event.foop2Data!!)
                }
                Foop2DataEvent.SAVE_SUCCESS -> {
                    loadData(event.foop2Data!!)
                    showMessage(event.message)
                }
                Foop2DataEvent.CLEAN_SUCCESS -> {
                    loadData(Foop2Data())
                }
                Foop2DataEvent.POST_FOOP2_SUCCESS -> {
                    downloadPdf(event.message)
                }
                Foop2DataEvent.LOAD_ERROR,
                Foop2DataEvent.SAVE_ERROR,
                Foop2DataEvent.CLEAN_ERROR,
                Foop2DataEvent.POST_FOOP2_ERROR -> {
                    showMessage(event.message)
                }
            }
        }
    }
}