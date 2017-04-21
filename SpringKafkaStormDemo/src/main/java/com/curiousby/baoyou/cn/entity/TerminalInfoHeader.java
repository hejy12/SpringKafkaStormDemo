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

import java.beans.Transient;
import java.io.Serializable;

import org.json.JSONObject;

import com.curiousby.baoyou.cn.utils.JSONUtilities;
import com.curiousby.baoyou.cn.utils.StringUtils;

/**
 * @Type TerminalInfoHeader.java
 * @Desc 
 * @author cmcc-B100036
 * @date 2016年12月16日 上午11:30:13
 * @version 
 */
public class TerminalInfoHeader implements EntityFormat<TerminalInfoHeader> ,Serializable{ 
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public String deviceMac;           //"24", "device_mac"
    public String deviceId;            //"25", "device_id"
    public String deviceToken;         //"56", "device_token"
    public String deviceImsi;          //"26", "device_imsi"
    public String deviceModel;         //"27", "device_model"
    public String deviceManufacture;   //"28",  "device_manufacture"
    public String channel;             //"52", "channel"
    public String appKey;              //"5", "appkey"
    public String userId;              //"51", "user_id"
    public String appVersion;          //"29", "app_version"
    public String versionCode;            //"31", "version_code"
    public String sdkType;             //"32", "sdk_type"
    public String sdkVersion;             //"33", "sdk_version"
    public String os;                  //"34", "os"
    public String country;             //"36", "country"
    public String language;            //"37", "language"
    public String timezone;               //"38", "timezone"
    public String resolution;          //"39", "resolution"
    public String access;              //"41", "access"
    public String accessSubtype;       //"42", "access_subtype"
    public String carrier;             //"43", "carrier"
    public String cpu;                 //"46", "cpu"
    public String taskId;              //"55", "task_id"
     
    public   TerminalInfoHeader formate(JSONObject localJson) { 
        
        this.deviceMac = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_DEVICE_MAC.getKey()));
        this.deviceId = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_DEVICE_ID.getKey()));
        this.deviceToken = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_DEVICE_TOKEN.getKey()));
        this.deviceImsi = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_DEVICE_IMSI.getKey()));
        this.deviceModel = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_DEVICE_MODEL.getKey()));
        this.deviceManufacture = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_DEVICE_MANUFACTURE.getKey()));
        this.channel = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_CHANNEL.getKey()));
        this.appKey = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_APPKEY.getKey()));
        this.userId = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_USERID.getKey()));
        this.appVersion = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_APP_VERSION.getKey()));
        this.versionCode = JSONUtilities .getSecureString(String.valueOf(localJson.getInt(JSONUtilities.TKN_VERSION_CODE.getKey())));
        this.sdkType = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_SDK_TYPE.getKey()));
        this.sdkVersion = JSONUtilities .getSecureString(String.valueOf(localJson.getInt(JSONUtilities.TKN_SDK_VERSION.getKey())));
        this.os = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_OS.getKey()));
        this.country = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_COUNTRY.getKey()));
        this.language = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_LANGUAGE.getKey()));
        this.timezone = JSONUtilities .getSecureString(String.valueOf(localJson.getInt(JSONUtilities.TKN_TIMEZONE.getKey())));
        this.resolution = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_RESOLUTION.getKey()));
        this.access = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_ACCESS.getKey()));
        this.accessSubtype = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_ACCESS_SUBTYPE.getKey()));
        this.carrier = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_CARRIER.getKey()));
        this.cpu = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_CPU.getKey()));
        this.taskId = JSONUtilities .getSecureString(localJson.getString(JSONUtilities.TKN_TASK_ID.getKey()));
         
        return this;
    }

 
    
    @Override
    public String toString() {
        return "TerminalInfoHeader [deviceMac=" + deviceMac + ", deviceId=" + deviceId
                + ", deviceToken=" + deviceToken + ", deviceImsi=" + deviceImsi + ", deviceModel="
                + deviceModel + ", deviceManufacture=" + deviceManufacture + ", channel=" + channel
                + ", appKey=" + appKey + ", userId=" + userId + ", appVersion=" + appVersion
                + ", versionCode=" + versionCode + ", sdkType=" + sdkType + ", sdkVersion="
                + sdkVersion + ", os=" + os + ", country=" + country + ", language=" + language
                + ", timezone=" + timezone + ", resolution=" + resolution + ", access=" + access
                + ", accessSubtype=" + accessSubtype + ", carrier=" + carrier + ", cpu=" + cpu
                + ", taskId=" + taskId + "]";
    }


    @Transient
    public String getAppId(){
        if (!this.appKey.contains("-")) {
            return null;
        }
       return  StringUtils.split(appKey, "-")[0]; 
    }
    

    public String getDeviceMac() {
        return deviceMac;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public String getDeviceImsi() {
        return deviceImsi;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public String getDeviceManufacture() {
        return deviceManufacture;
    }

    public String getChannel() {
        return channel;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getUserId() {
        return userId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public String getSdkType() {
        return sdkType;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public String getOs() {
        return os;
    }

    public String getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getResolution() {
        return resolution;
    }

    public String getAccess() {
        return access;
    }

    public String getAccessSubtype() {
        return accessSubtype;
    }

    public String getCarrier() {
        return carrier;
    }

    public String getCpu() {
        return cpu;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setDeviceMac(String deviceMac) {
        this.deviceMac = deviceMac;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public void setDeviceImsi(String deviceImsi) {
        this.deviceImsi = deviceImsi;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public void setDeviceManufacture(String deviceManufacture) {
        this.deviceManufacture = deviceManufacture;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public void setSdkType(String sdkType) {
        this.sdkType = sdkType;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public void setAccessSubtype(String accessSubtype) {
        this.accessSubtype = accessSubtype;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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