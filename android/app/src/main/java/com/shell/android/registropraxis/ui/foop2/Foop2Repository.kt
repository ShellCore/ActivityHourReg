package com.shell.android.registropraxis.ui.foop2

import com.shell.android.registropraxis.db.models.Foop2Data

interface Foop2Repository {

    fun loadFoop2Data()
    fun saveFoop2Data(foop2Data: Foop2Data)
    fun cleanFoop2Data(foop2Data: Foop2Data)
    fun generateFoop2Pdf()

}
