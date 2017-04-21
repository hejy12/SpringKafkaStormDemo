/*
 * Project: SpringKafkaStormDemo
 * 
 * File Created at 2016年12月26日
 * 
 * Copyright 2016 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package com.curiousby.baoyou.cn.hadoop;


import java.io.File;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;

import org.apache.hadoop.fs.FSDataOutputStream;

import org.apache.hadoop.fs.FileStatus;

import org.apache.hadoop.fs.FileSystem;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.JobConf;

import com.curiousby.baoyou.cn.utils.DatetimeUtil;

import ch.qos.logback.core.net.SyslogOutputStream;
/**
 * @see com.curiousby.baoyou.cn.hadoop.HDFSUtils
 * @Type HDFS.java
 * @Desc  HDFS(分布式文件存储系统操作类). 
 * @author cmcc-B100036
 * @date 2016年12月26日 下午5:18:39
 * @version 
 */ 
public class HDFSUtils {

    
    private static Configuration configuration; 
    private static final String HDFS_URL = "hdfs://172.23.27.120:9000/"; 
    private   FileSystem fileSystem;
  
    // 初始化 
    public void init() {  
        try {
            System.setProperty("hadoop.home.dir", "I:\\software\\hadoop-2.6.0");
            
            configuration = new JobConf(HDFSUtils.class);
            configuration.setBoolean( "dfs.support.append", true );
            configuration.set( "dfs.client.block.write.replace-datanode-on-failure.policy" , "NEVER" );
            configuration.set( "dfs.client.block.write.replace-datanode-on-failure.enable" , "true" );
         
            fileSystem = FileSystem.get(URI.create(HDFS_URL), configuration);
        } catch (IOException e) {
            System.out.println("读取服务器失败");
            e.printStackTrace();
        }
    }

    /**
     * 获取HDFS指定目录下文件状态列表
     * 
     * @param dirPath指定目录路径
     * @return fileStatusList
     * @throws FileNotFoundException
     * @throws IOException
     */
    public FileStatus[] getFileStatus(Path path) throws FileNotFoundException,  IOException { 
        FileStatus[] fileStatusList = fileSystem.listStatus(path); 
        return fileStatusList;
    }

    /**
     * 获取指定目录列表路径
     * @param dirPath
     */
    public List<String> dir(String dirPath) throws IOException {
        List<String> fileList = null;
        Path path = new Path(dirPath);
        if (fileSystem.exists(path)) {
            fileList = new ArrayList<String>();
            FileStatus[] list = this.getFileStatus(path);
            for (FileStatus fileStatus : list) {
                fileList.add(fileStatus.getPath().toString());
            }
        } else {
            System.out.println("目录不存在");
        }
        return fileList;
    }

    /**
     * 获取文件
     * @param filePath
     * @return
     * @throws IOException
     */
    public InputStream getFile(String filePath) throws IOException {
        Path path = new Path(filePath);
        return fileSystem.open(path);
    }

    /**
     * 更改HDSF文件名称
     * 
     * @param fileOldName
     * @param fileNewName
     * @return boolean:是否更名字成功
     * @throws IOException
     */
    public boolean rename(String src, String dst) throws IOException {
        Path srcPath = new Path(src);
        if (fileSystem.exists(srcPath)) {
            Path dstPath = new Path(dst);
            return fileSystem.rename(srcPath, dstPath);
        }
        System.out.println("原文件不存在");
        return false;
    }

    /**
     * 创建HDFS目录
     * @param dir
     */
    public boolean createDir(String dir) throws IOException {
        Path path = new Path(dir);
        if (fileSystem.exists(path)) {
            System.out.println("此目录已经存在不需要再创建");
            return true;
        }
        return fileSystem.mkdirs(path);
    }

