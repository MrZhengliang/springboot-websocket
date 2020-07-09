package com.example.demo.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xxxx.
 *
 * @author xxxx
 */

public class DateUtil {


    /*************************************************************************************************************/
    private static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 正例：注意线程安全，使用 DateUtil 。亦推荐如下处理：
     */
    private static final ThreadLocal<SimpleDateFormat> YEAR_MONTH_DATE = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };
    /***************************************************************************************************/
    public static final long YEAR_MILLION_SECONDS = 365 * 24 * 60 * 60 * 1000;
    public static final long MONTH_MILLION_SECONDS = 30 * 24 * 60 * 60 * 1000;
    public static final long DAY_MILLION_SECONDS = 24 * 60 * 60 * 1000;
    public static final long HOUR_MILLION_SECONDS = 60 * 60 * 1000;
    public static final long MINUTE_MILLION_SECONDS = 60 * 1000;
    public static final long MILLOIN_SECONDS = 1000;

    public static final String DATE_STRING_FORMAT_ALL = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_STRING_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm";
    public static final String DATE_STRING_FORMAT_CN = "yyyy年MM月dd日";
    public static final String DATE_STRING_FORMAT_WEEK_CN = "yyyy/MM/dd E";
    //    public static final String YEAR_MONTH_DATE = "yyyy-MM-dd";
    public static final String YEAR_MONTH_DATE_2 = "yyyy/MM/dd";
    public static final String MONTH_DATE = "MM-dd";
    public static final String MONTH_DATE_CN = "MM月dd日";
    public static final String DATE_FORMAT_TIME = "HH:mm";

    public static final String DATE_STRING_FORMAT_DAY = "yyyy-MM-dd";
    public static final String DATE_STRING_FORMAT_DAY_HOUR_MIN = "yyyy-MM-dd HH:mm";
    public static final String DATE_STRING_FORMAT_DAY2 = "yyyy.MM.dd";
    public static final String DATE_STRING_FORMAT_DAY3 = "yyyyMMdd";
    public static final String DATE_STRING_FORMAT_CN2 = "yyyy年MM月";
    public static final String DATE_STRING_FORMAT_CN3 = "yyyy年";

    public static final String TIME = "HH:mm:ss";
    public static final String DEFAULT = "yyyy-MM-dd";
    public static final String STANDARD = "yyyy-MM-dd HH:mm";
    public static final String ALL = "yyyy-MM-dd HH:mm:ss";
    public static final String STANDARD_CN = "yyyy年MM月dd日 HH时mm分";
    public static final String SIMPLE_CN = "yyyy年MM月dd日 HH时mm分ss秒";
    public static final String DATE_STRING_FORMAT_DAY_HOUR_MIN_CN = "yyyy年MM月dd日 HH时mm分";
    public static final String DATE_STRING_FORMAT_MON_DAY_HOUR_MIN_CN = "MM月dd日 HH时mm分";


    public static final String DATE_STRING_FORMAT_DAY_HOUR_MIN_SECOND = "yyyyMMddHHmmss";

    public static final String DATE_STRING_FORMAT_DAY_HOUR_MIN_SECOND_SSS = "yyyyMMddHHmmssSSS";

    /**
     * 将数字类型时间转换为时间字符串
     *
     * @param dateNumber
     * @param formatStr
     * @return
     */
    public static String dateLongToStr(Long dateNumber, String formatStr) {
        if (dateNumber == null || dateNumber.equals(0L) || formatStr == null || formatStr.length() == 0) {
            return "";
        }
        Date date = new Date(dateNumber);
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }

    public static String dateLongToStr(Long dateNumber) {
        Date date = new Date(dateNumber);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_STRING_FORMAT_DEFAULT);
        return sdf.format(date);
    }

    public static String dateToStr(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_STRING_FORMAT_DEFAULT);
        return sdf.format(date);
    }


    public static Long stringToDateToLong(String format, String time) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = null;
        Long ltime = 0L;
        try {
            date = formatter.parse(time);
            ltime = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ltime;
    }

    /**
     * 转化时间从长整形为指定格式日期
     *
     * @param format
     * @param time
     * @return
     */
    public static String convertDateLongToDateString(String format, Long time) {
        if (time == null || time == 0) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        Timestamp now = new Timestamp(time);
        return df.format(now);
    }

    /**
     * 转化时间从长整形为指定格式日期
     *
     * @param simpleDateFormat
     * @param time
     * @return
     */
    public static String convertDateLongToDateString(SimpleDateFormat simpleDateFormat, Long time) {
        if (time == null || time == 0) {
            return "";
        }
        Timestamp now = new Timestamp(time);
        return simpleDateFormat.format(now);
    }

    /**
     * 转化时间从指定格式日期为长整形
     *
     * @param format
     * @param time
     * @return
     */
    public static Long convertDateStringToDateLong(String format, String time) {
        if (time == null || "".equals(time.trim())) {
            return null;
        }
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        Date d = null;
        try {
            d = fmt.parse(time);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        if (null != d) {
            return d.getTime();
        } else {
            return null;
        }
    }

    /**
     * 转化时间串为指定格式日期
     *
     * @param format
     * @param time
     * @return date
     */
    public static Date convertDateStringToDate(String format, String time) {
        if (time == null || "".equals(time.trim())) {
            return null;
        }
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        Date d = null;
        try {
            d = fmt.parse(time);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        if (null != d) {
            return d;
        } else {
            return null;
        }
    }


    public static String convertDateLong2SimpleCNFormat(Long time) {
        if (time == null) {
            return null;
        }
        return new SimpleDateFormat(SIMPLE_CN).format(new Date(time));
    }

    public static String fullFormat(Date date) {
        if (date == null) {
            return "";
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static Date fullFormatNow() {
        Date now = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(now);
        Date date1 = null;
        try {
            date1 = (Date) format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }

    /**
     * @param formatString
     * @param date
     * @return
     */
    public static String format(String formatString, Date date) {
        if (date == null) {
            return "";
        }
        DateFormat format = new SimpleDateFormat(formatString);
        return format.format(date);
    }


    public static String getLinuxTime() {
        DateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String r = format.format(new Date());
        return r;
    }

    // 段时间
    public static String toPeriodTime(long m) {
        StringBuffer sb = new StringBuffer();
        long years = m / YEAR_MILLION_SECONDS;
        m = m - years * YEAR_MILLION_SECONDS;
        long months = m / MONTH_MILLION_SECONDS;
        m = m - months * MONTH_MILLION_SECONDS;
        long days = m / DAY_MILLION_SECONDS;
        m = m - days * DAY_MILLION_SECONDS;
        long hours = m / HOUR_MILLION_SECONDS;
        m = m - hours * HOUR_MILLION_SECONDS;
        long minutes = m / MINUTE_MILLION_SECONDS;
        if (0 != years) {
            sb.append(String.valueOf(years));
            sb.append("年");
        }
        if (0 != months) {
            sb.append(String.valueOf(months));
            sb.append("个月");
        }
        if (0 != days) {
            sb.append(String.valueOf(days));
            sb.append("天");
        }
        if (0 != hours) {
            sb.append(String.valueOf(hours));
            sb.append("小时");
        }
        if (0 != minutes) {
            sb.append(String.valueOf(minutes));
            sb.append("分钟");
        }
        return sb.toString();
    }

    /**
     * 时间段转换
     * <p>
     * 刚刚报价
     * <p>
     * 2-59分钟前报价
     * <p>
     * 1-23小时前报价
     * <p>
     * 1-29天前报价
     * <p>
     * 1-12个月前报价
     * <p>
     * 1年前报价
     */

    public static String toChangeTime(long m) {

        String result = "刚刚";

        long mtime = System.currentTimeMillis();
        long mtimeDiff = mtime - m;

        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        if (mtimeDiff < nm * 2) {
            result = "刚刚";
        } else if (nm * 2 <= mtimeDiff && mtimeDiff < nm * 60) {
            long min = mtimeDiff % nd % nh / nm;// 计算差多少分钟
            result = min + "分钟前";
        } else if (nh <= mtimeDiff && mtimeDiff < nh * 24) {
            long hour = mtimeDiff % nd / nh;// 计算差多少小时
            result = hour + "小时前";
        } else if (nd <= mtimeDiff && mtimeDiff < nd * 30) {
            long day = mtimeDiff / nd;
            result = day + "天前";
        } else if (nd * 30 <= mtimeDiff && mtimeDiff < nd * 30 * 12) {
            long month = mtimeDiff / (nd * 30);
            result = month + "个月前";
        } else if (mtimeDiff >= nd * 30 * 12) {
            long year = mtimeDiff / (nd * 30 * 12);
            result = year + "年前";
        } else {
            result = "刚刚";
        }

        return result;
    }

    public static long getQuot(String time1, String time2) {
        long quot = 0;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = ft.parse(time1);
            Date date2 = ft.parse(time2);
            quot = date1.getTime() - date2.getTime();
            quot = quot / 1000 / 60 / 60 / 24;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return quot;
    }


    //日期转换
    public static String convertDateLongToString(Long time) {
        if (time == null) {
            return "";
        }
        Date d = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return sdf.format(d);
    }

    public static String convertDateLongToString(Long time, String format) {
        if (time == null) {
            return "";
        }
        Date d = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(d);
    }

    /**
     * 得到今天零点零分零秒的Long值
     */
    public static Long getTheDayTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_STRING_FORMAT_DAY);
        return convertDateStringToDateLong(DATE_STRING_FORMAT_DAY, sdf.format(new Date()));
    }

    /**
     * 得到当前时间，精确到分
     */
    public static Long getTheDayTime2() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_STRING_FORMAT_DAY_HOUR_MIN);
        return convertDateStringToDateLong(DATE_STRING_FORMAT_DAY_HOUR_MIN, sdf.format(new Date()));
    }

    /**
     * 得到未来N天
     */
    public static Date getInnerDay(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + n);
        return calendar.getTime();
    }

    /**
     * 得到未来N天零点零分零秒的Long值
     */
    public static Long getInnerDayTime(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + n);
        return calendar.getTimeInMillis();
    }

    /**
     * 得到startTime未来N天零点零分零秒的Long值
     */
    public static Long getInnerDayTime(long standardTime, int differenceDayNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(standardTime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + differenceDayNum);
        return calendar.getTimeInMillis();
    }

    public static Long getInnerDayTime(String standardTime, int differenceDayNum) {
        if (StringUtils.isBlank(standardTime)) {
            return null;
        }
        Long dateLong = convertDateStringToDateLong(DateUtil.DATE_STRING_FORMAT_DAY, standardTime);
        return getInnerDayTime(dateLong, differenceDayNum);
    }

    /**
     * chenyuan
     *
     * 得到某月的结束时间
     * @param date
     * @return
     */

    public static Date getLastDayOfMonth(Date date) {
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(date);
        endCal.add(Calendar.MONTH, 1);
        endCal.set(Calendar.DATE, 1);
        endCal.add(Calendar.DATE, -1);
        endCal.set(Calendar.HOUR_OF_DAY, endCal.getMaximum(Calendar.HOUR_OF_DAY));
        endCal.set(Calendar.MINUTE, endCal.getMaximum(Calendar.MINUTE));
        endCal.set(Calendar.SECOND, endCal.getMaximum(Calendar.SECOND));
        endCal.set(Calendar.MILLISECOND, endCal.getMaximum(Calendar.MILLISECOND));
        return endCal.getTime();
    }

    /**
     * chenyuan
     *
     *得到某月的开始时间
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(date);
        startCal.set(Calendar.DATE, startCal.getMinimum(Calendar.DATE));
        startCal.set(Calendar.HOUR_OF_DAY, startCal.getMinimum(Calendar.HOUR_OF_DAY));
        startCal.set(Calendar.MINUTE, startCal.getMinimum(Calendar.MINUTE));
        startCal.set(Calendar.SECOND, startCal.getMinimum(Calendar.SECOND));
        startCal.set(Calendar.MILLISECOND, startCal.getMinimum(Calendar.MILLISECOND));
        return startCal.getTime();
    }

    /**
     * 获得当前月第一天 (yyyy-MM-dd)
     */
    @SuppressWarnings("static-access")
    public static String getFirtDayOfMonth() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar startCal = Calendar.getInstance();
        startCal.set(Calendar.DATE, 1);
        startCal.set(Calendar.HOUR_OF_DAY, 0);
        startCal.set(Calendar.MINUTE, 0);
        startCal.set(Calendar.SECOND, 0);
        startCal.set(Calendar.MILLISECOND, 0);
        return df.format(startCal.getTime());
    }


    /**
     * 获得当前月第一天 (自定义)
     */
    @SuppressWarnings("static-access")
    public static String getFirtDayOfMonth(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        Calendar startCal = Calendar.getInstance();
        startCal.set(Calendar.DATE, 1);
        startCal.set(Calendar.HOUR_OF_DAY, 0);
        startCal.set(Calendar.MINUTE, 0);
        startCal.set(Calendar.SECOND, 0);
        startCal.set(Calendar.MILLISECOND, 0);
        return df.format(startCal.getTime());
    }

    /**
     * 获得当前月第一天 (yyyy-MM-dd HH:mm)
     */
    @SuppressWarnings("static-access")
    public static String getFirtDayTimeOfMonth() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar startCal = Calendar.getInstance();
        startCal.set(Calendar.DATE, 1);
        startCal.set(Calendar.HOUR_OF_DAY, 0);
        startCal.set(Calendar.MINUTE, 0);
        startCal.set(Calendar.SECOND, 0);
        startCal.set(Calendar.MILLISECOND, 0);
        String dayStart = df.format(startCal.getTime());
        return dayStart;
    }

    /**
     * 获取一个月之前的时间 (yyyy-MM-dd HH:mm:ss)
     *
     * @return
     */
    public static Long getOneMonthBeforeDateTime() {
        Calendar startCal = Calendar.getInstance();
        startCal.add(Calendar.MONTH, -1);
        startCal.set(Calendar.HOUR_OF_DAY, 0);
        startCal.set(Calendar.MINUTE, 0);
        startCal.set(Calendar.SECOND, 0);
        startCal.set(Calendar.MILLISECOND, 0);
        Date date = (Date) startCal.getTime().clone();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return convertDateStringToDateLong("yyyy-MM-dd HH:mm:ss", format.format(date));
    }


    /**
     * 获取当前月第一天 (yyyy-MM-dd HH:mm:ss)
     *
     * @return
     */
    @SuppressWarnings("static-access")
    public static Long getFirstDayDateTimeOfMonth() {
        Calendar startCal = Calendar.getInstance();
        startCal.set(Calendar.DATE, 1);
        startCal.set(Calendar.HOUR_OF_DAY, 0);
        startCal.set(Calendar.MINUTE, 0);
        startCal.set(Calendar.SECOND, 0);
        startCal.set(Calendar.MILLISECOND, 0);
        Date date = (Date) startCal.getTime().clone();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return convertDateStringToDateLong("yyyy-MM-dd HH:mm:ss", format.format(date));

    }


    /**
     * 获取当前月第一天 (yyyy-MM-dd HH:mm:ss)
     *
     * @return
     * @throws
     */
    @SuppressWarnings("static-access")
    public static Date getFirstDayOfMonth() {
        Calendar startCal = Calendar.getInstance();
        startCal.set(Calendar.DATE, 1);
        startCal.set(Calendar.HOUR_OF_DAY, 0);
        startCal.set(Calendar.MINUTE, 0);
        startCal.set(Calendar.SECOND, 0);
        startCal.set(Calendar.MILLISECOND, 0);
        Date date = (Date) startCal.getTime().clone();
        return date;
    }

    /**
     * 获得当前月最后一天 (yyyy-MM-dd)
     */
    @SuppressWarnings("static-access")
    public static String getLastDayOfMonth() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar endCal = Calendar.getInstance();
        endCal.add(Calendar.MONTH, 1);
        endCal.set(Calendar.DATE, 1);
        endCal.add(Calendar.DATE, -1);
        endCal.set(Calendar.HOUR_OF_DAY, 0);
        endCal.set(Calendar.MINUTE, 0);
        endCal.set(Calendar.SECOND, 0);
        endCal.set(Calendar.MILLISECOND, 0);
        String dayEnd = df.format(endCal.getTime());
        return dayEnd;
    }

    /**
     * 获得当前月最后一天 (自定义)
     */
    @SuppressWarnings("static-access")
    public static String getLastDayOfMonth(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        Calendar endCal = Calendar.getInstance();
        endCal.add(Calendar.MONTH, 1);
        endCal.set(Calendar.DATE, 1);
        endCal.add(Calendar.DATE, -1);
        endCal.set(Calendar.HOUR_OF_DAY, 0);
        endCal.set(Calendar.MINUTE, 0);
        endCal.set(Calendar.SECOND, 0);
        endCal.set(Calendar.MILLISECOND, 0);
        String dayEnd = df.format(endCal.getTime());
        return dayEnd;
    }


    /**
     * 获得当前月上个月最后一天
     */
    public static Long getLastDayOfLastMonth() {
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        int year = 0;
        int month = cal.get(Calendar.MONTH); // 上个月月份
        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 结束天数
        if (month == 0) {
            year = cal.get(Calendar.YEAR) - 1;
            month = 12;
        } else {
            year = cal.get(Calendar.YEAR);
        }
        return convertDateStringToDateLong("yyyy-MM-dd", (year + "-" + month + "-" + day));
    }

    /**
     * 获得当前月上个月第一天
     */
    public static Long getFirstDayOfLastMonth() {
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        int year = 0;
        int month = cal.get(Calendar.MONTH); // 上个月月份
        int day = cal.getActualMinimum(Calendar.DAY_OF_MONTH); // 开始天数
        if (month == 0) {
            year = cal.get(Calendar.YEAR) - 1;
            month = 12;
        } else {
            year = cal.get(Calendar.YEAR);
        }
        return convertDateStringToDateLong("yyyy-MM-dd", (year + "-" + month + "-" + day));
    }

    /**
     * 获取当前月最后一天23时59分59秒
     *
     * @return
     * @throws
     */
    @SuppressWarnings("static-access")
    public static Long getLastDayDateTimeOfMonth() {
        Calendar endCal = Calendar.getInstance();
        endCal.add(Calendar.MONTH, 1);
        endCal.set(Calendar.DATE, 1);
        endCal.add(Calendar.DATE, -1);
        endCal.set(Calendar.HOUR_OF_DAY, 23);
        endCal.set(Calendar.MINUTE, 59);
        endCal.set(Calendar.SECOND, 59);
        endCal.set(Calendar.MILLISECOND, 59);
        Date date = (Date) endCal.getTime().clone();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return convertDateStringToDateLong("yyyy-MM-dd HH:mm:ss", format.format(date));

    }


    @SuppressWarnings("static-access")
    public static String getLastDayTimeOfMonth() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        String lastDay = df.format(cal.getTime());
        return lastDay;
    }

    /**
     * 获取指定月最后一天23时59分59秒
     *
     * @return
     * @throws
     */
    @SuppressWarnings("static-access")
    public static Long getLastDayOfMonth(Calendar cal) {
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        return cal.getTimeInMillis();

    }

    /**
     * 获取一年的最后一天 23时59分59秒
     *
     * @return
     * @throws
     */
    public static String getEndOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        Date date = (Date) calendar.getTime().clone();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 获取当年的开始 00时00分00秒
     *
     * @return
     * @throws
     */
    public static String getStartOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = (Date) calendar.getTime().clone();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 获取当年的开始 00时00分00秒
     *
     * @return
     * @throws
     */
    public static Long getStartOfYearOfLong() {
        return convertDateStringToDateLong("yyyy-MM-dd HH:mm:ss", getStartOfYear());
    }

    public static Long convertDateDateShortToDateLong(String format, Date date) throws Exception {
        DateFormat df = new SimpleDateFormat(format);
        String dateStr = df.format(date);
        return convertDateStringToDateLong(format, dateStr);
    }

    public static Long getToday(String format, Date date) {
        DateFormat df = new SimpleDateFormat(format);
        return convertDateStringToDateLong(format, df.format(date));
    }

    public static String getNowTimeStr(String format) {
        return format(format, new Date());
    }

    public static Date getDateFromString(String format, String dateString) {
        if (StringUtils.isEmpty(dateString)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (Exception e) {
            date = null;
            System.err.println(e.getMessage());
        }
        return date;
    }

    /**
     * 获取特定日期开始时间：00：00：00
     *
     * @return 特定日0时0分0秒
     * @throws
     */
    public static Long getStartTimeOfDate(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        Long dateLong = convertDateStringToDateLong(DateUtil.DATE_STRING_FORMAT_DAY, dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(dateLong));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date startDate = (Date) cal.getTime().clone();
        DateFormat format = new SimpleDateFormat(DATE_STRING_FORMAT_ALL);
        return convertDateStringToDateLong(DATE_STRING_FORMAT_ALL, format.format(startDate));
    }


    /**
     * 获取特定日期开始时间：00：00：00:000
     *
     * @return 特定日0时0分0秒0毫秒
     * @throws
     */
    public static Long getStartTimeOfDateStr(String dateStr, String formatStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        Long dateLong = convertDateStringToDateLong(formatStr, dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(dateLong));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date startDate = (Date) cal.getTime().clone();
        DateFormat format = new SimpleDateFormat(DATE_STRING_FORMAT_ALL);
        return convertDateStringToDateLong(DATE_STRING_FORMAT_ALL, format.format(startDate));
    }

    //获取特定时间的开始毫秒 hh：mm：ss:0
    public static Long getStartTimeOfSecondStr(String dateStr, String formatStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        Long dateLong = convertDateStringToDateLong(formatStr, dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(dateLong));
        cal.set(Calendar.MILLISECOND, 0);
        Date startDate = (Date) cal.getTime().clone();
        return startDate.getTime();
    }

    /**
     * 获取特定日期结束时间：23：59：59:999
     *
     * @return 特定日23时59分59秒:999毫秒
     * @throws
     */
    public static Long getEndTimeOfDateStr(String dateStr, String formatStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        Long dateLong = convertDateStringToDateLong(formatStr, dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(dateLong));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        Date startDate = (Date) cal.getTime().clone();
        DateFormat format = new SimpleDateFormat(DATE_STRING_FORMAT_ALL);
        return convertDateStringToDateLong(DATE_STRING_FORMAT_ALL, format.format(startDate));
    }

    /**
     * 获取特定日期结束时间：hh：mm：ss:999
     */
    public static Long getEndTimeOfSecondStr(String dateStr, String formatStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        Long dateLong = convertDateStringToDateLong(formatStr, dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(dateLong));
        cal.set(Calendar.MILLISECOND, 999);
        Date startDate = (Date) cal.getTime().clone();
        return startDate.getTime();
    }

    /**
     * 获取特定日期开始时间：00：00：00
     *
     * @return 特定日0时0分0秒
     * @throws
     */
    public static Date getStartTimeOfDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取特定日期23：59：59
     *
     * @return 特定日期23：59：59
     * @throws
     */
    public static Date getEndTimeOfDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Long getNextMonthTime(long currentTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(currentTime));
        cal.add(Calendar.MONTH, 1);
        return cal.getTime().getTime();
    }


    /**
     * 获取当前天开始时间：00：00：00
     *
     * @return 当前天0时0分0秒
     * @throws
     */
    public static Long getStartTimeOfToday() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        Date date = (Date) currentDate.getTime().clone();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return convertDateStringToDateLong("yyyy-MM-dd HH:mm:ss", format.format(date));
    }

    /**
     * 获取当前天23：59：59
     * 1.1 modify by qxy 当前天23：59：59.999  modifyTime 2013-11-13 15:59
     *
     * @return 当前天23：59：59.999
     * @throws
     */
    public static Long getEndTimeOfToday() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        currentDate.set(Calendar.MILLISECOND, 999);
        return currentDate.getTimeInMillis();
