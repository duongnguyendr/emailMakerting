package com.flodesk.SimpleTest.reports;

import com.flodesk.SimpleTest.common.Common;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duong.nguyen
 */
public class GenerateReport {
    private static Logger logger = Logger.getLogger(GenerateReport.class.getSimpleName());

	public static Reportable GenerateMasterthoughtReport(String sExecuteTime) {
        Reportable result = null;
        try {
            logger.info("START");
            File reportOutputDirectory = new File("Reports/HTMLReports/Report_" + sExecuteTime);
            List<String> jsonFiles = new ArrayList<>();
            jsonFiles.add("./target/cucumber-report.json");
            //jsonFiles.add("cucumber-report-2.json");

            String projectName = "Automation Report";

            Configuration configuration = createConfiguration(reportOutputDirectory, projectName);
            ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
            result = reportBuilder.generateReports();
            logger.info("DONE REPORT");
            // and here validate 'result' to decide what to do
            // if report has failed features, undefined steps etc
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static Configuration createConfiguration(File reportOutputDirectory, String projectName){
        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        String buildNumber = "1";
        configuration.setBuildNumber(buildNumber);

        // addidtional metadata presented on main page
        configuration.addClassifications("Platform", "Windows");
        configuration.addClassifications("Browser", Common.sBrowser);
        configuration.addClassifications("Branch", "release/1.0");
        configuration.addClassifications("Created by","AUTOMATION TEAM");

        return configuration;
    }
}