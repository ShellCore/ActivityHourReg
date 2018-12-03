package com.shell.android.registropraxis.rest.services.postassistance

import com.shell.android.registropraxis.db.models.ClientData
import com.shell.android.registropraxis.db.models.Day
import com.shell.android.registropraxis.db.models.UserData

class PostAssistanceRequest(

        var user   : UserData,
        var client : ClientData,
        var month  : List<Day>
)