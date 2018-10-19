package com.shell.android.registropraxis.ui.registerdetail.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.shell.android.registropraxis.R
import com.shell.android.registropraxis.db.models.Day
import com.shell.android.shellcorebaselibrary.utils.getFormattedDay
import com.shell.android.shellcorebaselibrary.utils.inflate
import kotlinx.android.synthetic.main.item_day.view.*

class RegisterAdapter(

        var days : List<Day>,
        var listener : DayListener

) : RecyclerView.Adapter<RegisterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder
            = ViewHolder(parent.inflate(R.layout.item_day))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(days[position], listener)

    override fun getItemCount(): Int = days.size

    fun updateDayList(days : List<Day>) {
        this.days = days
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(day: Day, listener: DayListener) = with(itemView) {

            txtDay.text = day.day.getFormattedDay()
            txtIn.text = day.begin
            txtFood.text = day.food
            txtWork.text = day.foodEnd
            txtOut.text = day.end
            txtComments.text = if (day.comments.isNotEmpty()) "..." else ""

            setOnClickListener { listener.onClick(day, adapterPosition) }
        }
    }
}