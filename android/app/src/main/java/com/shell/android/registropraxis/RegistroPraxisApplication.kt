package com.shell.android.registropraxis

import android.app.Application
import com.raizlabs.android.dbflow.config.FlowManager
import com.shell.android.registropraxis.libs.di.LibsModule
import com.shell.android.registropraxis.rest.di.RestModule
import com.shell.android.registropraxis.ui.clientdata.di.ClientDataModule
import com.shell.android.registropraxis.ui.clientdata.di.DaggerClientDataComponent
import com.shell.android.registropraxis.ui.clientdata.ui.ClientDataView
import com.shell.android.registropraxis.ui.foop1.di.DaggerFoop1Component
import com.shell.android.registropraxis.ui.foop1.di.Foop1Module
import com.shell.android.registropraxis.ui.foop1.ui.Foop1View
import com.shell.android.registropraxis.ui.foop2.di.DaggerFoop2Component
import com.shell.android.registropraxis.ui.foop2.di.Foop2Module
import com.shell.android.registropraxis.ui.foop2.ui.Foop2View
import com.shell.android.registropraxis.ui.foop3list.di.DaggerFoop3ListComponent
import com.shell.android.registropraxis.ui.foop3list.di.Foop3ListModule
import com.shell.android.registropraxis.ui.foop3list.ui.Foop3ListView
import com.shell.android.registropraxis.ui.home.di.DaggerHomeComponent
import com.shell.android.registropraxis.ui.home.di.HomeModule
import com.shell.android.registropraxis.ui.home.ui.HomeView
import com.shell.android.registropraxis.ui.registerdetail.di.DaggerRegisterDetailComponent
import com.shell.android.registropraxis.ui.registerdetail.di.RegisterDetailModule
import com.shell.android.registropraxis.ui.registerdetail.ui.RegisterDetailView
import com.shell.android.registropraxis.ui.userdata.di.DaggerUserDataComponent
import com.shell.android.registropraxis.ui.userdata.di.UserDataModule
import com.shell.android.registropraxis.ui.userdata.ui.UserDataView

class RegistroPraxisApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        createDatabase()
    }

    override fun onTerminate() {
        super.onTerminate()
        teardownDatabase()
    }

    private fun createDatabase() = FlowManager.init(this)

    private fun teardownDatabase() = FlowManager.destroy()

    fun getUserDataComponent(view: UserDataView) = DaggerUserDataComponent.builder()
            .libsModule(LibsModule())
            .userDataModule(UserDataModule(view))
            .build()!!

    fun getClientDataComponent(view: ClientDataView) = DaggerClientDataComponent.builder()
            .libsModule(LibsModule())
            .clientDataModule(ClientDataModule(view))
            .build()!!

    fun getHomeComponent(view: HomeView) = DaggerHomeComponent.builder()
            .libsModule(LibsModule())
            .homeModule(HomeModule(view))
            .build()!!

    fun getRegisterDetailComponent(view: RegisterDetailView) = DaggerRegisterDetailComponent.builder()
            .libsModule(LibsModule())
            .restModule(RestModule())
            .registerDetailModule(RegisterDetailModule(view))
            .build()!!

    fun getFoop1Component(view: Foop1View) = DaggerFoop1Component.builder()
            .libsModule(LibsModule())
            .restModule(RestModule())
            .foop1Module(Foop1Module(view))
            .build()!!

    fun getFoop2Component(view: Foop2View) = DaggerFoop2Component.builder()
            .libsModule(LibsModule())
            .restModule(RestModule())
            .foop2Module(Foop2Module(view))
            .build()!!

    fun getFoop3ListComponent(view: Foop3ListView) = DaggerFoop3ListComponent.builder()
            .libsModule(LibsModule())
            .restModule(RestModule())
            .foop3ListModule(Foop3ListModule(view))
            .build()!!
}