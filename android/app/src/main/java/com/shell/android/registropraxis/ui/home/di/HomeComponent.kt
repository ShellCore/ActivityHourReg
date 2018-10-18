package com.shell.android.registropraxis.ui.home.di

import com.shell.android.registropraxis.libs.di.LibsModule
import com.shell.android.registropraxis.ui.home.ui.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(LibsModule::class, HomeModule::class))
interface HomeComponent {
    fun inject(fragment: HomeFragment)
}