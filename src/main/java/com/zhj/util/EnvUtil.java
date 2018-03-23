package com.zhj.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhj on 2018/3/23.
 */

public class EnvUtil {


    public static boolean isWindows() {
        return isWindows;
    }
    private static final boolean isWindows = System.getProperty("os.name") != null
            && System.getProperty("os.name").toLowerCase().contains("windows");

    private static final String OS = System.getProperty("os.name").toLowerCase();

    public static boolean isMacOS(){
        return OS != null && OS.indexOf("mac")>=0 && OS.indexOf("os")>0;
    }

    static final Logger logger = LoggerFactory.getLogger(EnvUtil.class);
}