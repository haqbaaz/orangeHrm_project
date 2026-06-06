package com.orangehrm.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Extent Report Manager Class
 * Manages ExtentReports for test execution reporting
 */
public class ExtentReportManager {

    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    /**
     * Initialize Extent Reports
     */
    public static ExtentReports initExtentReports() {
        String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
        String reportDir = System.getProperty("user.dir") + "/test-output";
        String reportPath = reportDir + "/ExtentReports_" + timestamp + ".html";

        // Ensure test-output directory exists
        File directory = new File(reportDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("OrangeHRM Test Report");
        sparkReporter.config().setReportName("OrangeHRM Login Test Automation Report");

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReports.setSystemInfo("User", System.getProperty("user.name"));
        extentReports.setSystemInfo("Application", "OrangeHRM");

        return extentReports;
    }

    /**
     * Create a test case
     */
    public static ExtentTest createTest(String testName, String description) {
        extentTest = extentReports.createTest(testName, description);
        return extentTest;
    }

    /**
     * Get the current ExtentTest instance
     */
    public static ExtentTest getExtentTest() {
        return extentTest;
    }

    /**
     * Flush the extent reports
     */
    public static void flushExtentReports() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }

}
