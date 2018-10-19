package com.shell.android.registropraxis.ui.registerdetail.di

import com.shell.android.registropraxis.libs.di.LibsModule
import com.shell.android.registropraxis.ui.registerdetail.ui.RegisterDetailFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(LibsModule::class, RegisterDetailModule::class))
interface RegisterDetailComponent {
    fun inject(fragment: RegisterDetailFragment)
}