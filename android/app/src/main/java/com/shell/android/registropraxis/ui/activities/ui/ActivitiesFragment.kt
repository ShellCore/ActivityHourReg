package com.shell.android.registropraxis.ui.activities.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.shell.android.registropraxis.R
import com.shell.android.registropraxis.ui.foop1.ui.Foop1Fragment
import com.shell.android.registropraxis.ui.foop2.ui.Foop2Fragment
import com.shell.android.registropraxis.ui.foop3.ui.Foop3Fragment
import com.shell.android.registropraxis.ui.main.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_activities.*

class ActivitiesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.mainMenu_activities)
        return inflater.inflate(R.layout.fragment_activities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnFoop1.setOnClickListener { (activity as MainActivity).fragmentTransaction(Foop1Fragment()) }
        btnFoop2.setOnClickListener { (activity as MainActivity).fragmentTransaction(Foop2Fragment()) }
        btnFoop3.setOnClickListener { (activity as MainActivity).fragmentTransaction(Foop3Fragment()) }
    }
}
