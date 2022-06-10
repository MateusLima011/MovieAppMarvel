package com.mgt.movieappmarvel.utils

import android.accounts.NetworkErrorException
import android.content.Context
import com.mgt.movieappmarvel.R
import java.lang.Exception

class NetworkException() : NetworkErrorException(){
    fun getLocalizedMessage(context: Context): String{
        return context.getString(R.string.internetError)
    }
}

class GeneralException() : Exception(){
    fun getLocalizedMessage(context: Context): String{
        return context.getString(R.string.genericError)
    }
}