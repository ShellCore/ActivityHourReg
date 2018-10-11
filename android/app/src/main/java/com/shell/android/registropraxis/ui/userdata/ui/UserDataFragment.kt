package com.shell.android.registropraxis.ui.userdata.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shell.android.registropraxis.R

class UserDataFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.mainMenu_userData)
        return inflater.inflate(R.layout.fragment_user_data, container, false)
    }
}
