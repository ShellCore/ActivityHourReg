package com.shell.android.registropraxis.ui.registerdetail.di

import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.rest.DriveService
import com.shell.android.registropraxis.ui.registerdetail.*
import com.shell.android.registropraxis.ui.registerdetail.ui.RegisterDetailView
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RegisterDetailModule(val view: RegisterDetailView) {

    @Provides
    @Singleton
    fun providesRegisterDetailPresenter(eventBus: EventBus, view: RegisterDetailView?, interactor: RegisterDetailInteractor): RegisterDetailPresenter
            = RegisterDetailPresenterImpl(eventBus, view, interactor)

    @Provides
    @Singleton
    fun providesRegisterDetailView() = view

    @Provides
    @Singleton
    fun providesRegisterDetailInteractor(repository: RegisterDetailRepository): RegisterDetailInteractor
            = RegisterDetailInteractorImpl(repository)

    @Provides
    @Singleton
    fun providesRegisterDetailRepository(eventBus: EventBus, service: DriveService): RegisterDetailRepository
            = RegisterDetailRepositoryImpl(eventBus, service)
}