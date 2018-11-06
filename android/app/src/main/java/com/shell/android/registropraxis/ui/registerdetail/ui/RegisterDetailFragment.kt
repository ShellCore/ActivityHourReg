package com.shell.android.registropraxis.ui.registerdetail.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shell.android.registropraxis.R
import com.shell.android.registropraxis.RegistroPraxisApplication
import com.shell.android.registropraxis.db.models.Day
import com.shell.android.registropraxis.ui.editregister.ui.EditRegisterDialog
import com.shell.android.registropraxis.ui.editregister.ui.OnConditionClickListener
import com.shell.android.registropraxis.ui.registerdetail.RegisterDetailPresenter
import com.shell.android.registropraxis.ui.registerdetail.adapters.DayListener
import com.shell.android.registropraxis.ui.registerdetail.adapters.RegisterAdapter
import com.shell.android.shellcorebaselibrary.utils.showMessage
import kotlinx.android.synthetic.main.fragment_register_detail.*
import javax.inject.Inject

class RegisterDetailFragment : Fragment(), RegisterDetailView, DayListener, OnConditionClickListener, View.OnClickListener {
    // Components
    lateinit var registerAdapter: RegisterAdapter

    // Services
    @Inject
    lateinit var presenter: RegisterDetailPresenter

    // Variables
    var days: List<Day> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.registerDetail_title)
        return inflater.inflate(R.layout.fragment_register_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInjection()
        setupRecyclerView()
        setupOnclick()
        presenter.onCreate()
        presenter.loadRegisterMonth()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnAdd -> {
                onDaySelected(Day())
            }
        }
    }

    override fun showProgressBar() {
        crdList.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
        crdList.visibility = View.VISIBLE
    }

    override fun showMessage(message: String) {
        conRegisterDetail.showMessage(message)
    }

    override fun updateDayList(days: List<Day>) {
        this.days = days
        registerAdapter.updateDayList(this.days)
    }

    override fun onDaySelected(day: Day) {
        val dialog = EditRegisterDialog()
        dialog.day = day
        dialog.listener = this
        dialog.show(fragmentManager, "RegisterDetailFragment")
    }

    override fun onClickBtnAccept(day: Day) {
        presenter.saveRegister(day)
    }

    override fun onClickBtnDelete(day: Day) {
        presenter.deleteRegister(day)
    }

    private fun setupInjection() {
        val app = activity!!.application as RegistroPraxisApplication
        val component = app.getRegisterDetailComponent(this)
        component.inject(this)
    }

    private fun setupRecyclerView() {
        registerAdapter = RegisterAdapter(days, this)

        recRegisters.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
            adapter = registerAdapter
        }
    }

    private fun setupOnclick() {
        btnAdd.setOnClickListener(this)
    }
}
