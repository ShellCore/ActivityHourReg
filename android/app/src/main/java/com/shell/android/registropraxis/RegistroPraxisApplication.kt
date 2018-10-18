package com.shell.android.registropraxis

import android.app.Application
import com.raizlabs.android.dbflow.config.FlowManager
import com.shell.android.registropraxis.libs.di.LibsModule
import com.shell.android.registropraxis.ui.home.di.DaggerHomeComponent
import com.shell.android.registropraxis.ui.home.di.HomeComponent
import com.shell.android.registropraxis.ui.home.di.HomeModule
import com.shell.android.registropraxis.ui.home.ui.HomeView
import com.shell.android.registropraxis.ui.userdata.di.DaggerUserDataComponent
import com.shell.android.registropraxis.ui.userdata.di.UserDataComponent
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

    fun getUserDataComponent(view: UserDataView) : UserDataComponent {
        return DaggerUserDataComponent.builder()
                .libsModule(LibsModule())
                .userDataModule(UserDataModule(view))
                .build()
    }

    fun getHomeComponent(view: HomeView): HomeComponent {
        return DaggerHomeComponent.builder()
                .libsModule(LibsModule())
                .homeModule(HomeModule(view))
                .build()
    }
}