package com.shell.android.registropraxis.ui.foop3list

import com.shell.android.registropraxis.db.models.Foop3Data

interface Foop3ListInteractor {

    fun loadRegisterFoop3Data()
    fun saveFoop3Data(foop3Data: Foop3Data)
    fun deleteFoop3Data(foop3Data: Foop3Data)
    fun generateFoop3Pdf()
    fun cleanRegisters()
}
