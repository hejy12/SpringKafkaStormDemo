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
 * @Type TerminalInfoBodyEvent.java
 * @Desc 
 * @author cmcc-B100036
 * @date 2016年12月16日 下午2:39:41
 * @version 
 */
public class TerminalInfoBodyEvent implements EntityFormat<TerminalInfoBodyEvent> {
    //    \"6\": \"20161216110457499_02: 00: 00: 00: 00: 00_108999000003\",
    //    \"9\": \"20161216110520574\",
    //    \"16\": \"\xe6\xb6\x88\xe6\x81\xaf\xe4\xb8\xad\xe5\xbf\x83\xe6\x8c\x89\xe9\x92\xae\",
    //    \"50\": \"\xe6\xb6\x88\xe6\x81\xaf\xe4\xb8\xad\xe5\xbf\x83\xe6\x8c\x89\xe9\x92\xae\",
    //    \"17\": \"\xe6\xb6\x88\xe6\x81\xaf\xe4\xb8\xad\xe5\xbf\x83\xe6\x8c\x89\xe9\x92\xae\",
    //    \"18\": 1

    public String sessionId; //"6","session_id" 
    public String time; //"9","time" 
    public String tag; //"16","tag" 
    public String label; //"17","label" 
    public String acc; //"18","acc"  
    public String attribute; // "50", "attribute"
 
    public TerminalInfoBodyEvent formate(JSONObject localJson) {
        try {
            this.sessionId = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_SESSION_ID.getKey()));
            this.time = JSONUtilities.getSecureString(localJson.getString(JSONUtilities.TKN_TIME.getKey()));
            this.tag = JSONUtilities.getSecureString(localJson.getString(JSONUtilities.TKN_TAG.getKey()));
            this.label = JSONUtilities.getSecureString(localJson.getString(JSONUtilities.TKN_LABEL.getKey()));
            this.acc = JSONUtilities.getSecureString( String.valueOf(localJson.getInt(JSONUtilities.TKN_ACC.getKey())));
            this.attribute = JSONUtilities.getSecureString(localJson.getString(JSONUtilities.TKN_ATTRIBUTE.getKey()));
        } catch (Exception e) {
            return null;
        }
        return this;
    }

    
    
    @Override
    public String toString() {
        return "TerminalInfoBodyEvent [sessionId=" + sessionId + ", time=" + time + ", tag=" + tag
                + ", label=" + label + ", acc=" + acc + ", attribute=" + attribute + "]";
    }



    public String getSessionId() {
        return sessionId;
    }

    public String getTime() {
        return time;
    }

    public String getTag() {
        return tag;
    }

    public String getLabel() {
        return label;
    }

    public String getAcc() {
        return acc;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
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
