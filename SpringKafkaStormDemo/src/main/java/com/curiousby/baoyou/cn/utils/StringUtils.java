/*
 * Project: SpringKafkaStormDemo
 * 
 * File Created at 2016年12月21日
 * 
 * Copyright 2016 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package com.curiousby.baoyou.cn.utils;

/**
 * @Type StringUtils.java
 * @Desc 
 * @author cmcc-B100036
 * @date 2016年12月21日 下午4:41:21
 * @version 
 */
public class StringUtils {

    final static String splite =",";
    
    public static String[] split(final String toSplit, final String delimiter){
        if (!hasLength(toSplit) || !hasLength(delimiter)) {
            return null;
        }
        return toSplit.split(delimiter);
    }
    
    public static boolean hasLength(final String str) {
        return (str != null && str.length() > 0);
    }
    
    
    
    public static void main(String[] args) {
        System.out.println(split("baoyou",",")[0]);
        System.out.println(split("baoyou","o")[0]);
    }
    
}


/**
 * Revision history
 * -------------------------------------------------------------------------
 * 
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2016年12月21日 cmcc-B100036 creat
 */