package com.curiousby.baoyou.cn.entity;
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


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.curiousby.baoyou.cn.utils.JSONUtilities;

/**
 * @Type TerminalInfoBody.java
 * @Desc 
 * @author cmcc-B100036
 * @date 2016年12月16日 上午11:30:20
 * @version 
 */
public class TerminalInfoBody  implements EntityFormat<TerminalInfoBody>{

    
    public List<TerminalInfoBodyLaunch> terminalInfoBodyLaunchs;          //"8", "launch"
    public List<TerminalInfoBodyTerminate> terminalInfoBodyTerminates;    // "11", "terminate"
    public List<TerminalInfoBodyEvent> terminalInfoBodyEvents;            //"15", "event"
   
     
    public TerminalInfoBody formate(JSONObject localJson) {
        try { 
            this.terminalInfoBodyLaunchs = new ArrayList<TerminalInfoBodyLaunch>();
            JSONArray jsonArray1 = localJson.getJSONArray(JSONUtilities.TKN_LAUNCH.getKey());
            for (int i = 0; i < jsonArray1.length(); i++) { 
               this.terminalInfoBodyLaunchs.add(new TerminalInfoBodyLaunch().formate( (JSONObject)jsonArray1.get(i)));
           } 
            
            this.terminalInfoBodyTerminates = new ArrayList<TerminalInfoBodyTerminate>();
            JSONArray jsonArray2 = localJson.getJSONArray(JSONUtilities.TKN_TERMINATE.getKey());
            for (int i = 0; i < jsonArray2.length(); i++) { 
               this.terminalInfoBodyTerminates.add(new TerminalInfoBodyTerminate().formate( (JSONObject)jsonArray2.get(i)));
           } 
            
            
            this.terminalInfoBodyEvents = new ArrayList<TerminalInfoBodyEvent>();
            JSONArray jsonArray3 = localJson.getJSONArray(JSONUtilities.TKN_EVENT.getKey());
            for (int i = 0; i < jsonArray3.length(); i++) { 
               this.terminalInfoBodyEvents.add(new TerminalInfoBodyEvent().formate( (JSONObject)jsonArray3.get(i)));
           } 
            
            
        } catch (Exception e) {
            return null;
        }
        return this;
    }
    
    
    
    @Override
    public String toString() {
        return "TerminalInfoBody [terminalInfoBodyLaunchs=" + terminalInfoBodyLaunchs
                + ", terminalInfoBodyTerminates=" + terminalInfoBodyTerminates
                + ", terminalInfoBodyEvents=" + terminalInfoBodyEvents + "]";
    }



    public List<TerminalInfoBodyLaunch> getTerminalInfoBodyLaunchs() {
        return terminalInfoBodyLaunchs;
    }
    public List<TerminalInfoBodyTerminate> getTerminalInfoBodyTerminates() {
        return terminalInfoBodyTerminates;
    }
    public List<TerminalInfoBodyEvent> getTerminalInfoBodyEvents() {
        return terminalInfoBodyEvents;
    }
    public void setTerminalInfoBodyLaunchs(List<TerminalInfoBodyLaunch> terminalInfoBodyLaunchs) {
        this.terminalInfoBodyLaunchs = terminalInfoBodyLaunchs;
    }
    public void setTerminalInfoBodyTerminates(List<TerminalInfoBodyTerminate> terminalInfoBodyTerminates) {
        this.terminalInfoBodyTerminates = terminalInfoBodyTerminates;
    }
    public void setTerminalInfoBodyEvents(List<TerminalInfoBodyEvent> terminalInfoBodyEvents) {
        this.terminalInfoBodyEvents = terminalInfoBodyEvents;
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