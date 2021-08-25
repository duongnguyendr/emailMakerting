package com.flodesk.SimpleTest.listeners;

import static com.flodesk.SimpleTest.base.BaseTest.setUpDriverExecutable;

import org.apache.log4j.Logger;
import org.testng.IExecutionListener;

import com.flodesk.SimpleTest.common.Common;
import com.flodesk.SimpleTest.reports.GenerateReport;

public class TestNGListener extends TestListener implements IExecutionListener {
	private static Logger logger = Logger.getLogger(TestNGListener.class.getSimpleName());

	@Override
	public void onExecutionStart() {
		logger.info("***** TestNG is staring the execution *****");
		initReportsFolder();
		setUpDriverExecutable();
	}

	@Override
	public void onExecutionFinish() {
		String timeStamp = Common.GetTimeStampValue();
		GenerateReport.GenerateMasterthoughtReport(timeStamp);
	}

}
