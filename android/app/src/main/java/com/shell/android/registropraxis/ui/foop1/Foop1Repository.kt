package com.shell.android.registropraxis.ui.foop1

import com.shell.android.registropraxis.db.models.Foop1Data

interface Foop1Repository {

    fun loadFoop1Data()
    fun saveFoop1Data(foop1Data: Foop1Data)
    fun cleanFoop1Data(foop1Data: Foop1Data)
    fun generateFoop1Pdf()

}
