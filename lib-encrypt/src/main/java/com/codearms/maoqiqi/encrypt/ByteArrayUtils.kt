package com.codearms.maoqiqi.encrypt

object ByteArrayUtils {

    fun ByteArray?.isNullOrEmpty(): Boolean = this == null || this.isEmpty()

    fun ByteArray?.joins(data: ByteArray?): ByteArray? {
        if (this == null) return data
        if (data == null) return this
        val ret = ByteArray(size + data.size)
        System.arraycopy(this, 0, ret, 0, size)
        System.arraycopy(data, 0, ret, size, data.size)
        return ret
    }
}