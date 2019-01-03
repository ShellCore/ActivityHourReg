package com.shell.android.registropraxis.ui.registerdetail.di

import com.shell.android.registropraxis.libs.di.LibsModule
import com.shell.android.registropraxis.rest.di.RestModule
import com.shell.android.registropraxis.ui.registerdetail.ui.RegisterDetailFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [LibsModule::class, RestModule::class, RegisterDetailModule::class])
interface RegisterDetailComponent {
    fun inject(fragment: RegisterDetailFragment)
}