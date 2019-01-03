package com.shell.android.registropraxis.ui.foop3list.di

import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.rest.DriveService
import com.shell.android.registropraxis.ui.foop3list.*
import com.shell.android.registropraxis.ui.foop3list.ui.Foop3ListView
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class Foop3ListModule (val view: Foop3ListView) {

    @Provides
    @Singleton
    fun providesFoop3ListPresenter(eventBus: EventBus, view: Foop3ListView?, interactor: Foop3ListInteractor): Foop3ListPresenter
    = Foop3ListPresenterImpl(eventBus, view, interactor)

    @Provides
    @Singleton
    fun providesFoop3ListView() = view

    @Provides
    @Singleton
    fun providesFoop3ListInteractor(eventBus: EventBus, repository: Foop3ListRepository): Foop3ListInteractor
    = Foop3ListInteractorImpl(eventBus, repository)

    @Provides
    @Singleton
    fun providesFoop3ListRepository(eventBus: EventBus, service: DriveService): Foop3ListRepository
    = Foop3ListRepositoryImpl(eventBus, service)
}