package com.shell.android.registropraxis.ui.userdata.di

import com.shell.android.registropraxis.libs.di.LibsModule
import com.shell.android.registropraxis.ui.userdata.ui.UserDataFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [LibsModule::class, UserDataModule::class])
interface UserDataComponent {
    fun inject(fragment: UserDataFragment)
}