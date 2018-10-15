package com.shell.android.registropraxis.ui.userdata.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.shell.android.registropraxis.R
import com.shell.android.registropraxis.RegistroPraxisApplication
import com.shell.android.registropraxis.db.models.UserData
import com.shell.android.registropraxis.ui.userdata.UserDataPresenter
import com.shell.android.shellcorebaselibrary.utils.showMessage
import kotlinx.android.synthetic.main.fragment_user_data.*
import javax.inject.Inject

class UserDataFragment : Fragment(), UserDataView {

    @Inject
    lateinit var presenter: UserDataPresenter

    var userData: UserData = UserData()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.mainMenu_userData)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_user_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInjection()
        presenter.onCreate()
        presenter.loadSavedUserData()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        activity?.menuInflater?.inflate(R.menu.user_data_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
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

    override fun loadData(userData: UserData) {
        this.userData = userData
        edtName.setText(userData.name)
        edtId.setText(userData.userId)
    }

    override fun cleanData() {
        edtName.setText("")
        edtId.setText("")
        presenter.cleanUserData(this.userData)
    }

    override fun saveData() {
        this.userData.name = edtName.text.toString()
        this.userData.userId = edtId.text.toString()
        presenter.saveUserData(this.userData)
    }

    override fun showMessage(message: String) {
        conUserData.showMessage(message)
    }

    private fun setupInjection() {
        val app = activity!!.application as RegistroPraxisApplication
        val component = app.getUserDataComponent(this)
        component.inject(this)
    }
}
