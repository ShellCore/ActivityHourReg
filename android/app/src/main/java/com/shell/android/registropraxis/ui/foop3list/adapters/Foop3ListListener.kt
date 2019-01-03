package com.shell.android.registropraxis.ui.foop3list.adapters

import com.shell.android.registropraxis.db.models.Foop3Data

interface Foop3ListListener {

    fun onRegisterSelected(foop3Data: Foop3Data)
}
