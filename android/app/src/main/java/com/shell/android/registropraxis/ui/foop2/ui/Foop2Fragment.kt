package com.shell.android.registropraxis.ui.foop2.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.shell.android.registropraxis.R

class Foop2Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.foop2_title)
        return inflater.inflate(R.layout.fragment_foop2, container, false)
    }
}
