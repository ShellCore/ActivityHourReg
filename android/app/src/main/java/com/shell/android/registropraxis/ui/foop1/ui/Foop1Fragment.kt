package com.shell.android.registropraxis.ui.foop1.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.shell.android.registropraxis.R

class Foop1Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.foop1_title)
        return inflater.inflate(R.layout.fragment_foop1, container, false)
    }
}
