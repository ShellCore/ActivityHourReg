package com.shell.android.registropraxis.ui.foop3detail.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.shell.android.registropraxis.R
import com.shell.android.registropraxis.db.models.Foop3Data
import com.shell.android.shellcorebaselibrary.utils.getText
import com.shell.android.shellcorebaselibrary.utils.setText
import kotlinx.android.synthetic.main.dialog_edit_foop3.*

class EditFoop3Dialog : AppCompatDialogFragment() {

    // Variables
    lateinit var customView: View
    lateinit var foop3Data: Foop3Data
    lateinit var listener: OnFoop3ConditionClickListener
    var txtSelected: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return customView
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        customView = activity!!.layoutInflater.inflate(R.layout.dialog_edit_foop3, null)

        return AlertDialog.Builder(context!!)
                .setTitle("Detalle de registro")
                .setView(customView)
                .setNeutralButton(getString(R.string.editFoop3_btn_delete)) { _, _ ->
                    listener.onClickBtnDelete(foop3Data)
                    dismiss()
                }
                .setNegativeButton(getString(R.string.editFoop3_btn_cancel)) { _, _ ->
                    dismiss()
                }
                .setPositiveButton(getString(R.string.editFoop3_btn_save)) { _, _ ->
                    loadCapturedValues()
                    if (isDataValid()) {
                        listener.onClickBtnAccept(foop3Data)
                        dismiss()
                    } else {
                        Toast.makeText(context, "Se requiere que los datos del documento tenga valores válidos", Toast.LENGTH_SHORT)
                                .show()
                    }
                }
                .create()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        foop3Data.apply {
            tilNumber.setText(num.toString())
            tilDescription.setText(description)
            tilApp.setText(app)
            tilBegin.setText(begin)
            tilEnd.setText(end)
            tilScheduledHour.setText(scheduledHour.toString())
        }
    }

    private fun loadCapturedValues() {
        foop3Data.apply {
            num = Integer.parseInt(tilNumber.getText())
            description = tilDescription.getText()
            app = tilApp.getText()
            begin = tilBegin.getText()
            end = tilEnd.getText()
            scheduledHour = Integer.parseInt(tilScheduledHour.getText())
        }
    }

    private fun isDataValid(): Boolean {
        return foop3Data.num != 0
                && foop3Data.description.isNotEmpty()
                && foop3Data.app.isNotEmpty()
                && foop3Data.begin.isNotEmpty()
                && foop3Data.end.isNotEmpty()
                && foop3Data.scheduledHour != 0
    }
}