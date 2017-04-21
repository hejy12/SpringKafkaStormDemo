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

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

/**
 * @see com.curiousby.baoyou.cn.storm.TerminalInfosAnalysisBolt
 * @Type TerminalInfosAnalysisTopology.java
 * @Desc 
 * @author cmcc-B100036
 * @date 2016年12月15日 下午3:24:42
 * @version 
 */
public class TerminalInfosAnalysisBolt extends BaseRichBolt {

    @Override
    public void execute(Tuple input) {

    }

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {

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