    /**
     * 上传本地文件到HDFS（注意是服务器本地硬盘，非客户端硬盘)）
     * @return
     * @throws IOException
     */
    public void uploadLocalFile(String localFileSrc, String HDFSFileDst) throws IOException {
        Path src = new Path(localFileSrc);
        Path dst = new Path(HDFSFileDst);
        fileSystem.copyFromLocalFile(src, dst);
    }
    /**
     * 批量上传本地文件到HDFS
     * @param localFileSrcs本地文件列表
     * @param HDFSFileDst
     * @throws IOException
     */
    public void uploadLocalFile(String[] localFileSrcs, String HDFSFileDst) throws IOException {
        Path dstPath = new Path(HDFSFileDst);
        Path[] paths = new Path[localFileSrcs.length];
        for (int i = 0; i < localFileSrcs.length; i++) {
            paths[i] = new Path(localFileSrcs[i]);
        }
        fileSystem.copyFromLocalFile(false, false, paths, dstPath);
    }

    /**
     * 从HDFS下载文件到本地(注意是服务器本地硬盘，非浏览器客户端硬盘)
     * @param HDFSFilePath
     * @param localFilePath
     * @throws IOException
     */
    public void downFileToLocal(String HDFSFilePath, String localFilePath) throws IOException {
        Path dstPath = new Path(HDFSFilePath);
        FSDataInputStream in = fileSystem.open(dstPath);
        OutputStream out = new FileOutputStream(new File(localFilePath));
        IOUtils.copy(in, out);
    }

    /**
     * HDFS文件是否存在
     * @param filePath
     * @return boolean:是否存在
     * @throws IOException
     */
    public boolean exists(String filePath) throws IOException {
        Path path = new Path(filePath);
        return fileSystem.exists(path);
    }

    /**
     * 根据路径删除文件或文件夹
     * @param filePath
     * @return
     * @throws IOException
     */
    @SuppressWarnings("deprecation")
    public boolean deleteFile(String filePath) throws IOException {
        if (this.exists(filePath)) {
            Path path = new Path(filePath);
            fileSystem.delete(path);
            return true;
        }
        System.out.println("文件不存在");
        return false;
    }

    /**
     * 剪切本地文件到HDFS(注意为服务器本地文件);
     * @param src本地路径
     * @param dst分布式存储路径
     * @throws IOException
     */
    public void moveFromLocalFile(String localSrc, String HDFSDst) throws IOException {
        Path srcPath = new Path(localSrc);
        Path dstPath = new Path(HDFSDst);
        fileSystem.moveFromLocalFile(srcPath, dstPath);
    }

    /**
     * HDFS文件之间的复制
     * @param src源文件路径
     * @param dst要复制后复制文件的路径
     * @throws IOException
     */
    public void copyHDFSFile(String src, String dst) throws IOException {
        Path srcPath = new Path(src);
        Path dstPath = new Path(dst);
        InputStream in = fileSystem.open(srcPath);
        OutputStream out = fileSystem.create(dstPath);
        IOUtils.copy(in, out);
    }
    
    /**
     * 创建文件
     * @param file
     * @param dst
     * @throws IOException
     */
    public void create(String dst) throws IOException {
        FSDataOutputStream out = fileSystem.create(new Path(dst));
        out.close();
    }
    
    /**
     * hdfs 写文件
     * @param hdfs
     * @throws IOException
     */
    public void writeFile(String dst,String content) throws IOException {   
        FSDataOutputStream hdfsOutStream = fileSystem.create(new Path(dst));
        hdfsOutStream.writeChars(content);
        hdfsOutStream.close();
    }
    
    
    /**
     * hdfs 追加写文件
     * @param hdfs
     * @throws IOException
     */
    public void writeAppendFile(String dst,String content) throws IOException {   
        FSDataOutputStream out = fileSystem.append(new Path(dst));
        out.write(content.getBytes("UTF-8")); 
        out.hflush();
        out.close();
        
    }
    
