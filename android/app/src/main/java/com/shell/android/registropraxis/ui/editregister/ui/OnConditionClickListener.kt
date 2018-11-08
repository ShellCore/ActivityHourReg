package com.shell.android.registropraxis.ui.editregister.ui

import com.shell.android.registropraxis.db.models.Day

interface OnConditionClickListener {
    fun onClickBtnAccept(day: Day)
    fun onClickBtnDelete(day: Day)

}
