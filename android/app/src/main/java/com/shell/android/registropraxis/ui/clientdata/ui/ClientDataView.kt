package com.shell.android.registropraxis.ui.clientdata.ui

import com.shell.android.registropraxis.db.models.ClientData

interface ClientDataView {

    fun showProgressbar()
    fun hideProgressbar()
    fun showMessage(message: String)

    fun loadData(clientData: ClientData)
    fun cleanData()
    fun saveData()

}
