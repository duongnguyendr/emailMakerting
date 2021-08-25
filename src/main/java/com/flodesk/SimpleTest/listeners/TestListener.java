package com.flodesk.SimpleTest.listeners;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.flodesk.SimpleTest.common.Common;

public class TestListener implements ITestListener{
	@Override
	public void onTestStart(ITestResult result) {
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	}

	@Override
	public void onTestFailure(ITestResult result) {
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}

	private static Logger logger = Logger.getLogger(TestListener.class.getSimpleName());
	
	public static File sHtmlReports;
    public static File sPdfReports;
//    public static GeneratePDF pdf;
    public static Map<Integer, List<String>> excelDatas;
    public static int failCount;
    
	public static void initReportsFolder() {
        try {
            System.out.println("***** Start initialize Report folders *****\n");
            sHtmlReports = new File(Common.sDirPath + "/Reports/HTMLReports");
            sPdfReports = new File(Common.sDirPath + "/Reports/PDFReports");
            excelDatas = new HashMap<>();
            if (!sHtmlReports.exists()) {
                FileUtils.forceMkdir(sHtmlReports);
            }
            if (!sPdfReports.exists()) {
                FileUtils.forceMkdir(sPdfReports);
            }
        } catch (Exception e) {
            logger.info("***** Unable to create reportLB folders: " + e.getMessage());
        }
    }
}
