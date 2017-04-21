/*
 * Project: SpringKafkaStormDemo
 * 
 * File Created at 2016年12月16日
 * 
 * Copyright 2016 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package com.curiousby.baoyou.cn.entity;

import org.json.JSONObject;

import com.curiousby.baoyou.cn.utils.JSONUtilities;

/**
 * @Type TerminalInfoBodyLaunch.java
 * @Desc 
 * @author cmcc-B100036
 * @date 2016年12月16日 下午2:39:30
 * @version 
 */
public class TerminalInfoBodyLaunch  implements EntityFormat<TerminalInfoBodyLaunch>{
    
//    \"6\": \"20161216110603887_02: 00: 00: 00: 00: 00_108999000003\",
//    \"9\": \"20161216110603903\"
    
    public String sessionId; //"6","session_id"
    public String time; //"9","time"
    
    public TerminalInfoBodyLaunch formate(JSONObject localJson) {
        try {
            this.sessionId = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_SESSION_ID.getKey()));
            this.time = JSONUtilities.getSecureString(localJson.getString(JSONUtilities.TKN_TIME.getKey())); 
        } catch (Exception e) {
            return null;
        }
        return this;
    }
    
    
    @Override
    public String toString() {
        return "TerminalInfoBodyLaunch [sessionId=" + sessionId + ", time=" + time + "]";
    }


    public String getSessionId() {
        return sessionId;
    }
    public String getTime() {
        return time;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    public void setTime(String time) {
        this.time = time;
    }
  
     
    
}


/**
 * Revision history
 * -------------------------------------------------------------------------
 * 
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2016年12月16日 cmcc-B100036 creat
 */