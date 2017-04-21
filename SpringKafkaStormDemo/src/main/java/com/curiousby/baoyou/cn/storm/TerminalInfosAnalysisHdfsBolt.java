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
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @see com.curiousby.baoyou.cn.storm.TerminalInfosAnalysisHdfsBolt
 * @Type TerminalInfosAnalysisTopology.java
 * @Desc 
 * @author cmcc-B100036
 * @date 2016年12月15日 下午3:24:42
 * @version 
 */
public class TerminalInfosAnalysisHdfsBolt extends BaseRichBolt {

    private Logger logger =LoggerFactory.getLogger(TerminalInfosAnalysisHdfsBolt.class);
    private OutputCollector collector; 
    HDFSUtils hdfs = null; 
    static String hdfsRoot ="/user/hadoop/storm";
    protected transient Object writeLock;
    
    @Override
    public void execute(Tuple tuple) { 
        synchronized (this.writeLock) {
            
            
            this.hdfs = new HDFSUtils();
            this.hdfs.init();
            logger.info("============================hdfs execute===============================");
            for (int i = 0; i < tuple.size(); i++) {
                TerminalInfos entity = (TerminalInfos) tuple.getValue(i); 
                TerminalInfoHeader tih = entity.getTerminalInfoHeader(); 
                
                try {
                    String terminalPath = hdfsRoot+"/"+"terminal_"+tih.getAppId();
                    boolean existsTerminalPath = this.hdfs.exists(terminalPath);
                    if (!existsTerminalPath) {
                        this.hdfs.createDir(terminalPath);
                    }
                    String terminalTodayPath = terminalPath + "/temialinfo."+DatetimeUtil.getYMD(new Date())+".log";
                    boolean existsTerminalTodayPath = this.hdfs.exists(terminalTodayPath);
                    if (!existsTerminalTodayPath) {
                        this.hdfs.create(terminalTodayPath);
                    }
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
                    
                    this.hdfs.writeAppendFile(terminalTodayPath, sb.toString());
                    
                    logger.info("============================hdfs write end===============================");
                } catch (IOException e) { 
                    e.printStackTrace();
                }
                
            }
            this.hdfs.close();
        }
       
    }

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) { 
        this.collector = collector; 
        if (OsUtil.isWindows()) {
            System.setProperty("hadoop.home.dir", "I:\\software\\hadoop-2.6.0");
        }
        this.writeLock = new Object();
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
