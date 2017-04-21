package com.curiousby.baoyou.cn.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author baoyou E-mail:curiousby@163.com
 * @version 创建时间：2015年7月10日 上午11:09:17 des:
 */
public class DatetimeUtil {
    public static final long SECOND = 1000;
    public static final long MINITE = SECOND * 60;
    public static final long HOUR = MINITE * 60;
    public static final long DAY = HOUR * 24;

    /** yyyy-MM-dd HH:mm:ss */
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /** yyyy-MM-dd */
    private static final SimpleDateFormat SDF_YMD = new SimpleDateFormat("yyyy-MM-dd");
    /** yyyyMMdd */
    private static final SimpleDateFormat SDF_YMDH = new SimpleDateFormat("yyyyMMddHH");
    /** yyyyMMddHHmmss.SSS */
    private static final SimpleDateFormat SDF_YMDHMS_MS = new SimpleDateFormat(
            "yyyyMMddHHmmss.SSS");
    /** MMddHHmmssSSS */
    private static final SimpleDateFormat SDF_MDHMSMS = new SimpleDateFormat("MMddHHmmssSSS");

    private static ReentrantLock sdf_lock = new ReentrantLock();
    private static ReentrantLock ymd_lock = new ReentrantLock();
    private static ReentrantLock ymdh_lock = new ReentrantLock();
    private static ReentrantLock ymdhmsms_lock = new ReentrantLock();
    private static ReentrantLock mdhmsms_lock = new ReentrantLock();

    /**
     * @param d
     * @return yyyy-MM-dd
     */
    public static String getYMD(Date d) {
        String temp;
        ymd_lock.lock();
        temp = SDF_YMD.format(d);
        ymd_lock.unlock();
        return temp;
    }

    /**
     * @param ymd
     *            yyyy-MM-dd
     * @return
     */
    public static Date fromYMD(String ymd) {
        Date temp = null;
        try {
            ymd_lock.lock();
            temp = SDF_YMD.parse(ymd);
        } catch (ParseException e) {
        } finally {
            ymd_lock.unlock();
        }
        return temp;
    }

    /**
     * @param d
     * @return yyyyMMddHH
     */
    public static String getYMDH(Date d) {
        String temp;
        ymdh_lock.lock();
        temp = SDF_YMDH.format(d);
        ymdh_lock.unlock();
        return temp;
    }

    /**
     * @param ymd
     *            yyyyMMddHH
     * @return
     */
    public static Date fromYMDH(String ymd) {
        Date temp = null;
        try {
            ymdh_lock.lock();
            temp = SDF_YMDH.parse(ymd);
        } catch (ParseException e) {
        } finally {
            ymdh_lock.unlock();
        }
        return temp;
    }

    /**
     * @param d
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatYMDHMS(Date d) {
        String temp;
        sdf_lock.lock();
        temp = SDF.format(d);
        sdf_lock.unlock();
        return temp;
    }

    /**
     * @param d
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static Date getYMDHMS(String d) {
        Date temp = null;
        try {
            sdf_lock.lock();
            temp = SDF.parse(d);
        } catch (ParseException e) {
        } finally {
            sdf_lock.unlock();
        }
        return temp;
    }

    /**
     * 
     * @param d
     * @return yyyyMMddHHmmss.SSS
     */
    public static String getYMDHMSMS(Date d) {
        String temp;
        ymdhmsms_lock.lock();
        temp = SDF_YMDHMS_MS.format(d);
        ymdhmsms_lock.unlock();
        return temp;
    }

    /**
     * 
     * @param time
     *            format
     * @return yyyyMMddHHmmss.SSS
     * @throws ParseException
     */
    public static long getYMDHMSMS(String time) throws Exception {
        try {
            long temp;
            ymdhmsms_lock.lock();
            temp = SDF_YMDHMS_MS.parse(time).getTime();
            return temp;
        } catch (Exception e) {
            throw new Exception("");
        } finally {
            ymdhmsms_lock.unlock();
        }
    }

    /**
     * @param date
     * @return yyyyMMddHHmmss.SSS
     */
    public static String formatYMDHMSMS(Date date) {
        String temp;
        ymdhmsms_lock.lock();
        temp = SDF_YMDHMS_MS.format(date);
        ymdhmsms_lock.unlock();
        return temp;
    }

    /**
     * @param timestamp
     * @return yyyyMMddHHmmss.SSS
     */
    public static String formatYMDHMSMS(long timestamp) {
        String temp;
        Date date = new Date(timestamp);
        ymdhmsms_lock.lock();
        temp = SDF_YMDHMS_MS.format(date);
        ymdhmsms_lock.unlock();
        return temp;
    }

    /**
     * @param date
     * @return MMddHHmmssSSS 月日时分秒毫秒
     */
    public static String formatMDHMSsss(Date date) {
        String temp;
        mdhmsms_lock.lock();
        temp = SDF_MDHMSMS.format(date);
        mdhmsms_lock.unlock();
        return temp;
    }

    /**
     * 求时间差，单位为毫秒 当to时间小于from时间时，返回负数
     * 
     * @param from
     * @param to
     * @return
     */
    public static long diffMillisecond(Date from, Date to) {
        long diff = 0;
        diff = to.getTime() - from.getTime();
        return diff;
    }

    /** HH:mm:ss */
    private static final SimpleDateFormat SDF_HMS = new SimpleDateFormat("HH:mm:ss");
    private static ReentrantLock SDF_HMS_LOCK = new ReentrantLock();

    /**
     * 
     * @param d
     * @return
     */
    public static String formatHMS(Date d) {
        String temp;
        SDF_HMS_LOCK.lock();
        temp = SDF_HMS.format(d);
        SDF_HMS_LOCK.unlock();
        return temp;
    }

    /**
     * 获得当前的时间戳，此方法为同步方法，在方法内会sleep一毫秒
     * 
     * @return
     */
    public static synchronized long getUniqueTimestamp() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }
}
