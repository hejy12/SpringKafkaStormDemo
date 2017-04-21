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

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @Type EntityFormat.java
 * @Desc 
 * @author cmcc-B100036
 * @date 2016年12月16日 下午3:47:49
 * @version 
 */
public interface EntityFormat<T> {

      T formate(JSONObject json);  
}


/**
 * Revision history
 * -------------------------------------------------------------------------
 * 
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2016年12月16日 cmcc-B100036 creat
 */