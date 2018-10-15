package com.shell.android.registropraxis.libs.di

import com.shell.android.registropraxis.libs.GreenRobotEventBus
import com.shell.android.registropraxis.libs.base.EventBus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LibsModule {

    @Provides
    @Singleton
    fun providesEventBus(eventBus: org.greenrobot.eventbus.EventBus): EventBus = GreenRobotEventBus(eventBus)

    @Provides
    @Singleton
    fun providesLibraryEventBus(): org.greenrobot.eventbus.EventBus = org.greenrobot.eventbus.EventBus.getDefault()
}