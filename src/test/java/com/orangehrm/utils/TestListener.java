package com.orangehrm.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestNG Listener for Extent Reports
 * Implements ITestListener to capture test execution details
 */
public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("=== Test Suite Execution Started: " + context.getName() + " ===");
        ExtentReportManager.initExtentReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("=== Test Case Started: " + result.getName() + " ===");
        ExtentReportManager.createTest(result.getName(), result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("=== Test Case Passed: " + result.getName() + " ===");
        ExtentTest extentTest = ExtentReportManager.getExtentTest();
        if (extentTest != null) {
            extentTest.log(Status.PASS, result.getName() + " test passed successfully");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("=== Test Case Failed: " + result.getName() + " ===");
        ExtentTest extentTest = ExtentReportManager.getExtentTest();
        if (extentTest != null) {
            extentTest.log(Status.FAIL, "Test Failed: " + result.getName());
            extentTest.log(Status.FAIL, "Failure Reason: " + result.getThrowable().getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("=== Test Case Skipped: " + result.getName() + " ===");
        ExtentTest extentTest = ExtentReportManager.getExtentTest();
        if (extentTest != null) {
            extentTest.log(Status.SKIP, result.getName() + " test was skipped");
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("=== Test Suite Execution Finished: " + context.getName() + " ===");
        ExtentReportManager.flushExtentReports();
    }

}
