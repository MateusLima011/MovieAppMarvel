package com.mgt.data

import okhttp3.internal.and
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

const val publicKey = "39b9342d5feb15f50d704fb4939b88ba"
private const val privateKey = "8d15298f9597a6f331024f1049c62ad611f68890"
private var timeStamp: String? = null

fun getHash(): String {
    return try {
        val timeStamp = getTimeStamp()
        val value: String = timeStamp + privateKey + publicKey
        val md5Encoder: MessageDigest = MessageDigest.getInstance("MD5")
        val md5Bytes: ByteArray = md5Encoder.digest(value.toByteArray())
        val md5 = StringBuilder()
        for (i in md5Bytes.indices) {
            md5.append(Integer.toHexString(md5Bytes[i] and 0xFF or 0x100).substring(1, 3))
        }
        md5.toString()
    } catch (e: NoSuchAlgorithmException) {
        throw Throwable("cannot generate the api key", e)
    }
}

fun getTimeStamp(): String {
    if (timeStamp == null) {
        timeStamp = System.currentTimeMillis().toString()
    }
    return timeStamp as String
}
