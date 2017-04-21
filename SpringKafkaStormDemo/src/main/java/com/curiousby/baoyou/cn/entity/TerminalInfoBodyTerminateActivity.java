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
 
/**
 * @Type TerminalInfoBodyTerminateActivity.java
 * @Desc 
 * @author cmcc-B100036
 * @date 2016年12月16日 下午2:48:52
 * @version 
 */
public class TerminalInfoBodyTerminateActivity {

    //   \"com.push.aoe.appDemo2.AOEDemoActivity2\", 23081
    public List<String> terminalInfoBodyTerminateActivity;

    public TerminalInfoBodyTerminateActivity formate(JSONArray localJsonArr) {
        try {
            this.terminalInfoBodyTerminateActivity = new ArrayList<String>();
            this.terminalInfoBodyTerminateActivity.add(localJsonArr.getString(0));
            this.terminalInfoBodyTerminateActivity.add(String.valueOf(localJsonArr.getLong(1)));
            return this;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < terminalInfoBodyTerminateActivity.size(); i++) {
            sb.append(terminalInfoBodyTerminateActivity.get(i));
        }
        return sb.toString();
    }
    
    public List<String> getTerminalInfoBodyTerminateActivity() {
        return terminalInfoBodyTerminateActivity;
    }

    public void setTerminalInfoBodyTerminateActivity(List<String> terminalInfoBodyTerminateActivity) {
        this.terminalInfoBodyTerminateActivity = terminalInfoBodyTerminateActivity;
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
