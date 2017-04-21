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

import java.util.UUID;

import org.apache.storm.hdfs.bolt.HdfsBolt;
 
import org.apache.storm.hdfs.bolt.format.DefaultFileNameFormat;
import org.apache.storm.hdfs.bolt.format.DelimitedRecordFormat;
import org.apache.storm.hdfs.bolt.format.FileNameFormat;
import org.apache.storm.hdfs.bolt.format.RecordFormat;
import org.apache.storm.hdfs.bolt.rotation.FileRotationPolicy;
import org.apache.storm.hdfs.bolt.rotation.TimedRotationPolicy;
import org.apache.storm.hdfs.bolt.rotation.TimedRotationPolicy.TimeUnit;
import org.apache.storm.hdfs.bolt.sync.CountSyncPolicy;
import org.apache.storm.hdfs.bolt.sync.SyncPolicy;

import storm.kafka.BrokerHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.StringScheme;
import storm.kafka.ZkHosts;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields; 

/**
 * @see com.curiousby.baoyou.cn.storm.TerminalInfosAnalysisTopology
 * @Type TerminalInfosAnalysisTopology.java
 * @Desc 
 * @author cmcc-B100036
 * @date 2016年12月15日 下午4:54:50
 * @version 
 */
public class TerminalInfosAnalysisTopology {

    private static String topicName = "baoy-topic";
    private static String zkRoot = "/kafka" ;

    public static void main(String[] args) {
        BrokerHosts hosts = new ZkHosts(
                "172.23.27.120:2181,172.23.27.115:2181,172.23.27.116:2181/kafka");
        SpoutConfig spoutConfig = new SpoutConfig(hosts, topicName, zkRoot, UUID.randomUUID().toString());
        spoutConfig.forceFromStart= false;
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        //spoutConfig.socketTimeoutMs=60; 
        KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);
 
       RecordFormat format = new DelimitedRecordFormat().withFieldDelimiter("\t"); 
        SyncPolicy syncPolicy = new CountSyncPolicy(2); 
        FileRotationPolicy rotationPolicy = new TimedRotationPolicy(1.0f, TimeUnit.HOURS);  
        FileNameFormat fileNameFormat = new DefaultFileNameFormat().withPath("/user/hadoop/storm/").withPrefix("terminalInfo_").withExtension(".log");  
         HdfsBolt hdfsBolt = new HdfsBolt()
                 .withFsUrl("hdfs://172.23.27.120:9000/")
                 .withFileNameFormat(fileNameFormat).withRecordFormat(format)
                 .withRotationPolicy(rotationPolicy).withSyncPolicy(syncPolicy);

         
        
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("kafkaSpout", kafkaSpout);
        builder.setBolt("terminalInfosAnalysisIsValidBolt", new TerminalInfosAnalysisIsValidBolt(),1).shuffleGrouping("kafkaSpout");  
        builder.setBolt("terminalInfosAnalysisRedisBolt", new TerminalInfosAnalysisRedisBolt(),1).shuffleGrouping("terminalInfosAnalysisIsValidBolt");  
        builder.setBolt("terminalInfosAnalysisHdfsReportBolt", new TerminalInfosAnalysisHdfsReportBolt(),1).shuffleGrouping("terminalInfosAnalysisIsValidBolt");  
        builder.setBolt("terminalInfo", hdfsBolt,1).fieldsGrouping("terminalInfosAnalysisHdfsReportBolt",new Fields("hdfs-terminalinfo"));  
        // builder.setBolt("terminalInfosAnalysisHdfsBolt", new TerminalInfosAnalysisHdfsBolt(),1).shuffleGrouping("terminalInfosAnalysisIsValidBolt");  
        
         
        Config conf = new Config();
        conf.setDebug(true);

        if (args != null && args.length > 0) {
            conf.setNumWorkers(1);
            try {
                StormSubmitter.submitTopologyWithProgressBar(args[0], conf,  builder.createTopology());
            } catch (AlreadyAliveException e) {
                e.printStackTrace();
            } catch (InvalidTopologyException e) {
                e.printStackTrace();
            }
        } else { 
            conf.setMaxSpoutPending(1);
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("terminalInfosAnalysisTopology", conf, builder.createTopology());

        }

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
