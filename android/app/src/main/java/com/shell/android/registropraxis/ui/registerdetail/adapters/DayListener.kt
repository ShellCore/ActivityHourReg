package com.shell.android.registropraxis.ui.registerdetail.adapters

import com.shell.android.registropraxis.db.models.Day

interface DayListener {

    fun onDaySelected(day: Day)
}
