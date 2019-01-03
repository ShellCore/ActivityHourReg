package com.shell.android.registropraxis.ui.foop3detail.ui

import com.shell.android.registropraxis.db.models.Foop3Data

interface OnFoop3ConditionClickListener {
    fun onClickBtnAccept(foop3Data: Foop3Data)
    fun onClickBtnDelete(foop3Data: Foop3Data)
}