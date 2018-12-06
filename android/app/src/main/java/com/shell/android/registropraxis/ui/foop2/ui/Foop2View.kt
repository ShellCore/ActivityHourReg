package com.shell.android.registropraxis.ui.foop2.ui

import com.shell.android.registropraxis.db.models.Foop2Data

interface Foop2View {

    fun showProgreesbar()
    fun hideProgreesbar()
    fun showMessage(message: String)

    fun loadData(foop2Data: Foop2Data)
    fun cleanData()
    fun saveData()
    fun generatePdf()

    fun downloadPdf(url: String)
}