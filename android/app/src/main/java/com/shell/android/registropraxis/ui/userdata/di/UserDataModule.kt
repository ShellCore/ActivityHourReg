package com.shell.android.registropraxis.ui.userdata.di

import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.ui.userdata.*
import com.shell.android.registropraxis.ui.userdata.ui.UserDataView
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserDataModule(val view: UserDataView) {

    @Provides
    @Singleton
    fun providesUserDataPresenter(eventBus: EventBus, view: UserDataView?, interactor: UserDataInteractor): UserDataPresenter
            = UserDataPresenterImpl(eventBus, view, interactor)

    @Provides
    @Singleton
    fun providesUserDataView() : UserDataView = view

    @Provides
    @Singleton
    fun providesUserDataInteractor(eventBus: EventBus, repository: UserDataRepository): UserDataInteractor
            = UserDataInteractorImpl(eventBus, repository)

    @Provides
    @Singleton
    fun providesUserDataRepository(eventBus: EventBus): UserDataRepository
            = UserDataRepositoryImpl(eventBus)
}