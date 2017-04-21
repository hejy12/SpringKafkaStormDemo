package com.curiousby.baoyou.cn.utils;

/** 
 * 
 * @author baoyou E-mail:curiousby@163.com
 * @version 2016年9月27日 下午4:01:17
 * desc:
 */
public class OsUtil {
    /** 
     * 
     * @return
     */
    public static boolean isLinux() {
        return !isWindows();
    }

    /** 
     * 
     * @return
     */
    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }
}
