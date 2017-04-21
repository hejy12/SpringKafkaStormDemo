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
 * @Type TerminalInfos.java
 * @Desc 
 * @author cmcc-B100036
 * @date 2016年12月16日 上午11:28:58
 * @version 
 */
public class TerminalInfos implements EntityFormat<TerminalInfos>{

     public  TerminalInfoHeader terminalInfoHeader;  //"19", "header"
     public TerminalInfoBody terminalInfoBody;       //"21", "body"
    
     
     
    public TerminalInfoHeader getTerminalInfoHeader() {
        return terminalInfoHeader;
    }
    public TerminalInfoBody getTerminalInfoBody() {
        return terminalInfoBody;
    }
    public void setTerminalInfoHeader(TerminalInfoHeader terminalInfoHeader) {
        this.terminalInfoHeader = terminalInfoHeader;
    }
    public void setTerminalInfoBody(TerminalInfoBody terminalInfoBody) {
        this.terminalInfoBody = terminalInfoBody;
    }
     
    
    @Override
    public String toString() {
        return "TerminalInfos [terminalInfoHeader=" + terminalInfoHeader + ", terminalInfoBody="
                + terminalInfoBody + "]";
    } 
     
    
    public static JSONObject formate(String json){ 
        JSONObject localJson = new JSONObject(json); 
        return  localJson;
    }
    
     
    public TerminalInfos formate(JSONObject localJson) {
        
        JSONObject localHeader = localJson.getJSONObject(JSONUtilities.TKN_HEADER.getKey());
        JSONObject localBody = localJson.getJSONObject(JSONUtilities.TKN_BODY.getKey());
        
        if (localHeader == null || localBody == null) {
            return null;
        }
        
        this.setTerminalInfoHeader( new TerminalInfoHeader( ).formate(localHeader) );
        this.setTerminalInfoBody(new TerminalInfoBody( ).formate(localBody) );
        
        return  this;
    }
    
    public   boolean isValid(){
        if(this.getTerminalInfoHeader() != null && this.getTerminalInfoBody() != null )
            if (this.getTerminalInfoHeader().getAppId() != null ) {
                return true;
            }
        return false;
    }
    
    
    public static void main(String[] args) {
        String str =  "{\"19\":{\"24\":\"02:00:00:00:00:00\",\"25\":\"863360028147399\",\"56\":\"0c3631303038393039393030303030303030333030\",\"26\":\"460010430511343\",\"27\":\"MI 3W\",\"28\":\"Xiaomi\",\"52\":\"MMSHANGCHENG\",\"5\":\"108999000003-Android\",\"51\":\"unknown\",\"29\":\"2.0\",\"31\":2,\"32\":\"Android\",\"33\":23,\"34\":\"Android\",\"35\":\"6.0.1\",\"36\":\"unknown\",\"37\":\"unknown\",\"38\":8,\"39\":\"1920*1080\",\"41\":\"Wi-Fi\",\"42\":\"unknown\",\"43\":\"unknown\",\"46\":\"ARMv7 Processor rev 1 (v7l)\",\"55\":\"\"},\"21\":{\"15\":[{\"6\":\"20161216110457499_02:00:00:00:00:00_108999000003\",\"9\":\"20161216110520574\",\"16\":\"unknown\",\"50\":\"unknown\",\"17\":\"unknown\",\"18\":1}],\"11\":[{\"6\":\"20161216110457499_02:00:00:00:00:00_108999000003\",\"9\":\"20161216110603885\",\"1\":\"23081\",\"4\":[[\"com.push.aoe.appDemo2.AOEDemoActivity2\",23081]]}],\"8\":[{\"6\":\"20161216110603887_02:00:00:00:00:00_108999000003\",\"9\":\"20161216110603903\"}]}}";
        JSONObject formate = TerminalInfos.formate(str);
        
        TerminalInfos entity = new TerminalInfos();
        entity.formate(formate);
        System.out.println(entity);
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