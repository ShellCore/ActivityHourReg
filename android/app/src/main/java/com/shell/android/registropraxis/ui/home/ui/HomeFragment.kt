package com.shell.android.registropraxis.ui.home.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shell.android.registropraxis.R
import com.shell.android.shellcorebaselibrary.utils.getFormattedDay
import com.shell.android.shellcorebaselibrary.utils.getFormattedMonthYear
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class HomeFragment : Fragment() {

    private var actualDate:Date = Calendar.getInstance().time

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.mainMenu_home)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtDay.text = actualDate.getFormattedDay()
        txtMonth.text = actualDate.getFormattedMonthYear()
    }

}
