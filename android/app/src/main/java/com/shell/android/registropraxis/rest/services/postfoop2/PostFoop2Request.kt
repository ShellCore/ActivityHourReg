package com.shell.android.registropraxis.rest.services.postfoop2

import com.shell.android.registropraxis.db.models.ClientData
import com.shell.android.registropraxis.db.models.Foop2Data
import com.shell.android.registropraxis.db.models.UserData

class PostFoop2Request (

        var user : UserData,
        var client : ClientData,
        var foop2 : Foop2Data

)