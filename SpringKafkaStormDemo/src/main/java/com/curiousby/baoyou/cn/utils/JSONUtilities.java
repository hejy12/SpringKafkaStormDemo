package com.curiousby.baoyou.cn.utils;

/**   */
public class JSONUtilities {
    /**
     * static finals
     */
    public static final int LENGTH_EVENT_TAG = 64;
    public static final int LENGTH_EVENT_ATTR = 128;
    public static final int LENGTH_EVENT_LABEL = 128;
    public static final int LENGTH_APPLICATION_KEY = 256;
    public static final int LENGTH_APPLICATION_CHANNEL = 256;

    public static final String DEFAULT_UNKNOWN = "UnKnown";
    public static final String DEFAULT_NOT_AVAILABLE = "N/A";
    /**
     * token pairs
     */
    public static final JSONTokenPair TKN_DURATION = new JSONTokenPair("1", "duration");
    public static final JSONTokenPair TKN_END_MILLIS = new JSONTokenPair("2", "end_millis");
    public static final JSONTokenPair TKN_START_MILLIS = new JSONTokenPair("3", "start_millis");
    public static final JSONTokenPair TKN_ACTIVITIES = new JSONTokenPair("4", "activities");
    public static final JSONTokenPair TKN_APPKEY = new JSONTokenPair("5", "appkey");
    public static final JSONTokenPair TKN_SESSION_ID = new JSONTokenPair("6", "session_id");
    public static final JSONTokenPair TKN_TYPE = new JSONTokenPair("7", "type");
    public static final JSONTokenPair TKN_LAUNCH = new JSONTokenPair("8", "launch");
    public static final JSONTokenPair TKN_TIME = new JSONTokenPair("9", "time");

    public static final JSONTokenPair TKN_TERMINATE = new JSONTokenPair("11", "terminate");
    public static final JSONTokenPair TKN_ERROR = new JSONTokenPair("12", "error");
    public static final JSONTokenPair TKN_CONTEXT = new JSONTokenPair("13", "context");
    public static final JSONTokenPair TKN_FLUSH = new JSONTokenPair("14", "flush");
    public static final JSONTokenPair TKN_EVENT = new JSONTokenPair("15", "event");
    public static final JSONTokenPair TKN_TAG = new JSONTokenPair("16", "tag");
    public static final JSONTokenPair TKN_LABEL = new JSONTokenPair("17", "label");
    public static final JSONTokenPair TKN_ACC = new JSONTokenPair("18", "acc");
    public static final JSONTokenPair TKN_HEADER = new JSONTokenPair("19", "header");

    public static final JSONTokenPair TKN_BODY = new JSONTokenPair("21", "body");
    public static final JSONTokenPair TKN_TRUE = new JSONTokenPair("22", "true");
    public static final JSONTokenPair TKN_FALSE = new JSONTokenPair("23", "false");
    public static final JSONTokenPair TKN_DEVICE_MAC = new JSONTokenPair("24", "device_mac");
    public static final JSONTokenPair TKN_DEVICE_ID = new JSONTokenPair("25", "device_id");
    public static final JSONTokenPair TKN_DEVICE_IMSI = new JSONTokenPair("26", "device_imsi");
    public static final JSONTokenPair TKN_DEVICE_MODEL = new JSONTokenPair("27", "device_model");
    public static final JSONTokenPair TKN_DEVICE_MANUFACTURE = new JSONTokenPair("28", "device_manufacture");
    public static final JSONTokenPair TKN_APP_VERSION = new JSONTokenPair("29", "app_version");

    public static final JSONTokenPair TKN_VERSION_CODE = new JSONTokenPair("31", "version_code");
    public static final JSONTokenPair TKN_SDK_TYPE = new JSONTokenPair("32", "sdk_type");
    public static final JSONTokenPair TKN_SDK_VERSION = new JSONTokenPair("33", "sdk_version");
    public static final JSONTokenPair TKN_OS = new JSONTokenPair("34", "os");
    public static final JSONTokenPair TKN_OS_VERSION = new JSONTokenPair("35", "os_version");
    public static final JSONTokenPair TKN_COUNTRY = new JSONTokenPair("36", "country");
    public static final JSONTokenPair TKN_LANGUAGE = new JSONTokenPair("37", "language");
    public static final JSONTokenPair TKN_TIMEZONE = new JSONTokenPair("38", "timezone");
    public static final JSONTokenPair TKN_RESOLUTION = new JSONTokenPair("39", "resolution");

    public static final JSONTokenPair TKN_ACCESS = new JSONTokenPair("41", "access");
    public static final JSONTokenPair TKN_ACCESS_SUBTYPE = new JSONTokenPair("42", "access_subtype");
    public static final JSONTokenPair TKN_CARRIER = new JSONTokenPair("43", "carrier");
    public static final JSONTokenPair TKN_LAT = new JSONTokenPair("44", "lat");
    public static final JSONTokenPair TKN_LNG = new JSONTokenPair("45", "lng");
    public static final JSONTokenPair TKN_CPU = new JSONTokenPair("46", "cpu");
    public static final JSONTokenPair TKN_CONTENT = new JSONTokenPair("47", "content");
    public static final JSONTokenPair TKN_UNKNOWN = new JSONTokenPair("48", "unknown");
    public static final JSONTokenPair TKN_NA = new JSONTokenPair("49", "N/A");

    public static final JSONTokenPair TKN_ATTRIBUTE = new JSONTokenPair("50", "attribute");
    public static final JSONTokenPair TKN_USERID = new JSONTokenPair("51", "user_id");
    public static final JSONTokenPair TKN_CHANNEL = new JSONTokenPair("52", "channel");
    public static final JSONTokenPair TKN_TRANSACTION = new JSONTokenPair("53", "transaction");
    public static final JSONTokenPair TKN_TRANS_ID = new JSONTokenPair("54", "trans_id");

    public static final JSONTokenPair TKN_TASK_ID = new JSONTokenPair("55", "task_id");
    public static final JSONTokenPair TKN_DEVICE_TOKEN = new JSONTokenPair("56", "device_token");

    /**
     * 
     * @param paramInput
     * @param paramError
     * @return
     */
    private static String getSecureString(String paramInput, String paramError) {
        try {
            if (null == paramInput || paramInput.length() < 1) {
                paramInput = paramError;
            }
        } catch (Exception e) {
            return paramError;
        }
        return paramInput;
    }

    /**
     * 
     * @param paramInput
     * @return
     */
    public static String getSecureString(String paramInput) {
        return getSecureString(paramInput, "");
    }

    /**
     * 
     * @param paramInput
     * @return
     */
    public static String getSecureStringNA(String paramInput) {
        return getSecureString(paramInput, "N/A");
    }

    /**
     * 
     * @param paramInput
     * @return
     */
    public static String getSecureStringUnknown(String paramInput) {
        return getSecureString(paramInput, "Unknown");
    }

    /**
     * 
     * @param paramInput
     * @return
     */
    public static String getSecureStringEmpty(String paramInput) {
        return getSecureString(paramInput, "Empty");
    }

    
}//end JSONUtilities
