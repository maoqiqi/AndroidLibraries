package com.codearms.maoqiqi.encrypt;

import com.codearms.maoqiqi.utils.ConvertUtils;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class MD5UtilsJavaTest {

    @Test
    public void testMd5() throws NoSuchAlgorithmException, IOException {
        String str1 = null;
        String str2 = "proguard-rules.pro";

        System.out.println(ConvertUtils.toHexString(MD5Utils.bytesToMd5(null)));
        System.out.println(ConvertUtils.toHexString(MD5Utils.strToMd5(null)));
        System.out.println(ConvertUtils.toHexString(MD5Utils.filePathToMd5(null)));
        System.out.println(ConvertUtils.toHexString(MD5Utils.fileToMd5(null)));

        System.out.println("------------------------------");

        System.out.println(str2);
        System.out.println(ConvertUtils.toHexString(MD5Utils.bytesToMd5(str2.getBytes())));
        System.out.println(ConvertUtils.toHexString(MD5Utils.strToMd5(str2)));
        System.out.println(ConvertUtils.toHexString(MD5Utils.filePathToMd5(str2)));
        System.out.println(ConvertUtils.toHexString(MD5Utils.fileToMd5(new File(str2))));
    }
}
