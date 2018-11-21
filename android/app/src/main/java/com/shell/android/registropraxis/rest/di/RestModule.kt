package com.shell.android.registropraxis.rest.di

import com.shell.android.registropraxis.rest.DriveClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RestModule {

    @Provides
    @Singleton
    fun providesDriveService() = DriveClient().getDriveService()
}