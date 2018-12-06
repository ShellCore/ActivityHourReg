package com.shell.android.registropraxis.ui.foop2.di

import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.rest.DriveService
import com.shell.android.registropraxis.ui.foop2.*
import com.shell.android.registropraxis.ui.foop2.ui.Foop2View
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class Foop2Module (val view: Foop2View) {

    @Provides
    @Singleton
    fun providesFoop2Presenter(eventbus: EventBus, view: Foop2View, interactor: Foop2Interactor): Foop2Presenter
            = Foop2PresenterImpl(eventbus, view, interactor)

    @Provides
    @Singleton
    fun providesFoop2View() : Foop2View = view

    @Provides
    @Singleton
    fun providesFoop2Interactor(eventBus: EventBus, repository: Foop2Repository): Foop2Interactor
            = Foop2InteractorImpl(eventBus, repository)

    @Provides
    @Singleton
    fun providesFoop2Repository(eventBus: EventBus, service: DriveService): Foop2Repository
            = Foop2RepositoryImpl(eventBus, service)
}