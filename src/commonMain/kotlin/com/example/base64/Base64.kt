package com.example.base64

import kotlin.js.JsName

interface Base64Encoder {
    @JsName("encode")
    fun encode(src: ByteArray): ByteArray

    @JsName("encodeToString")
    fun encodeToString(src: ByteArray): String {
        return encode(src).let { encoded ->
            buildString(encoded.size) {
                encoded.forEach { append(it.toInt().toChar()) }
            }
        }
    }
}

expect object Base64Factory {
    fun createEncoder(): Base64Encoder
}