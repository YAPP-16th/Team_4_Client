package kr.yapp.teamplay.presentation.util

import java.security.MessageDigest

class HashingPassword {
    fun hashString(input: String, algorithm: String): String {
        return MessageDigest
            .getInstance(algorithm)
            .digest(input.toByteArray())
            .fold("", { str, it -> str + "%02x".format(it) })
    }
}

fun String.md5(): String {
    return HashingPassword().hashString(this, "MD5")
}

fun String.sha256(): String {
    return HashingPassword().hashString(this, "SHA-256")
}
