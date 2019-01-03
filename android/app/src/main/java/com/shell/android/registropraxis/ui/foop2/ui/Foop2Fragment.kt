package com.shell.android.registropraxis.ui.foop2.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*

import com.shell.android.registropraxis.R
import com.shell.android.registropraxis.RegistroPraxisApplication
import com.shell.android.registropraxis.db.models.Foop2Data
import com.shell.android.registropraxis.ui.foop2.Foop2Presenter
import com.shell.android.shellcorebaselibrary.utils.downloadFile
import com.shell.android.shellcorebaselibrary.utils.getText
import com.shell.android.shellcorebaselibrary.utils.setText
import com.shell.android.shellcorebaselibrary.utils.showMessage
import kotlinx.android.synthetic.main.fragment_foop2.*
import javax.inject.Inject

class Foop2Fragment : Fragment(), Foop2View {

    @Inject
    lateinit var presenter: Foop2Presenter

    var foop2Data : Foop2Data = Foop2Data()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.foop2_title)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_foop2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInjection()
        presenter.onCreate()
        presenter.loadSavedFoop2Data()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        activity?.menuInflater?.inflate(R.menu.foop1_data_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
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

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgreesbar() {
        progressBar.visibility = View.GONE
    }

    override fun showMessage(message: String) {
        conFoop2Data.showMessage(message)
    }

    override fun loadData(foop2Data: Foop2Data) {
        this.foop2Data = foop2Data
        tilApplication.setText(foop2Data.application)
        tilDescription.setText(foop2Data.description)
        tilDescriptionLarge.setText(foop2Data.descriptionLarge)
        tilJustification.setText(foop2Data.justification)
    }

    override fun cleanData() {
        tilApplication.setText("")
        tilDescription.setText("")
        tilDescriptionLarge.setText("")
        tilJustification.setText("")
        presenter.cleanFoop2Data(this.foop2Data)
    }

    override fun saveData() {
        this.foop2Data.apply {
            application = tilApplication.getText()
            description = tilDescription.getText()
            descriptionLarge = tilDescriptionLarge.getText()
            justification = tilJustification.getText()
            presenter.saveFoop2Data(this)
        }
    }

    override fun generatePdf() {
        presenter.generateFoop2Pdf()
    }

    override fun downloadPdf(url: String) {
        url.downloadFile(context!!)
    }

    private fun setupInjection() {
        val app = activity!!.application as RegistroPraxisApplication
        val component = app.getFoop2Component(this)
        component.inject(this)
    }
}
