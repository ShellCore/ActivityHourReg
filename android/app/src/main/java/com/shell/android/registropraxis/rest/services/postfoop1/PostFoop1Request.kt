package com.shell.android.registropraxis.rest.services.postfoop1

import com.shell.android.registropraxis.db.models.ClientData
import com.shell.android.registropraxis.db.models.Foop1Data
import com.shell.android.registropraxis.db.models.UserData

class PostFoop1Request (

        var user: UserData,
        var client: ClientData,
        var foop1: Foop1Data
)