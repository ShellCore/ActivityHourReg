package com.shell.android.registropraxis.db.models

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import com.shell.android.registropraxis.db.RegistroPraxisDB

@Table(database = RegistroPraxisDB::class)
data class UserData(

        @PrimaryKey(autoincrement = true)
        @Column
        var id: Long = 0L,

        @Column
        var name: String = "",

        @Column
        var userId: String = ""

) : BaseModel()