package com.shell.android.registropraxis.rest

import com.shell.android.registropraxis.rest.postassistance.PostAssistanceRequest
import com.shell.android.registropraxis.rest.postassistance.PostAssistanceResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DriveService {

    @POST
    fun postAssistance(@Body request : PostAssistanceRequest) : Call<PostAssistanceResponse>
}