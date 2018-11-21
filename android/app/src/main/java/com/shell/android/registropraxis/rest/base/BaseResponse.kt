package com.shell.android.registropraxis.rest.base

open class BaseResponse {

    var result: Int = 0
    var message: String = ""

    companion object {

        val OK = 200
        val BAD_REQUEST = 400
        val SERVER_NOT_FOUND = 500
    }
}