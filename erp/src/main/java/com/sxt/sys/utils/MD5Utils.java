package com.sxt.sys.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 加密工具类
 */
public class MD5Utils {

    /**
     * 生成32位的md5码(密码加密)
     */
    public static String Md5Pssword(String source,Object salt){
        Md5Hash hash = new Md5Hash(source,salt,2);
        return hash.toString();
    }
}
