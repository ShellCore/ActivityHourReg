package com.shell.android.registropraxis.ui.foop3list.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.shell.android.registropraxis.R
import com.shell.android.registropraxis.db.models.Foop3Data
import com.shell.android.shellcorebaselibrary.utils.inflate
import kotlinx.android.synthetic.main.item_foop3_register.view.*

class Foop3ListAdapter(

        var registers: List<Foop3Data>,
        var listener: Foop3ListListener

) : RecyclerView.Adapter<Foop3ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder
            = ViewHolder(parent.inflate(R.layout.item_foop3_register))

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(registers[position], listener)

    override fun getItemCount(): Int = registers.size

    fun updateRegisterList(registers: List<Foop3Data>) {
        this.registers = registers
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(foop3Data: Foop3Data, listener: Foop3ListListener) = with(itemView) {
            foop3Data.apply {
                txtNum.text = num.toString()
                txtBegin.text = begin
                txtEnd.text = end
                txtScheduledHour.text = scheduledHour.toString()
            }

            setOnClickListener { listener.onRegisterSelected(foop3Data) }
        }
    }
}