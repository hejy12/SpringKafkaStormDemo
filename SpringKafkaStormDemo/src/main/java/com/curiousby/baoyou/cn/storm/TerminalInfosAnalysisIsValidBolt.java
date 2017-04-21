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

import com.curiousby.baoyou.cn.entity.TerminalInfos;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

/**
 * @see com.curiousby.baoyou.cn.storm.TerminalInfosAnalysisIsValidBolt
 * @Type TerminalInfosAnalysisTopology.java
 * @Desc 
 * @author cmcc-B100036
 * @date 2016年12月15日 下午3:24:42
 * @version 
 */
public class TerminalInfosAnalysisIsValidBolt extends BaseRichBolt {

    private Logger logger =LoggerFactory.getLogger(TerminalInfosAnalysisIsValidBolt.class);
    private OutputCollector collector;
    
    @Override
    public void execute(Tuple tuple) {

        System.out.println(tuple.size());
        logger.info("============================TerminalInfosAnalysisIsValidBolt execute===============================");
        for (int i = 0; i < tuple.size(); i++) {
            JSONObject formate = TerminalInfos.formate(tuple.getString(i));
            TerminalInfos entity = new TerminalInfos();
            entity.formate(formate);
            if (entity != null && entity.isValid()) {
                System.out.println(entity);
                collector.emit(tuple, new Values(entity));
                collector.ack(tuple); 
            }
        }
    }

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        
        
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("after_isvalid"));  
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
