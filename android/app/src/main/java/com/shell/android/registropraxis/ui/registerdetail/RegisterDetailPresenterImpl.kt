package com.shell.android.registropraxis.ui.registerdetail

import com.shell.android.registropraxis.db.models.Day
import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.ui.registerdetail.events.RegisterDetailEvent
import com.shell.android.registropraxis.ui.registerdetail.ui.RegisterDetailView
import org.greenrobot.eventbus.Subscribe

class RegisterDetailPresenterImpl(

        val eventBus: EventBus,
        var view: RegisterDetailView?,
        val interactor: RegisterDetailInteractor

) : RegisterDetailPresenter {

    override fun onCreate() {
        eventBus.register(this)
    }

    override fun onDestroy() {
        eventBus.unregister(this)
        view = null
    }

    override fun loadRegisterMonth() {
        view?.apply {
            showProgressBar()
            interactor.loadRegisterMonth()
        }
    }

    override fun saveRegister(day: Day) {
        view?.apply {
            showProgressBar()
            interactor.saveRegister(day)
        }
    }

    @Subscribe
    override fun onEventMainThread(event: RegisterDetailEvent) {
        view?.apply {
            hideProgressBar()
            when (event.eventType) {
                RegisterDetailEvent.LOAD_SUCCESS -> {
                    updateDayList(event.days!!)
                }
                RegisterDetailEvent.SAVE_SUCCESS -> {
                    loadRegisterMonth()
                }
                RegisterDetailEvent.LOAD_ERROR,
                RegisterDetailEvent.SAVE_ERROR -> showMessage(event.message)
            }
        }
    }
}