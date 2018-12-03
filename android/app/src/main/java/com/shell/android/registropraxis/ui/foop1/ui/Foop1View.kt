package com.shell.android.registropraxis.ui.foop1.ui

import com.shell.android.registropraxis.db.models.Foop1Data

interface Foop1View {

    fun showProgreesbar()
    fun hideProgreesbar()
    fun showMessage(message: String)

    fun loadData(foop1Data: Foop1Data)
    fun cleanData()
    fun saveData()
    fun generatePdf()

    fun downloadPdf(url: String)
}
