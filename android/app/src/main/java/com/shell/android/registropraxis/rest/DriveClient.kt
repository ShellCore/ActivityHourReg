package com.shell.android.registropraxis.rest

import com.shell.android.registropraxis.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DriveClient {

    var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.DRIVE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()

    fun getDriveService() = retrofit.create(DriveService::class.java)

    private fun getClient(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(getLoggingInterceptor())
            .build()

    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        }
        return logging
    }
}