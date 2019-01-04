package com.shell.android.registropraxis.db.models

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import com.shell.android.registropraxis.db.RegistroPraxisDB

@Table(database = RegistroPraxisDB::class)
class Foop3Data (

        @PrimaryKey(autoincrement = true)
        @Column
        var id : Long = 0L,

        @Column
        var num : Int = 0,

        @Column
        var description : String = "",

        @Column
        var app : String = "",

        @Column
        var begin : String = "",

        @Column
        var end : String = "",

        @Column
        var scheduledHour : Int = 0

) : BaseModel()