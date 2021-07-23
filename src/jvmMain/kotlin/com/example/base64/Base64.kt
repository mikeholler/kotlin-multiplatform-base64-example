package com.example.base64

import java.util.Base64

actual object Base64Factory {
    actual fun createEncoder(): Base64Encoder = JvmBase64Encoder
}

object JvmBase64Encoder : Base64Encoder {
    override fun encode(src: ByteArray): ByteArray {
        return Base64.getEncoder().encode(src)
    }

    override fun encodeToString(src: ByteArray): String {
        return Base64.getEncoder().encodeToString(src)
    }
}
