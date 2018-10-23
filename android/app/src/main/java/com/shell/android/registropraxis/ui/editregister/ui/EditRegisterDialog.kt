package com.shell.android.registropraxis.ui.editregister.ui


import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shell.android.registropraxis.R
import com.shell.android.registropraxis.db.models.Day
import kotlinx.android.synthetic.main.dialog_edit_register.*

class EditRegisterDialog : AppCompatDialogFragment() {

    // Variables
    lateinit var customView: View
    lateinit var day: Day
    lateinit var listener: OnConditionClickListener


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return customView
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        customView = activity!!.layoutInflater.inflate(R.layout.dialog_edit_register, null)

        return AlertDialog.Builder(context!!)
                .setView(customView)
                .setNegativeButton(getString(R.string.editRegister_btn_cancel)) { _, _ ->
                    dismiss()
                }
                .setPositiveButton(getString(R.string.editRegister_btn_save)) { _, _ ->
                    loadCapturedValues()
                    listener.onClickBtnAccept(day)
                    dismiss()
                }
                .create()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        day.apply {
            tilIn.editText!!.setText(begin)
            tilFood.editText!!.setText(food)
            tilWork.editText!!.setText(foodEnd)
            tilOut.editText!!.setText(end)
            tilComments.editText!!.setText(comments)
        }
    }

    private fun loadCapturedValues() {
        day.apply {
            begin = tilIn.editText!!.getText().toString()
            food = tilFood.editText!!.getText().toString()
            foodEnd = tilWork.editText!!.getText().toString()
            end = tilOut.editText!!.getText().toString()
            comments = tilComments.editText!!.getText().toString()
        }
    }
}