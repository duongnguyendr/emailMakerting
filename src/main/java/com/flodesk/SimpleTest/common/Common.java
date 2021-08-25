package com.flodesk.SimpleTest.common;

import java.text.SimpleDateFormat;
import java.util.Date;

//import org.apache.log4j.Logger;

public class Common {
//	private static Logger logger = Logger.getLogger(Common.class.getSimpleName());
    public static String sDirPath = System.getProperty("user.dir");
    public static String sUserPath = System.getProperty("user.home");
    public static String sBrowser = "Chrome";
    
    public static String GetTimeStampValue() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss_SSS");
        String systime = sdf.format(new Date());
        systime = systime.replace(":", "");
        systime = systime.replace("-", "");
        return systime;
    }
    
    public static void waitSomeSecond(int seconds) {
    	try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