    /**
     * HDFS中移动文件
     * @param src源文件路径
     * @param dst要移动后的路径
     * @throws IOException
     */
    @SuppressWarnings("deprecation")
    public void moveHDFSFile(String src, String dst) throws IOException {
        Path srcPath = new Path(src);
        Path dstPath = new Path(dst);
        InputStream in = fileSystem.open(srcPath);
        OutputStream out = fileSystem.create(dstPath);
        IOUtils.copy(in, out);
        fileSystem.delete(srcPath);
    }

    /**
     * 剪切HDFS文件到本地
     * @param HDFSSrc
     * @param localDst
     * @throws IOException
     */
    public void moveToLocalFile(String HDFSSrc, String localDst) throws IOException {
        Path srcPath = new Path(HDFSSrc);
        Path dstPath = new Path(localDst);
        fileSystem.moveToLocalFile(srcPath, dstPath);
    }

    /**
     * HDFS创建文件
     * 
     * @param in输入流
     * @param dst分布式存储路径
     * @throws IOException
     */
    public void create(InputStream in, String dst) throws IOException {
        Path dstPath = new Path(dst);
        FSDataOutputStream out = fileSystem.create(dstPath);
        IOUtils.copy(in, out);
    }
    /**
     * 在HDFS创建文件
     * @param file
     * @param dst分布式存储路径
     * @throws IOException
     */
    public void create(File file, String dst) throws IOException {
        InputStream in = new FileInputStream(file);
        this.create(in, dst);
    }

    /**
     * 在HDFS创建文件
     * @param src本地文件路径
     * @param dst分布式存储路径
     * @throws IOException
     */
    public void create(String src, String dst) throws IOException {
        File file = new File(src);
        this.create(file, dst);
    }

    /**
     * 获取FileSystem对象
     * @return
     */
    public FileSystem getFileSystem() {
        return fileSystem;
    }
    /**
     * 关闭HDFS
     * @throws IOException
     */
    public void close()   {
        try {
            if(fileSystem != null)
              fileSystem.close();
            else{
                
            }
        } catch (IOException e) { 
            e.printStackTrace();
        }
    }

    public boolean isDir(String src) throws IOException {
        Path path = new Path(src);
        return fileSystem.isDirectory(path);
    }

    public static void main(String[] args) throws IOException {
        String hdfsRoot ="/user/hadoop/storm";
       // System.setProperty("hadoop.home.dir", "I:\\software\\hadoop-2.6.0");
        HDFSUtils hdfs = new HDFSUtils(); 
        hdfs.init();
        System.out.println(hdfs.dir("/")); 
        System.out.println("================1==============");
        String terminalPath = hdfsRoot+"/"+"terminal_"+"108999000003";
        boolean existsTerminalPath =  hdfs.exists(terminalPath);
        if (!existsTerminalPath) {
            hdfs.createDir(terminalPath);
            hdfs.close();  
            hdfs.init();  

        }
        System.out.println("================2==============");
        String terminalTodayPath = terminalPath + "/temialinfo."+DatetimeUtil.getYMD(new Date())+".log";
        boolean existsTerminalTodayPath =  hdfs.exists(terminalTodayPath);
        if (!existsTerminalTodayPath) {
            hdfs.create(terminalTodayPath);
        }
        System.out.println("================3===============");
        StringBuffer sb = new StringBuffer();
        sb.append("108999000003,2016-12-27 17:20:05,863360028147399,0c3631303038393039393030303030303030333030,460010430511343,MI 3W,Xiaomi,MMSHANGCHENG,108999000003-Android,unknown,2.0,2,Android,Android,unknown,unknown,8,1920*1080,Wi-Fi,unknown,unknown,ARMv7 Processor rev 1 (v7l)");
        System.out.println("================4===============");

        hdfs.writeAppendFile(terminalTodayPath, sb.toString());
        System.out.println("================5===============");
        hdfs.close();
    }

}

/**
 * Revision history
 * -------------------------------------------------------------------------
 * 
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2016年12月26日 cmcc-B100036 creat
 */
