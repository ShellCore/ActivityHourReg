package com.shell.android.registropraxis.ui.foop3list.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.*

import com.shell.android.registropraxis.R
import com.shell.android.registropraxis.RegistroPraxisApplication
import com.shell.android.registropraxis.db.models.Foop3Data
import com.shell.android.registropraxis.ui.foop3detail.ui.EditFoop3Dialog
import com.shell.android.registropraxis.ui.foop3detail.ui.OnFoop3ConditionClickListener
import com.shell.android.registropraxis.ui.foop3list.Foop3ListPresenter
import com.shell.android.registropraxis.ui.foop3list.adapters.Foop3ListListener
import com.shell.android.registropraxis.ui.foop3list.adapters.Foop3ListAdapter
import com.shell.android.shellcorebaselibrary.utils.downloadFile
import com.shell.android.shellcorebaselibrary.utils.showMessage
import kotlinx.android.synthetic.main.fragment_foop3_list.*
import javax.inject.Inject

class Foop3ListFragment : Fragment(), Foop3ListView, Foop3ListListener, OnFoop3ConditionClickListener, View.OnClickListener {

    // Components
    lateinit var listAdapter: Foop3ListAdapter

    // Services
    @Inject
    lateinit var presenter: Foop3ListPresenter

    // Variables
    var registers: List<Foop3Data> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.foop3_list_title)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_foop3_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInjection()
        setupRecyclerView()
        setupOnClick()
        presenter.onCreate()
        presenter.loadRegisterFoop3Data()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        activity!!.menuInflater.inflate(R.menu.register_detail_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuGeneratePdf -> onClickBtnGeneratePdf()
            R.id.menuCleanRegister -> onClickBtnClean()
        }
        return true
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnAdd -> {
                onRegisterSelected(Foop3Data())
            }
        }
    }

    override fun showProgressbar() {
        progressbar.visibility = View.VISIBLE
    }

    override fun hideProgressbar() {
        progressbar.visibility = View.GONE
    }

    override fun showMessage(message: String) {
        conFoop3List.showMessage(message)
    }

    override fun updateRegisterList(registers: List<Foop3Data>) {
        this.registers = registers
        listAdapter.updateRegisterList(this.registers)
    }

    override fun downloadPdf(url: String) {
        url.downloadFile(context!!)
    }

    override fun onRegisterSelected(foop3Data: Foop3Data) {
        val dialog = EditFoop3Dialog()
        dialog.foop3Data = foop3Data
        dialog.listener = this
        dialog.show(fragmentManager, "Foop3ListFragment")
    }

    override fun onClickBtnAccept(foop3Data: Foop3Data) {
        presenter.saveFoop3Data(foop3Data)
    }

    override fun onClickBtnDelete(foop3Data: Foop3Data) {
        presenter.deleteFoop3Data(foop3Data)
    }

    private fun onClickBtnGeneratePdf() {
        presenter.generateFoop3Pdf()
    }

    private fun onClickBtnClean() {
        presenter.cleanRegisters()
    }

    private fun setupRecyclerView() {
        listAdapter = Foop3ListAdapter(registers, this)

        recRegisters.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
            adapter = listAdapter
        }
    }

    private fun setupOnClick() {
        btnAdd.setOnClickListener(this)
    }

    private fun setupInjection() {
        val app = activity!!.application as RegistroPraxisApplication
        val component = app.getFoop3ListComponent(this)
        component.inject(this)
    }
}
