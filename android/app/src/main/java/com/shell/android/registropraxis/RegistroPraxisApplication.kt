package com.shell.android.registropraxis

import android.app.Application
import com.raizlabs.android.dbflow.config.FlowManager

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
}