package com.shell.android.registropraxis.ui.foop1.di

import com.shell.android.registropraxis.libs.di.LibsModule
import com.shell.android.registropraxis.rest.di.RestModule
import com.shell.android.registropraxis.ui.foop1.ui.Foop1Fragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [LibsModule::class, RestModule::class, Foop1Module::class])
interface Foop1Component {
    fun inject(fragment: Foop1Fragment)
}