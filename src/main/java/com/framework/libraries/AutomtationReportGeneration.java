package com.framework.libraries;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

/**
 * @author Rupak Mansingh This class executed at the end of execution and generates
 * report It merge individual report into sigle report and generate very
 * good html report After execution go to target/cucumber-html-reports
 * and open overview-feature.html file in browser
 */
public class AutomtationReportGeneration {
    private static final LogUtils LOGGER = new LogUtils(AutomtationReportGeneration.class);
    @SuppressWarnings("unused")
    public static void main(String arg[]) {

        File reportOutputDirectory = new File("target");
        File generatedReport = new File("./target/cucumber-parallel/");
        File[] fileList = generatedReport.listFiles();
        List<String> jsonFiles = new ArrayList<>();
        if (fileList.length != 0) {
            for (File file : fileList) {
                jsonFiles.add(file.getPath());
            }
        } else {
            LOGGER.info("Report is not generated");
        }

        String projectName = "Automation Test";

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        Reportable result = reportBuilder.generateReports();
    }
}
