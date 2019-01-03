package com.shell.android.registropraxis.ui.foop3list.di

import com.shell.android.registropraxis.libs.di.LibsModule
import com.shell.android.registropraxis.rest.di.RestModule
import com.shell.android.registropraxis.ui.foop3list.ui.Foop3ListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [LibsModule::class, RestModule::class, Foop3ListModule::class])
interface Foop3ListComponent {
    fun inject(fragment: Foop3ListFragment)
}