//    Date date = (Date) currentDate.getTime().clone();
//    DateFormat format = new SimpleDateFormat(DATE_STRING_FORMAT_ALL);
//    return convertDateStringToDateLong(DATE_STRING_FORMAT_ALL, format.format(date));
    }

    /**
     * 判断目标时间是否在今天
     *
     * @param targetDate
     * @return
     */
    public static Boolean isInToday(Long targetDate) {
        if (targetDate == null) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Long startOfToday = calendar.getTimeInMillis();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        Long endOfToday = calendar.getTimeInMillis();
        if (targetDate >= startOfToday && targetDate <= endOfToday) {
            return true;
        }
        return false;
    }

    public static Boolean isInYesterday(Long targetDate) {
        if (targetDate == null) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Long startOfYesterday = calendar.getTimeInMillis();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        Long endOfYesterday = calendar.getTimeInMillis();
        if (targetDate >= startOfYesterday && targetDate <= endOfYesterday) {
            return true;
        }
        return false;
    }

    /**
     * 获取昨天：00：00：00
     *
     * @return 昨天：00：00：00
     * @throws
     */
    public static Long getStartTimeOfYesterday() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DAY_OF_YEAR, -1);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        Date date = (Date) currentDate.getTime().clone();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return convertDateStringToDateLong("yyyy-MM-dd HH:mm:ss", format.format(date));
    }

    /**
     * 两个日期间的年月日差
     *
     * @param startDate
     * @param endDate
     * @return XX年XX月XX日
     */
    public static String dateDifference(Long startDate, Long endDate, boolean hideDayIfMonth) {
        if (endDate.compareTo(startDate) < 0) {   //保证endDate>startDate
            Long tmp = startDate;
            startDate = endDate;
            endDate = tmp;
        }
        Calendar calS = Calendar.getInstance();
        calS.setTimeInMillis(startDate);
        int startY = calS.get(Calendar.YEAR);
        int startM = calS.get(Calendar.MONTH);
        int startD = calS.get(Calendar.DATE);
        int startDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);

        calS.setTimeInMillis(endDate);
        int endY = calS.get(Calendar.YEAR);
        int endM = calS.get(Calendar.MONTH);
        //处理2011-01-10到2011-01-10，认为服务为一天
        int endD = calS.get(Calendar.DATE) + 1;
        int endDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);

        StringBuilder sBuilder = new StringBuilder();

        int lday = endD - startD;
        if (lday < 0) {
            endM = endM - 1;
            lday = startDayOfMonth + lday;
        }
        //处理天数问题，如：2011-01-01 到 2013-12-31  2年11个月31天     实际上就是3年
        if (lday == endDayOfMonth) {
            endM = endM + 1;
            lday = 0;
        }
        int mos = (endY - startY) * 12 + (endM - startM);
        int lyear = mos / 12;
        int lmonth = mos % 12;
        if (lyear > 0) {
            sBuilder.append(lyear + "年");
        }
        if (lmonth > 0) {
            sBuilder.append(lmonth + "月");
        }
        if (!hideDayIfMonth) {
            if (lday > 0) {
                sBuilder.append(lday + "天");
            }
        } else {
            if (lyear == 0 && lmonth == 0) {
                sBuilder.append(lday + "天");
            }
        }
        return sBuilder.toString();
    }

    public static int fieldDifference(long time1, long time2, int field) {
        if (time1 == time2) {
            return 0;
        } else if (time1 > time2) {
            // 确保time1比time2小
            return fieldDifference(time2, time1, field);
        }
        // 下面清除不要参与比较的内容
        Calendar cal1 = Calendar.getInstance();
        cal1.setLenient(false);
        cal1.setTimeInMillis(time1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setLenient(false);
        cal2.setTimeInMillis(time2);
        for (int x = 0; x < Calendar.FIELD_COUNT; x++) {
            if (x > field) {
                cal1.clear(x);
                cal2.clear(x);
            }
        }
        // 重新设定初始值
        time1 = cal1.getTimeInMillis();
        time2 = cal2.getTimeInMillis();
        long ms = 0;
        int min = 0;// 下限,从0开始
        int max = 1;// 每次所加的值,第一次加1
        while (true) {
            // 因为max的值都是相对time1而言,故每次都是从time1开始而不是ms
            cal1.setTimeInMillis(time1);
            cal1.add(field, max);// 将field对应的字段加上max的值
            ms = cal1.getTimeInMillis();
            if (ms == time2) {
                // 两个时间之间相差的值为max
                min = max;
                break;
            } else if (ms > time2) {
                // 超过后,则差值肯定小于max
                break;
            } else {
                // 仍然小于time2,继续增大max,直到ms>=time2为止
                max <<= 1;
            }
        }
        // 上面的操作中没有找到准确的值,接下来使用二分查找以准确找出差值
        while (max > min) {
            cal1.setTimeInMillis(time1);
            int t = (min + max) >>> 1;
            cal1.add(field, t);
            ms = cal1.getTimeInMillis();
            if (ms == time2) {
                min = t;
                break;
            } else if (ms > time2) {
                max = t;
            } else {
                min = t;
            }
        }
        return min;
    }

    /**
     * 获取过期时间(转换成字符串显示：MM-YY-DD)
     * date为空的时候是根据当前时间算的
     * term为-1L的时候返回-1L(业务中便是无限期)
     *
     * @param term 传入的月份
     * @return
     */
    public static Long getDeadline(Long data, int term) throws Exception {
        if (-1 != term) {

            if (null == data) {
                data = System.currentTimeMillis();
            }
            if (data.longValue() < System.currentTimeMillis()) {
                data = System.currentTimeMillis();
            }
            String dateStr = DateUtil.dateLongToStr(data, DateUtil.YEAR_MONTH_DATE.get().toString());

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, Integer.parseInt(dateStr.substring(0, 4)));
            cal.set(Calendar.MONTH, Integer.parseInt(dateStr.substring(5, 7)) - 1);
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateStr.substring(8, 10)));
            cal.add(Calendar.MONTH, term);
            data = DateUtil.convertDateStringToDateLong(DateUtil.YEAR_MONTH_DATE.get().toString(),
                    DateUtil.convertDateLongToDateString(DateUtil.YEAR_MONTH_DATE.get().toString(), cal.getTimeInMillis()));
            return data.longValue() + 86399000L;
        }

        return -1L;
    }

    public static String getTodayStr() {
        return getTodayStr(DateUtil.YEAR_MONTH_DATE.get());
    }

    public static String getTodayStr(String format) {
        Long date = System.currentTimeMillis();
        return DateUtil.convertDateLongToDateString(format, date);
    }

    public static String getTodayStr(SimpleDateFormat simpleDateFormat) {
        Long date = System.currentTimeMillis();
        return DateUtil.convertDateLongToDateString(simpleDateFormat, date);
    }

    public static String getYesterdayStr() {
        return getYesterdayStr(DateUtil.YEAR_MONTH_DATE.get().toString());
    }

    public static String getYesterdayStr(String format) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        Long date = c.getTimeInMillis();
        return DateUtil.convertDateLongToDateString(format, date);
    }

    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getYear(Long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(Long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getDay(Long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    //判断是否是当前日期
    public static boolean isCurrentTime(Long date) throws Exception {
        if (date == null) {
            return false;
        }
        date = DateUtil.convertDateDateShortToDateLong(DATE_STRING_FORMAT_DAY, new Date(date));
        Long currentDate = DateUtil.convertDateDateShortToDateLong(DATE_STRING_FORMAT_DAY, new Date(System.currentTimeMillis()));
        return (currentDate - date == 0);
    }


    public static boolean isSameDay(Long currentDate, Long compareDate) {
        try {
            if (currentDate == null || compareDate == null) {
                return false;
            }

            currentDate = DateUtil.convertDateDateShortToDateLong(DateUtil.DATE_STRING_FORMAT_DAY, new Date(currentDate));
            compareDate = DateUtil.convertDateDateShortToDateLong(DateUtil.DATE_STRING_FORMAT_DAY, new Date(compareDate));
            return (currentDate - compareDate == 0);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }


    public static Long get6clock(Long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 标准格式,例如： 2012-04-10
     *
     * @param sDate
     * @return
     */
    public static boolean isStandardDateFormat(String sDate) {
        if (StringUtils.isEmpty(sDate)) {
            return false;
        }
        String importedDatePattern = "^[1-9]\\d{3}\\-(0?[1-9]|1[0-2])\\-(0?[1-9]|1\\d|2\\d|3[0-1])$";
        Pattern pattern = Pattern.compile(importedDatePattern);
        Matcher match = pattern.matcher(sDate);
        return match.matches();
    }


    public static Long getLastWeekTime(Calendar calendar) {
        calendar.add(Calendar.DATE, -7);
        return calendar.getTimeInMillis();
    }

    public static Long getLastMonthTime(Calendar calendar) {
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTimeInMillis();
    }

    public static Long getLastThreeMonthTime(Calendar calendar) {
        calendar.add(Calendar.MONTH, -3);
        return calendar.getTimeInMillis();
    }

    public static Long getLastHalfYearTime(Calendar calendar) {
        calendar.add(Calendar.MONTH, -6);
        return calendar.getTimeInMillis();
    }

    public static Long getLastYearTime(Calendar calendar) {
        calendar.add(Calendar.YEAR, -1);
        return calendar.getTimeInMillis();
    }


    /**
     * 获取指定日期开始时间：00：00：00
     *
     * @return 当前天0时0分0秒
     * @throws
     */
    public static Long getStartOfDate(Long vDate, String formatStr) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(vDate));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = (Date) calendar.getTime().clone();
        DateFormat format = new SimpleDateFormat(formatStr);
        return convertDateStringToDateLong(formatStr, format.format(date));
    }

    /**
     * 获取指定日期结束时间23：59：59
     *
     * @return 当前天23：59：59
     * @throws
     */
    public static Long getEndOfDate(Long vDate, String formatStr) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(vDate));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date date = (Date) calendar.getTime().clone();
        DateFormat format = new SimpleDateFormat(formatStr);
        return convertDateStringToDateLong(formatStr, format.format(date));
    }

    public static Long getEndOfDate(Long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(date));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime().getTime();
    }

    public static int getWeekOfYear(Long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取当前月的总天数
     *
     * @return
     */
    public static int getDayOfMonth() {
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        return day;
    }

    public static List<String> getDays(String startDate, String endDate) {
        return getDates(startDate, endDate, null, null);
    }

    public static List<String> getDates(String startDate, String endDate, String inFormat, String outFormat) {
        List<String> days = new ArrayList<String>();// 日期集合
        if (StringUtils.isEmpty(inFormat)) {
            inFormat = DateUtil.YEAR_MONTH_DATE.get().toString();
        }
        if (StringUtils.isEmpty(outFormat)) {
            outFormat = DateUtil.YEAR_MONTH_DATE.get().toString();
        }
        long end = convertDateStringToDateLong(inFormat, endDate);
        long begin = convertDateStringToDateLong(inFormat, startDate);
        while (begin <= end) {
            Date day = new Date();
            day.setTime(begin);
            begin += 3600 * 24 * 1000;
            days.add(format(outFormat, day));
        }
        return days;
    }

    public static String getHourTime(int dayOffset, int hour) {
        Calendar cal = Calendar.getInstance();
        if (dayOffset != 0) {
            cal.add(Calendar.DATE, dayOffset);
        }
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
    }

    /**
     * 获得当前月最后N天 (date)
     */
    @SuppressWarnings("static-access")
    public static Date getLastDaysOfMonth(int n) {
        Calendar endCal = Calendar.getInstance();
        endCal.add(Calendar.MONTH, 1);
        endCal.set(Calendar.DATE, 1);
        endCal.add(Calendar.DATE, -n);
        endCal.set(Calendar.HOUR_OF_DAY, 0);
        endCal.set(Calendar.MINUTE, 0);
        endCal.set(Calendar.SECOND, 0);
        endCal.set(Calendar.MILLISECOND, 0);
        return endCal.getTime();
    }

    /**
     * 时间毫秒数转多少天多少时多少分多少秒
     *
     * @param
     * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
     * @author fy.zhang
     */
    public static String formatDuring(long mss) {
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
        return days + "天" + hours + "小时" + minutes + "分钟"
                + seconds + "秒";
    }

    //获取特定日期
    public static Long pointFutureDay(Date date, String formatStr, String cat, Integer num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if ("month".equals(cat)) {
            cal.add(Calendar.MONTH, 1);
        }
        if ("year".equals(cat)) {
            cal.add(Calendar.YEAR, 1);
        }
        return cal.getTime().getTime();
    }

    /**
     * 订单超时时间28分钟
     */
    protected final static Integer ORDER_EXPIRE_TIME = 28 * 60 * 1000;
/*
    public static void main(String[] args) throws Exception {
        Date now = new Date();
        System.out.println(now.getTime());
        System.out.println(convertDateDateShortToDateLong(DateUtil.DATE_STRING_FORMAT_DAY_HOUR_MIN_SECOND,now));;
        String orderTime = "2018-04-06 20:06:29";
        long expireTimestamp = DateUtil.convertDateStringToDateLong(DATE_STRING_FORMAT_ALL,orderTime) + ORDER_EXPIRE_TIME;
        long nowTimestamp = System.currentTimeMillis() ;

        System.out.println(expireTimestamp);

        System.out.println(nowTimestamp);

        System.out.println(expireTimestamp > nowTimestamp);


//        System.out.println(DateUtil.getStartTimeOfYesterday());
//        System.out.println(DateUtil.convertDateLongToDateString(DateUtil.DATE_STRING_FORMAT_DAY_HOUR_MIN_SECOND,DateUtil.getStartTimeOfYesterday()));
//
//        System.out.println(DateUtil.convertDateLongToDateString(DateUtil.DATE_STRING_FORMAT_DAY_HOUR_MIN_SECOND,System.currentTimeMillis()));

//        try {
//            System.out.println(""+fullFormatNow());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        System.out.println(getTodayStr(DATE_STRING_FORMAT_DAY));
//        System.out.println(compareTwoDate(getTodayStr(DATE_STRING_FORMAT_DAY),"2018-01-24"));
//        System.out.println(compareTwoDate(getTodayStr(DATE_STRING_FORMAT_DAY),"2018-01-26"));
//        System.out.println(getWeekOfDate(new Date()));
//        System.out.println(new Date(1512618354518L));
//        System.out.println(DateUtil.getWeekOfDate(DateUtil.getDateFromString(DateUtil.DATE_STRING_FORMAT_DAY,"2018-01-25")));
//        System.out.println(DateUtil.getWeekOfDate(DateUtil.getDateFromString(DateUtil.DATE_STRING_FORMAT_DAY,"2018-01-23")));
//        System.out.println(DateUtil.format(
//                DateUtil.DATE_FORMAT_TIME, new Date()));
    }
*/

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * @param date
     * @return 获取当前是星期几;
     */
    public static String getWeekOfMonth(Long date) {
        String strWeek = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        int dw = calendar.get(Calendar.DAY_OF_WEEK);
        if (dw == 1) {
            strWeek = "周日";
        } else if (dw == 2) {
            strWeek = "周一";
        } else if (dw == 3) {
            strWeek = "周二";
        } else if (dw == 4) {
            strWeek = "周三";
        } else if (dw == 5) {
            strWeek = "周四";
        } else if (dw == 6) {
            strWeek = "周五";
        } else if (dw == 7) {
            strWeek = "周六";
        }
        return strWeek;
    }

    /**
     * 获取 date 的 第 someDay 后 的具体日期
     * 格式  yyyy-MM-dd
     *
     * @param date
     * @param someDay
     * @return
     */
    public static String getNextSomeDay(Date date, int someDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, someDay);
        date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 格式化时间yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String parseDateTime(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat df = new SimpleDateFormat(DATE_STRING_FORMAT_ALL);
        return df.format(date);
    }

    /**
     * -1 begin<end; 1 begin>end
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Integer compareTwoDate(String beginDate, String endDate) {
        return compareDateTime(beginDate, endDate, DateUtil.YEAR_MONTH_DATE.get().toString());
    }

    /**
     * -1 begin<end; 1 begin>end; 0 begin=end
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static Integer compareTwoTime(String beginTime, String endTime) {
        return compareDateTime(beginTime, endTime, DATE_FORMAT_TIME);
    }

    /**
     * 比较日期或者时间
     *
     * @param beginDateTime
     * @param endDateTime
     * @param format
     * @return
     */
    public static Integer compareDateTime(String beginDateTime, String endDateTime, String format) {
        /**
         * 默认0,
         */
        Integer result = NumberUtils.INTEGER_ZERO;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date bt = sdf.parse(beginDateTime);
            Date et = sdf.parse(endDateTime);
            if (bt.equals(et)) {
                result = NumberUtils.INTEGER_ZERO;
            }
            if (bt.before(et)) {
                //表示bt小于et
                result = NumberUtils.INTEGER_MINUS_ONE;
            }
            if (bt.after(et)) {
                result = NumberUtils.INTEGER_ONE;
            }
        } catch (Exception e) {
            LOG.error("系统异常");
        }
        return result;
    }

    /**
     * 获取两个时间段内整数个小时数
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static Integer calculateTwoTimeCount(String beginTime, String endTime) {
        Integer hours = 0;
        Date start = DateUtil.getDateFromString(DateUtil.DATE_FORMAT_TIME, beginTime);
        Date end = DateUtil.getDateFromString(DateUtil.DATE_FORMAT_TIME, endTime);
        start = hoursAdd(start);
        for (; start.compareTo(end) <= 0; start = hoursAdd(start)) {
            hours++;
        }
        return hours;
    }

    /**
     * 小时数+1
     *
     * @param today
     * @return
     */
    public static Date hoursAdd(Date today) {
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.HOUR_OF_DAY, 1);

        return c.getTime();
    }


    /*************************************************************************************************/
    /**
     *
     */
    private static DateFormat yyyyMMddTHHmmssSSSXXX = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    private static SimpleDateFormat EEEMMMddHHmmssZyyyy = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
    private static DateFormat yMdHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formatChYmd(String str) throws ParseException {
        if (str == null || "".equals(str)) {
            return null;
        }
        if (str.length() == 10) {
            return str.substring(0, 4) + " 年 " + str.substring(5, 7) + " 月 " + str.substring(8) + " 日 ";
        }
        if (str.length() == 7) {
            return str.substring(0, 4) + "年" + str.substring(5, 7) + "月";
        }
        if (str.length() == 4) {
            return str.substring(0, 4) + "年";
        }
        return str + "月";
    }

    public static String dealDateFormat(String oldDateStr) throws ParseException {
        //此格式只有  jdk 1.7才支持  yyyy-MM-dd'T'HH:mm:ss.SSSXXX
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");  //yyyy-MM-dd'T'HH:mm:ss.SSSZ
        Date date = yyyyMMddTHHmmssSSSXXX.parse(oldDateStr);
//        SimpleDateFormat df1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
        Date date1 = EEEMMMddHHmmssZyyyy.parse(date.toString());
//        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//  Date date3 =  df2.parse(date1.toString());
        return yMdHHmmss.format(date1);
    }

    public static String dealDateByFormat(String oldDateStr, String dateFormatStr) throws ParseException {
        //此格式只有  jdk 1.7才支持  yyyy-MM-dd'T'HH:mm:ss.SSSXXX
        Date date = yyyyMMddTHHmmssSSSXXX.parse(oldDateStr);
        Date date1 = EEEMMMddHHmmssZyyyy.parse(date.toString());
        DateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
        return dateFormat.format(date1);
    }


    public static String formatDate(String str) {
        String sfstr = "";
        if (null != str) {
            try {
                SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
                sfstr = sf2.format(sf1.parse(str));
            } catch (ParseException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return sfstr;
    }


    public static Date longToDate(Long date) {
        if (date != null) {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(date);
            return c.getTime();
        } else {
            return null;
        }

    }

    public static Date strToDateLong(String strDate) {
        if (null != strDate) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    public static int monthDiff(String headMonth, String endMonth) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(sdf.parse(headMonth));
        aft.setTime(sdf.parse(endMonth));
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        return Math.abs(month + result);
    }

    /**
     * 获取月份最后一天DATE
     *
     * @param basicMonth yyyy-MM型
     * @return 月份最后一天 yyyy-MM-dd型
     * @throws ParseException
     */
    public static Date lastDayDateOfMonth(String basicMonth) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(basicMonth));
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DATE));
        return c.getTime();
    }

    /**
     * date2翻译为多少天
     *
     * @return
     */
    public static int dateDays(String date) throws Exception {
        return differentDays(yMdHHmmss.parse(date), new Date());
    }

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Integer differentDays(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return null;
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else    //不同年
        {
            //System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2 - day1;
        }
    }

    public static String formatDateYM(Date date) {
        if (date != null) {
            return formatDate(date, "yyyy-MM");
        } else {
            return null;
        }
    }

    public static String formatDateYMD(Date date) {
        if (date != null) {
            return formatDate(date, "yyyyMMdd");
        } else {
            return null;
        }
    }

    public static String formatDateMD(Date date) {
        if (date != null) {
            return formatDate(date, "yyMMdd");
        } else {
            return null;
        }
    }

    public static String formatDateYDMHS(Date date) {
        if (date != null) {
            return formatDate(date, "yyyyMMddHHmmss");
        } else {
            return null;
        }
    }

    //获得date下一天的凌晨
    public static Date getTimeOf12(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    //比较日期大小
    public static int compareDate(Date dt1, Date dt2) {
        //DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            if (dt1.getTime() > dt2.getTime()) {
                //System.out.println("dt1 在dt2后");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                //System.out.println("dt1在dt2前");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            LOG.error(exception.getMessage(), exception);
        }
        return 0;
    }


    /**
     * 格式化日期：yyyy-MM-dd HH:mm
     *
     * @param date
     * @return
     */
    public static String formatDate2YYYYMMDDHHMISS(Date date) {
        if (date != null) {
            return formatDate(date, "yyyy-MM-dd HH:mm:ss");
        } else {
            return null;
        }
    }

    public static Date addSecends(Date basicDate, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(basicDate);
        calendar.add(Calendar.SECOND, n);
        return calendar.getTime();
    }


    public static Date nDaysAfterOneDate(Date basicDate, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(basicDate);
        calendar.add(Calendar.DAY_OF_MONTH, n);
        return calendar.getTime();
    }

    public static Date nMonthAfterOneDate(Date basicDate, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(basicDate);
        calendar.add(Calendar.MONTH, n);
        return calendar.getTime();
    }

    public static String nYearAfterOneDate2Str(Date basicDate, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(basicDate);
        calendar.add(Calendar.YEAR, n);
        return formatDate(calendar.getTime());
    }

    public static Date nYearAfterOneDate(Date basicDate, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(basicDate);
        calendar.add(Calendar.YEAR, n);
        return calendar.getTime();
    }

    public static String nMonthAfterOneMonthStr(Date basicDate, int n) {
        Date dt = nMonthAfterOneDate(basicDate, n);
        return new SimpleDateFormat("yyyy-MM").format(dt);
    }

    public static Date nMonthAfterOneMonth(Date basicDate, int n) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(basicDate);
        cale.add(Calendar.MONTH, n);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return cale.getTime();
    }

    /**
     * 获取月份第一天日期
     *
     * @param basicMonth yyyy-MM型
     * @return 月份第一天
     */
    public static String firstDayOfMonth(String basicMonth) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(basicMonth));
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DATE));
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    /**
     * 获取月份最后一天日期
     *
     * @param basicMonth yyyy-MM型
     * @return 月份最后一天 yyyy-MM-dd型
     * @throws ParseException
     */
    public static String lastDayOfMonth(String basicMonth) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(basicMonth));
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DATE));
        return new SimpleDateFormat("yyyy-MM-dd ").format(c.getTime());
    }


    /**
     * 获取当周第一天日期
     *
     * @return 月份第一天
     */
    public static String firstDayOfWeek() throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

    /**
     * 获取当周最后一天日期
     *
     * @return 当周最后一天 yyyy-MM-dd型
     * @throws ParseException
     */
    public static String lastDayOfWeek() throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); //获取本周一的日期
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }


    /**
     * 获取当年的第一天
     *
     * @return
     */
    public static String getCurrYearFirst() throws ParseException {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return firstDayOfYear(currentYear);
    }

    /**
     * 获取当年的最后一天
     *
     * @return
     */
    public static String getCurrYearLast() throws ParseException {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return lastDayOfYear(currentYear);
    }

    /**
     * 获取某年第一天日期
     *
     * @return 月份第一天
     */
    public static String firstDayOfYear(int year) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    /**
     * 获取某年最后一天日期
     *
     * @return 当周最后一天 yyyy-MM-dd型
     * @throws ParseException
     */
    public static String lastDayOfYear(int year) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }


    public static String formatDate(Date date, String reg) {
        if (date != null) {
            return new SimpleDateFormat(reg).format(date);
        } else {
            return null;
        }

    }

    /**
     * 格式化日期，yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        if (date != null) {
            return formatDate(date, "yyyy-MM-dd");
        } else {
            return null;
        }
    }

    /**
     * 格式化日期：yyyy-MM-dd HH:mm
     *
     * @param date
     * @return
     */
    public static String formatDate2YYYYMMDDHHMI(Date date) {
        if (date != null) {
            return formatDate(date, "yyyy-MM-dd HH:mm");
        } else {
            return null;
        }
    }

    /**
     * 格式化日期：yyyy-MM-dd HH:mm
     * 将double类型数据变成时间
     *
     * @param time
     * @return
     */
    public static String formatDateByTime(Double time) {
        if (time != null) {
            Long dateRe = new Double(time).longValue();
            Date date = new Date(dateRe);
            return formatDate(date, "yyyy-MM-dd HH:mm");
        } else {
            return null;
        }
    }

    /**
     * 格式化日期，yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String formatDateYYYYMMDDHHMISS(Date date) {
        if (date != null) {
            return formatDate(date, "yyyy-MM-dd HH:mm:ss");
        } else {
            return null;
        }
    }


    public static Date parseDate(String date, String reg) throws ParseException {
        return new SimpleDateFormat(reg).parse(date);
    }

    public static Date parseDate(String date) throws ParseException {
        return parseDate(date, "yyyy-MM-dd");
    }

    /**
     * 将时间戳转换为日期
     *
     * @param unixTimestamp
     * @return
     * @throws ParseException
     */
    public static Date parseUnixTime(Long unixTimestamp) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(sdf.format(unixTimestamp));
    }

    /**
     * 将时间戳转换为日期
     *
     * @param unixTimestamp
     * @return
     * @throws ParseException
     */
    public static String parseUnixTimeByLong(Long unixTimestamp) {
        Date date = new Date(unixTimestamp);
        if (date != null) {
            return formatDate(date, "yyyy-MM-dd HH:mm");
        } else {
            return null;
        }
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(betweenDays));
    }

    /**
     * 字符串的日期格式的计算
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(betweenDays));
    }

    /**
     *
     */
    public static String orderTimeCompare(Date date) {
        String result = "";
        if (date == null) {
            return result;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH时mm分");
            long day = daysBetween(date, new Date());
            if (day > 7) {
                result = sdf.format(date);
            } else if (day < 1) {
                int hours = hourBetween(date, new Date());
                if (hours < 1) {
                    int minutes = minuteBetween(date, new Date());
                    if (minutes < 1) {
                        minutes = 1;
                    }
                    result = minutes + "分钟前";
                } else {
                    result = hours + "小时前";
                }
            } else {
                result = day + "天前";
            }
        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 1小时内：n分钟前
     * 24小时内：时：分
     * 超过24小时：月-日
     * 超过1年：年-月-日
     *
     * @param date
     * @return
     */
    public static String orderTimeCompareV2(Date date) {
        String result = "";
        if (date == null) {
            return result;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            long day = daysBetween(date, new Date());
            if (day > 365) {
                result = sdf.format(date);
            } else if (day < 1) {
                int hours = hourBetween(date, new Date());
                if (hours < 1) {
                    int minutes = minuteBetween(date, new Date());
                    if (minutes < 1) {
                        minutes = 1;
                    }
                    result = minutes + "分钟前";
                } else {
                    result = hours + "小时前";
                }
            } else {
                sdf = new SimpleDateFormat("MM-dd");
                result = sdf.format(date);
            }
        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 计算两个日期之间相差的小时数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差小时数
     * @throws ParseException
     */
    public static int hourBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        Long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        Long time2 = cal.getTimeInMillis();
        Long betweenHours = (time2 - time1) / (1000 * 3600);
        return betweenHours.intValue();
    }

    /**
     * 计算两个日期之间相差的分钟数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差分钟数
     * @throws ParseException
     */
    public static int minuteBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        Long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        Long time2 = cal.getTimeInMillis();
        Long betweenMins = (time2 - time1) / (1000 * 60);
        return betweenMins.intValue();
    }

    /**
     * 计算两个时间戳之间相差的分钟数
     *
     * @param now
     * @param unixTimestamp
     * @return
     */
    public static int timeStampMinuteCompare(Long now, Long unixTimestamp) {
        if (now == null || unixTimestamp == null) {
            return -1;
        }
        Long betweenMins = (now - unixTimestamp) / (1000 * 60);
        return betweenMins.intValue();
    }

    public static String timeMillinsToStr(Long timeMillins) {
        Date date = new Date(timeMillins);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd HH:mm");
        return sdf.format(date);
    }

    public static Date formatGmtToLocalString(String dateStr) throws Exception {
        dateStr = dateStr.replace("Z", " UTC");//注意是空格+UTC
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
        return sdf.parse(dateStr);
    }



    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH,2-1);
        calendar.set(Calendar.DAY_OF_MONTH,31);


        int MONTH = getMonth(calendar.getTimeInMillis());
        System.out.println(MONTH);
        System.out.println(formatDateYMD(calendar.getTime()));
    }
}
