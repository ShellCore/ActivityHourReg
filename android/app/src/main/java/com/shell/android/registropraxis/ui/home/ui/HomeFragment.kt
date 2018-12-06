package com.shell.android.registropraxis.ui.home.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shell.android.registropraxis.R
import com.shell.android.registropraxis.RegistroPraxisApplication
import com.shell.android.registropraxis.db.models.Day
import com.shell.android.registropraxis.ui.home.HomePresenter
import com.shell.android.registropraxis.ui.main.ui.MainActivity
import com.shell.android.registropraxis.ui.registerdetail.ui.RegisterDetailFragment
import com.shell.android.shellcorebaselibrary.utils.getFormattedDay
import com.shell.android.shellcorebaselibrary.utils.getFormattedHour
import com.shell.android.shellcorebaselibrary.utils.getFormattedMonthYear
import com.shell.android.shellcorebaselibrary.utils.showMessage
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import javax.inject.Inject

class HomeFragment : Fragment(), HomeView, View.OnClickListener {

    private var actualDate:Date = Calendar.getInstance().time

    // Services
    @Inject
    lateinit var presenter: HomePresenter

    // Variables
    lateinit var day: Day


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.mainMenu_home)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtDay.text = actualDate.getFormattedDay()
        txtMonth.text = actualDate.getFormattedMonthYear()
        setupOnClicks()
        setupInjection()
        presenter.onCreate()
        presenter.loadActualDay()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onClick(itemView: View) {
        when (itemView.id) {
            R.id.btnIn -> setHourRegistration(Day.TIME_IN, Calendar.getInstance().time)
            R.id.btnFood -> setHourRegistration(Day.TIME_FOOD, Calendar.getInstance().time)
            R.id.btnWork -> setHourRegistration(Day.TIME_WORK, Calendar.getInstance().time)
            R.id.btnOut -> setHourRegistration(Day.TIME_OUT, Calendar.getInstance().time)
            R.id.btnEdit -> navigateToEditDay()
        }
    }

    override fun showProgressbar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressbar() {
        progressBar.visibility = View.GONE
    }

    override fun showMessage(message: String) {
        conHome.showMessage(message)
    }

    override fun setLoadedDay(day: Day) {
        this.day = day
    }

    override fun navigateToEditDay() {
        (activity as MainActivity).fragmentTransaction(RegisterDetailFragment())
    }

    private fun setupOnClicks() {
        btnIn.setOnClickListener(this)
        btnFood.setOnClickListener(this)
        btnWork.setOnClickListener(this)
        btnOut.setOnClickListener(this)
        btnEdit.setOnClickListener(this)
    }

    private fun setupInjection() {
        val app = activity!!.application as RegistroPraxisApplication
        val component = app.getHomeComponent(this)
        component.inject(this)
    }

    private fun setHourRegistration(time: Int, registeredDate: Date) {
        when (time) {
            Day.TIME_IN -> day.begin = registeredDate.getFormattedHour()
            Day.TIME_FOOD -> day.food = registeredDate.getFormattedHour()
            Day.TIME_WORK -> day.work = registeredDate.getFormattedHour()
            Day.TIME_OUT -> day.end = registeredDate.getFormattedHour()
        }
        presenter.saveActualDay(day)
    }

}
