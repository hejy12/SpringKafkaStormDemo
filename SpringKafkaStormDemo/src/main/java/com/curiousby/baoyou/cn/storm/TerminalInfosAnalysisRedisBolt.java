/*
 * Project: SpringKafkaStormDemo
 * 
 * File Created at 2016年12月15日
 * 
 * Copyright 2016 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package com.curiousby.baoyou.cn.storm;

import java.util.Map;
 
import org.json.JSONObject; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.curiousby.baoyou.cn.entity.TerminalInfoHeader;
import com.curiousby.baoyou.cn.entity.TerminalInfos;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @see com.curiousby.baoyou.cn.storm.TerminalInfosAnalysisRedisBolt
 * @Type TerminalInfosAnalysisTopology.java
 * @Desc 
 * @author cmcc-B100036
 * @date 2016年12月15日 下午3:24:42
 * @version 
 */
public class TerminalInfosAnalysisRedisBolt extends BaseRichBolt {

    private Logger logger =LoggerFactory.getLogger(TerminalInfosAnalysisRedisBolt.class);
    private OutputCollector collector;
    JedisPool pool; 
    
    
    @Override
    public void execute(Tuple tuple) {  
        Jedis jedis = pool.getResource(); 
        logger.info("============================TerminalInfosAnalysisRedisBolt execute===============================");
        for (int i = 0; i < tuple.size(); i++) {
            TerminalInfos entity = (TerminalInfos) tuple.getValue(i);
            TerminalInfoHeader tih = entity.getTerminalInfoHeader(); 
            String key = tih.getAppId()+"-"+tih.getDeviceToken();
            String value = jedis.get(key);
            if (value == null || "".equals(value)) {
                // 
                jedis.set( key,  JSON.toJSONString(tih));
                // insert es all infos
                
            }else{
                //update  es lastupdatetime 
            }
            
            
            
        }
         
    }

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        logger.info("============================redis prepare===============================");
        this.collector = collector;
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxActive(1000);
        config.setMaxIdle(50);
        config.setMaxWait(1000l);
        config.setTestOnBorrow(false);
        this.pool = new JedisPool(config, "172.23.27.120", 6379); 
        
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

}

/**
 * Revision history
 * -------------------------------------------------------------------------
 * 
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2016年12月15日 cmcc-B100036 creat
 */
