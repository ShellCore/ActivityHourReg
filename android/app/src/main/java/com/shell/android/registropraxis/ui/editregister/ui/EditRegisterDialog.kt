package com.shell.android.registropraxis.ui.editregister.ui


import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import com.shell.android.registropraxis.R
import com.shell.android.registropraxis.db.models.Day
import com.shell.android.shellcorebaselibrary.utils.*
import kotlinx.android.synthetic.main.dialog_edit_register.*
import java.util.*

class EditRegisterDialog : AppCompatDialogFragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    // Variables
    lateinit var customView: View
    lateinit var day: Day
    lateinit var listener: OnConditionClickListener
    var txtSelected : TextView? = null

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
            txtDay!!.text = this.day.getFormattedDaySimple()
            txtIn.text = begin
            txtFood.text = food
            txtWork.text = foodEnd
            txtOut.text = end
            tilComments.setText(comments)
        }

        setupOnClick()
    }

    override fun onDateSet(datePicker: DatePicker, year: Int, month: Int, day: Int) {
        txtDay.text = "$day"
    }

    override fun onTimeSet(timePicker: TimePicker, hour: Int, minute: Int) {
        val time = "${hour.format2Digits()}:${minute.format2Digits()}"
        txtSelected!!.text = time
        txtSelected = null
    }

    private fun loadCapturedValues() {
        day.apply {
            this.day = Date().setCustomDay(Integer.parseInt(txtDay.text.toString()))
            begin = txtIn.text.toString()
            food = txtFood.text.toString()
            foodEnd = txtWork.text.toString()
            end = txtOut.text.toString()
            comments = tilComments.getText()
        }
    }

    private fun setupOnClick() {
        txtDay.setOnClickListener {
            showDatePickerDialog()
        }

        txtIn.setOnClickListener {
            showTimePickerDialog(txtIn)
        }
        txtFood.setOnClickListener {
            showTimePickerDialog(txtFood)
        }
        txtWork.setOnClickListener {
            showTimePickerDialog(txtWork)
        }
        txtOut.setOnClickListener {
            showTimePickerDialog(txtOut)
        }
    }

    private fun showDatePickerDialog() {
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        DatePickerDialog(context, this, year, month, day).show()
    }

    private fun showTimePickerDialog(selected: TextView) {
        txtSelected = selected
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        TimePickerDialog(context, this, hour, minute, true).show()
    }
}