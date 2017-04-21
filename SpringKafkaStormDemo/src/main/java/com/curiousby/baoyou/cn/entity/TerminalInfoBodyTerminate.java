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

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.curiousby.baoyou.cn.utils.JSONUtilities;

/**
 * @Type TerminalInfoBodyTerminate.java
 * @Desc 
 * @author cmcc-B100036
 * @date 2016年12月16日 下午2:39:36
 * @version 
 */
public class TerminalInfoBodyTerminate implements EntityFormat<TerminalInfoBodyTerminate>{

    
//    \"6\": \"20161216110457499_02: 00: 00: 00: 00: 00_108999000003\",
//    \"9\": \"20161216110603885\",
//    \"1\": \"23081\",
//    \"4\": [
//        [
//            \"com.push.aoe.appDemo2.AOEDemoActivity2\",
//            23081
//        ]
//    ]
    
    public String duration;                                    // "1","duration" 
    public List<TerminalInfoBodyTerminateActivity> activities; // "4","activities" 
    public String sessionId;                                   // "6","session_id" 
    public String time;                                        // "9","time" 
     
    public TerminalInfoBodyTerminate formate(JSONObject localJson) { 
        try {
            this.duration = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_DURATION.getKey()));
            this.sessionId = JSONUtilities.getSecureString(localJson.getString(JSONUtilities.TKN_SESSION_ID.getKey())); 
            this.time = JSONUtilities.getSecureString(localJson.getString(JSONUtilities.TKN_TIME.getKey())); 
            
            this.activities = new ArrayList<TerminalInfoBodyTerminateActivity>();
            JSONArray jsonArray = localJson.getJSONArray(JSONUtilities.TKN_ACTIVITIES.getKey());
            for (int i = 0; i < jsonArray.length(); i++) { 
               this.activities.add(new TerminalInfoBodyTerminateActivity().formate((JSONArray) jsonArray.get(i)));
           } 
        } catch (Exception e) {
            return null;
        }
        return this;
    }
    
     
    
    @Override
    public String toString() {
        return "TerminalInfoBodyTerminate [duration=" + duration + ", activities=" + activities + ", sessionId=" + sessionId + ", time=" + time + "]";
    }



    public String getDuration() {
        return duration;
    }
    public List<TerminalInfoBodyTerminateActivity> getActivities() {
        return activities;
    }
    public String getSessionId() {
        return sessionId;
    }
    public String getTime() {
        return time;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public void setActivities(List<TerminalInfoBodyTerminateActivity> activities) {
        this.activities = activities;
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