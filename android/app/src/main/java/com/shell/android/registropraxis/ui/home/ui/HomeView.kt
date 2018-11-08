package com.shell.android.registropraxis.ui.home.ui

import com.shell.android.registropraxis.db.models.Day

interface HomeView {

    fun showProgressbar()
    fun hideProgressbar()
    fun showMessage(message: String)
    fun setLoadedDay(day: Day)
    fun navigateToEditDay()
}