package com.easy.make.tenantmaker.utils;

import com.easy.make.tenantmaker.TenantApplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by ravi on 05/07/16.
 */
public class DateTimeUtils {

    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", TenantApplication.getInstance().getLocale());
    public static SimpleDateFormat dateFormatddMMMMyyyy = new SimpleDateFormat("dd MMMM yyyy", TenantApplication.getInstance().getLocale());
    public static final SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", TenantApplication.getInstance().getLocale());

    public static String getCurrentDateInUTC()
    {
        simpleDateFormatWithTime.setTimeZone(TimeZone.getTimeZone("UTC"));
        return  simpleDateFormatWithTime.format(new Date());
    }
}
