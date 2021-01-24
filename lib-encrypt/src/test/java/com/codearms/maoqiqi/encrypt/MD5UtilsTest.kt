package com.codearms.maoqiqi.encrypt

import com.codearms.maoqiqi.encrypt.MD5Utils.bytesToMd5
import com.codearms.maoqiqi.encrypt.MD5Utils.filePathToMd5
import com.codearms.maoqiqi.encrypt.MD5Utils.fileToMd5
import com.codearms.maoqiqi.encrypt.MD5Utils.strToMd5
import com.codearms.maoqiqi.utils.ConvertUtils.toHexString
import org.junit.Test
import java.io.File

class MD5UtilsTest {

    @Test
    fun testMd5() {
        val str2 = "proguard-rules.pro"

        println(null.bytesToMd5())
        println(null.strToMd5())
        println(null.filePathToMd5())
        println(null.fileToMd5())

        println("------------------------------")

        println(str2)
        println(str2.toByteArray().bytesToMd5().toHexString())
        println(str2.strToMd5().toHexString())
        println(str2.filePathToMd5().toHexString())
        println(File(str2).fileToMd5().toHexString())
    }
}