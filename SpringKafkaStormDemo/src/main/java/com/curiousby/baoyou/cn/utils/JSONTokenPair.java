package com.curiousby.baoyou.cn.utils;

/** 
 * 
 * @author baoyou E-mail:curiousby@163.com
 * @version 2016年12月16日 下午3:24:24
 * desc:
 */
public class JSONTokenPair {
    
    private String key = "";
    private String token = "";
    
    public JSONTokenPair(String paramKey, String paramToken) {
        if (null != paramKey) {
            this.key = paramKey;
        }
        if (null != paramToken) {
            this.token = paramToken;
        }
    }

  
    public String getKey() {
        return key;
    }
 
    public String getToken() {
        return token;
    }

   
}
