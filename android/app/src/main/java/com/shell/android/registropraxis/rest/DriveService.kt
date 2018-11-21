package com.shell.android.registropraxis.rest

import com.shell.android.registropraxis.rest.services.postassistance.PostAssistanceRequest
import com.shell.android.registropraxis.rest.services.postassistance.PostAssistanceResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DriveService {

    @POST("AKfycbx4BkeOl10NqoCtDlLn61mn2QPCZtT5PWRuAMR2ByRzv91Nlgg/exec")
    fun postAssistance(@Body request : PostAssistanceRequest) : Call<PostAssistanceResponse>
}