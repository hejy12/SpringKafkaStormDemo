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
import java.util.UUID;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.topology.base.BaseRichSpout;
import storm.kafka.BrokerHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.StringScheme;
import storm.kafka.ZkHosts;

/**
 * @see com.curiousby.baoyou.cn.storm.TerminalInfosAnalysisSpout
 * @Type TerminalInfosAnalysisTopology.java
 * @Desc 
 * @author cmcc-B100036
 * @date 2016年12月15日 下午4:54:50
 * @version 
 */
public class TerminalInfosAnalysisSpout  extends BaseRichSpout {

    
    
    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) { 
        
    }

    @Override
    public void nextTuple() { 
        
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
