package com.sxt.sys.utils;

import java.util.UUID;

public class RandomUtils {

    /**
     * 生成随机UUID
     */
    public static String createRandomStringUserUUID(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }
}
