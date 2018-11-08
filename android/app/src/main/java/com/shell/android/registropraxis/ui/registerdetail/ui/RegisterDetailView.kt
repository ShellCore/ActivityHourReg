package com.shell.android.registropraxis.ui.registerdetail.ui

import com.shell.android.registropraxis.db.models.Day

interface RegisterDetailView {

    fun showProgressBar()
    fun hideProgressBar()
    fun showMessage(message: String)

    fun updateDayList(days: List<Day>)
}
