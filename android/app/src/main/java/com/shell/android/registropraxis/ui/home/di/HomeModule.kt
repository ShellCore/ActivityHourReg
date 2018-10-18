package com.shell.android.registropraxis.ui.home.di

import com.shell.android.registropraxis.libs.base.EventBus
import com.shell.android.registropraxis.ui.home.*
import com.shell.android.registropraxis.ui.home.ui.HomeView
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HomeModule(val view: HomeView) {

    @Provides
    @Singleton
    fun providesHomePresenter(eventBus: EventBus, view: HomeView?, interactor: HomeInteractor) : HomePresenter
            = HomePresenterImpl(eventBus, view, interactor)

    @Provides
    @Singleton
    fun providesHomeView() : HomeView = view

    @Provides
    @Singleton
    fun providesHomeInteractor(repository: HomeRepository): HomeInteractor
            = HomeInteractorImpl(repository)

    @Provides
    @Singleton
    fun providesHomeRepository(eventBus: EventBus) : HomeRepository
            = HomeRepositoryImpl(eventBus)
}