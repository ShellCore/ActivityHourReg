package com.shell.android.registropraxis.db.models

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import com.shell.android.registropraxis.db.RegistroPraxisDB
import java.util.*

@Table(database = RegistroPraxisDB::class)
data class Day (

        @PrimaryKey(autoincrement = true)
        var id: Long = 0L,

        @Column
        var day: Date = Date(),

        @Column
        var begin: String = "",

        @Column
        var food: String = "",

        @Column
        var foodEnd: String = "",

        @Column
        var end: String = "",

        @Column
        var comments: String?

): BaseModel() {
}