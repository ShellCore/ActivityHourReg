package com.shell.android.registropraxis.ui.clientdata.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.shell.android.registropraxis.R
import com.shell.android.registropraxis.RegistroPraxisApplication
import com.shell.android.registropraxis.db.models.ClientData
import com.shell.android.registropraxis.ui.clientdata.ClientDataPresenter
import com.shell.android.shellcorebaselibrary.utils.getText
import com.shell.android.shellcorebaselibrary.utils.setText
import com.shell.android.shellcorebaselibrary.utils.showMessage
import kotlinx.android.synthetic.main.fragment_client_data.*
import javax.inject.Inject

class ClientDataFragment : Fragment(), ClientDataView {

    @Inject
    lateinit var presenter: ClientDataPresenter

    var clientData: ClientData = ClientData()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.mainMenu_clientData)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_client_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInjection()
        presenter.onCreate()
        presenter.loadSavedClientData()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        activity?.menuInflater?.inflate(R.menu.client_data_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuClean -> cleanData()
            R.id.menuSave -> saveData()
        }
        return true
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgressbar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressbar() {
        progressBar.visibility = View.GONE
    }

    override fun showMessage(message: String) {
        conClientData.showMessage(message)
    }

    override fun loadData(clientData: ClientData) {
        this.clientData = clientData
        tilContact.setText(clientData.contact)
        tilApplicant.setText(clientData.client)
        tilCompany.setText(clientData.company)
        tilAssignKey.setText(clientData.assignKey)
    }

    override fun cleanData() {
        tilContact.setText("")
        tilApplicant.setText("")
        tilCompany.setText("")
        tilAssignKey.setText("")
        presenter.cleanClientData(this.clientData)
    }

    override fun saveData() {
        this.clientData.apply {
            contact = tilContact.getText()
            client = tilApplicant.getText()
            company = tilCompany.getText()
            assignKey = tilAssignKey.getText()
            presenter.saveClientData(this)
        }
    }

    private fun setupInjection() {
        val app = activity!!.application as RegistroPraxisApplication
        val component = app.getClientDataComponent(this)
        component.inject(this)
    }
}


