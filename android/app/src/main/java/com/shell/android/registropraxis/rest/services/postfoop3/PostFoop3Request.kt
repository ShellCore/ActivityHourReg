package com.shell.android.registropraxis.rest.services.postfoop3

import com.shell.android.registropraxis.db.models.ClientData
import com.shell.android.registropraxis.db.models.Foop3Data
import com.shell.android.registropraxis.db.models.UserData

class PostFoop3Request (

        var user: UserData,
        var client: ClientData,
        var foop3: List<Foop3Data>
)