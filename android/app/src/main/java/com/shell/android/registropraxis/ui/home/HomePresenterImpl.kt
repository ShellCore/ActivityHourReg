package com.shell.android.registropraxis.ui.home

import com.shell.android.registropraxis.db.models.Day
import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.ui.home.events.HomeEvent
import com.shell.android.registropraxis.ui.home.ui.HomeView
import org.greenrobot.eventbus.Subscribe

class HomePresenterImpl(

        val eventBus: EventBus,
        var view: HomeView?,
        val interactor: HomeInteractor

) : HomePresenter {

    override fun onCreate() {
        eventBus.register(this)
    }

    override fun onDestroy() {
        eventBus.unregister(this)
        view = null
    }

    override fun loadActualDay() {
        view?.apply {
            showProgressbar()
            interactor.loadActualDay()
        }
    }

    override fun saveActualDay(day: Day) {
        view?.apply {
            showProgressbar()
            interactor.saveActualDay(day)
        }
    }

    @Subscribe
    override fun onEventMainThread(event: HomeEvent) {
        view?.apply {
            hideProgressbar()

            when (event.eventType) {
                HomeEvent.SAVE_SUCCESS -> {
                    setLoadedDay(event.day!!)
                    showMessage(event.message)
                }
                HomeEvent.LOAD_SUCCESS -> {
                    setLoadedDay(event.day!!)
                }
                HomeEvent.SAVE_ERROR,
                HomeEvent.LOAD_ERROR -> showMessage(event.message)
            }
        }
    }
}