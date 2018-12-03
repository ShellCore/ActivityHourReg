package com.shell.android.registropraxis.rest

import com.shell.android.registropraxis.rest.services.postassistance.PostAssistanceRequest
import com.shell.android.registropraxis.rest.services.postassistance.PostAssistanceResponse
import com.shell.android.registropraxis.rest.services.postfoop1.PostFoop1Request
import com.shell.android.registropraxis.rest.services.postfoop1.PostFoop1Response
import com.shell.android.registropraxis.rest.services.postfoop2.PostFoop2Request
import com.shell.android.registropraxis.rest.services.postfoop2.PostFoop2Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DriveService {

    @POST("AKfycbx4BkeOl10NqoCtDlLn61mn2QPCZtT5PWRuAMR2ByRzv91Nlgg/exec")
    fun postAssistance(@Body request : PostAssistanceRequest) : Call<PostAssistanceResponse>

    @POST("AKfycby-8CHw8PYgGxY1NZ2PI1ykOPhnhBEDH55U4EmbX20xeYRSclo/exec")
    fun postFoop1(@Body request : PostFoop1Request) : Call<PostFoop1Response>

    @POST("AKfycbzppOS0cVOxNTDRYVpkOdNyLup0QThcZCEq-oMw/exec")
    fun postFoop2(@Body request: PostFoop2Request) : Call<PostFoop2Response>
}