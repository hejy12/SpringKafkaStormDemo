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

import java.io.IOException;
import java.util.Date;
import java.util.Map;
 
import org.json.JSONObject; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.curiousby.baoyou.cn.entity.TerminalInfoHeader;
import com.curiousby.baoyou.cn.entity.TerminalInfos;
import com.curiousby.baoyou.cn.hadoop.HDFSUtils;
import com.curiousby.baoyou.cn.utils.DatetimeUtil;
import com.curiousby.baoyou.cn.utils.OsUtil;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @see com.curiousby.baoyou.cn.storm.TerminalInfosAnalysisHdfsReportBolt
 * @Type TerminalInfosAnalysisTopology.java
 * @Desc 
 * @author cmcc-B100036
 * @date 2016年12月15日 下午3:24:42
 * @version 
 */
public class TerminalInfosAnalysisHdfsReportBolt extends BaseRichBolt {

    private Logger logger =LoggerFactory.getLogger(TerminalInfosAnalysisHdfsReportBolt.class);
    private OutputCollector collector;  
    
    
    
    @Override
    public void execute(Tuple tuple) {  
        logger.info("============================TerminalInfosAnalysisHdfsReportBolt execute===============================");
       for (int i = 0; i < tuple.size(); i++) {
           TerminalInfos entity = (TerminalInfos) tuple.getValue(i); 
           TerminalInfoHeader tih = entity.getTerminalInfoHeader();
           StringBuffer sb = new StringBuffer();
           sb.append(tih.getAppId()).append(",");
           sb.append(tih.getDeviceMac()).append(",");
           sb.append(tih.getDeviceId()).append(",");
           sb.append(tih.getDeviceToken()).append(",");
           sb.append(tih.getDeviceImsi()).append(",");
           sb.append(tih.getDeviceModel()).append(",");
           sb.append(tih.getDeviceManufacture()).append(",");
           sb.append(tih.getChannel()).append(",");
           sb.append(tih.getAppKey()).append(",");
           sb.append(tih.getUserId()).append(",");
           sb.append(tih.getAppVersion()).append(",");
           sb.append(tih.getVersionCode()).append(","); 
           sb.append(tih.getSdkType()).append(",");
           sb.append(tih.getOs()).append(",");
           sb.append(tih.getCountry()).append(",");
           sb.append(tih.getLanguage()).append(",");
           sb.append(tih.getTimezone()).append(",");
           sb.append(tih.getResolution()).append(",");
           sb.append(tih.getAccess()).append(",");
           sb.append(tih.getAccessSubtype()).append(",");
           sb.append(tih.getCarrier()).append(",");
           sb.append(tih.getCpu());
           collector.emit(tuple, new Values("hdfs-terminalinfo",sb.toString()));
           collector.ack(tuple); 
           
       }
    }

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) { 
        this.collector = collector; 
      
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("hdfs-terminalinfo", "record"));
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
