package com.shell.android.registropraxis.ui.clientdata.di

import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.ui.clientdata.*
import com.shell.android.registropraxis.ui.clientdata.ui.ClientDataView
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ClientDataModule(val view: ClientDataView) {

    @Provides
    @Singleton
    fun getClientDataPresenter(eventBus: EventBus, view: ClientDataView, interactor: ClientDataInteractor): ClientDataPresenter
            = ClientDataPresenterImpl(eventBus, view, interactor)

    @Provides
    @Singleton
    fun getClientDataView() : ClientDataView = view

    @Provides
    @Singleton
    fun getClientDataInteractor(eventBus: EventBus, repository: ClientDataRepository): ClientDataInteractor
            = ClientDataInteractorImpl(eventBus, repository)

    @Provides
    @Singleton
    fun getClientDataRepository(eventBus: EventBus): ClientDataRepository
            = ClientDataRepositoryImpl(eventBus)
}