package com.shell.android.registropraxis.ui.foop2.di

import com.shell.android.registropraxis.libs.di.LibsModule
import com.shell.android.registropraxis.rest.di.RestModule
import com.shell.android.registropraxis.ui.foop2.ui.Foop2Fragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [LibsModule::class, RestModule::class, Foop2Module::class])
interface Foop2Component {
    fun inject(fragment: Foop2Fragment)
}