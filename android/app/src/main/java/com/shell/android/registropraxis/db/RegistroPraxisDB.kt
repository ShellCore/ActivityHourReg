package com.shell.android.registropraxis.db

import com.raizlabs.android.dbflow.annotation.Database

@Database(name = RegistroPraxisDB.DATABASE_NAME, version = RegistroPraxisDB.VERSION)
class RegistroPraxisDB {

    companion object {
        const val DATABASE_NAME = "Expedientes"
        const val VERSION = 1
    }
}