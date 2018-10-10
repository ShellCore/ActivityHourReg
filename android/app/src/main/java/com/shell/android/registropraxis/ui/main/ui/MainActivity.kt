package com.shell.android.registropraxis.ui.main.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.shell.android.registropraxis.R
import com.shell.android.registropraxis.ui.userdata.ui.UserDataActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnUserData.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        startActivity(Intent(this, UserDataActivity::class.java))
    }
}
