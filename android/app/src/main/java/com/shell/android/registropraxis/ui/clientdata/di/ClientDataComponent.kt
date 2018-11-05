package com.shell.android.registropraxis.ui.clientdata.di

import com.shell.android.registropraxis.libs.di.LibsModule
import com.shell.android.registropraxis.ui.clientdata.ui.ClientDataFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [LibsModule::class, ClientDataModule::class])
interface ClientDataComponent {
    fun inject(fragment: ClientDataFragment)
}