package com.example.base64

@JsExport
actual object Base64Factory {
    actual fun createEncoder(): Base64Encoder = JsBase64Encoder
}

@JsExport
object JsBase64Encoder : Base64Encoder {
    override fun encode(src: ByteArray): ByteArray {
        val buffer = js("Buffer").from(src)
        val string = buffer.toString("base64") as String
        return ByteArray(string.length) { string[it].code.toByte() }
    }
}
