package com.shell.android.registropraxis.ui.foop1.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.raizlabs.android.dbflow.kotlinextensions.save

import com.shell.android.registropraxis.R
import com.shell.android.registropraxis.RegistroPraxisApplication
import com.shell.android.registropraxis.db.models.Foop1Data
import com.shell.android.registropraxis.ui.foop1.Foop1Presenter
import com.shell.android.shellcorebaselibrary.utils.downloadFile
import com.shell.android.shellcorebaselibrary.utils.getText
import com.shell.android.shellcorebaselibrary.utils.setText
import com.shell.android.shellcorebaselibrary.utils.showMessage
import kotlinx.android.synthetic.main.fragment_foop1.*
import javax.inject.Inject

class Foop1Fragment : Fragment(), Foop1View {

    @Inject
    lateinit var presenter: Foop1Presenter

    var foop1Data: Foop1Data = Foop1Data()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.foop1_title)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_foop1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInjection()
        presenter.onCreate()
        presenter.loadSavedFoop1Data()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        activity?.menuInflater?.inflate(R.menu.foop1_data_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menuClean -> cleanData()
            R.id.menuSave -> saveData()
            R.id.menuGeneratePdf -> generatePdf()
        }
        return true
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgreesbar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgreesbar() {
        progressBar.visibility = View.GONE
    }

    override fun showMessage(message: String) {
        conFoop1Data.showMessage(message)
    }

    override fun loadData(foop1Data: Foop1Data) {
        this.foop1Data = foop1Data
        tilJob.setText(foop1Data.job)
        tilDepartment.setText(foop1Data.department)
    }

    override fun cleanData() {
        tilJob.setText("")
        tilDepartment.setText("")
        presenter.cleanFoop1Data(this.foop1Data)
    }

    override fun saveData() {
        this.foop1Data.apply {
            job = tilJob.getText()
            department = tilDepartment.getText()
            presenter.saveFoop1Data(this)
        }
    }

    override fun generatePdf() {
        presenter.generateFoop1Pdf()
    }

    override fun downloadPdf(url: String) {
        url.downloadFile(context!!)
    }

    private fun setupInjection() {
        val app = activity!!.application as RegistroPraxisApplication
        val component = app.getFoop1Component(this)
        component.inject(this)
    }
}
