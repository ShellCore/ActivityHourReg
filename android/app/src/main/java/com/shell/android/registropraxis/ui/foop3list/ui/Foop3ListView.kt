package com.shell.android.registropraxis.ui.foop3list.ui

import com.shell.android.registropraxis.db.models.Foop3Data

interface Foop3ListView {

    fun showProgressbar()
    fun hideProgressbar()
    fun showMessage(message: String)

    fun updateRegisterList(registers: List<Foop3Data>)
    fun downloadPdf(url: String)
}
