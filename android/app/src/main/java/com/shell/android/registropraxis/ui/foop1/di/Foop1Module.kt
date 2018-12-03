package com.shell.android.registropraxis.ui.foop1.di

import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.rest.DriveService
import com.shell.android.registropraxis.ui.foop1.*
import com.shell.android.registropraxis.ui.foop1.ui.Foop1View
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class Foop1Module(val view : Foop1View) {

    @Provides
    @Singleton
    fun getFoop1Presenter(eventBus: EventBus, view: Foop1View?, interactor: Foop1Interactor) : Foop1Presenter
            = Foop1PresenterImpl(eventBus, view, interactor)

    @Provides
    @Singleton
    fun getFoop1View() : Foop1View = view

    @Provides
    @Singleton
    fun getFoop1Interactor(eventBus: EventBus, repository: Foop1Repository) : Foop1Interactor
            = Foop1InteractorImpl(eventBus, repository)

    @Provides
    @Singleton
    fun getFoop1Repository(eventBus: EventBus, service: DriveService): Foop1Repository
            = Foop1RepositoryImpl(eventBus, service)
}