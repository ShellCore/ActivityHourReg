package com.shell.android.registropraxis.libs.base

interface EventBus {

    fun register(subscriber: Any)
    fun unregister(subscriber: Any)
    fun post(event: Any)
}