package com.shell.android.registropraxis.rest.base

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class BaseCallback<T> : Callback<T> {

    override fun onResponse(call: Call<T>, res: Response<T>) {
        if (res.isSuccessful) {
            val response = res.body() as BaseResponse
            if (response.result == BaseResponse.SERVER_NOT_FOUND) {
                onError(response.message)
            } else {
                onSuccess(res.body() as T)
            }
        } else {
            onError(res.message())
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        onError(t.localizedMessage)
    }

    abstract fun onSuccess(response: T)

    abstract fun onError(errorMessage: String)
}