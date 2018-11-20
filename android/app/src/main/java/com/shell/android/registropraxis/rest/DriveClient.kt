package com.shell.android.registropraxis.rest

import com.shell.android.registropraxis.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DriveClient {

    var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.DRIVE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getDriveClient() = retrofit.create(DriveService::class.java)
}