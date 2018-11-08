package com.shell.android.registropraxis.ui.home

import com.shell.android.registropraxis.db.models.Day

class HomeInteractorImpl(val repository: HomeRepository) : HomeInteractor {

    override fun loadActualDay() {
        repository.loadActualDay()
    }

    override fun saveActualDay(day: Day) {
        repository.saveDay(day)
    }
}