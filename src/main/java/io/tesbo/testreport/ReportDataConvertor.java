package io.tesbo.testreport;


import com.jayway.jsonpath.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.UUID;

public class ReportDataConvertor {


    JSONObject reportData;

    ReportDataConvertor(JSONObject reportData) {
        this.reportData = reportData;
    }


    public void PrepareFinalReport() {
        JSONObject report = new JSONObject();

        JSONObject suite = new JSONObject();

        suite.put("started-at", getStartedAt());
        suite.put("name", getSuiteName());
        suite.put("finished-at", getFinishedAt());
        suite.put("duration-ms", getDuration());
        suite.put("tests", getAvailableTestList());
        report.put("suite", suite);

        System.out.println(report);
    }

    public String getStartedAt() {
        String startedAt = JsonPath.parse(reportData.toString()).read("$.testng-results.suite.started-at");
        return startedAt;
    }

    public String getFinishedAt() {
        String startedAt = JsonPath.parse(reportData.toString()).read("$.testng-results.suite.finished-at");
        return startedAt;
    }

    public int getDuration() {
        int startedAt = JsonPath.parse(reportData.toString()).read("$.testng-results.suite.duration-ms");
        return startedAt;
    }

    public String getSuiteName() {
        String startedAt = JsonPath.parse(reportData.toString()).read("$.testng-results.suite.name");
        return startedAt;
    }

    public JSONArray getTestList() {
        net.minidev.json.JSONArray list = JsonPath.parse(reportData.toString()).read("$.testng-results.suite.test");
        JSONArray testList = new JSONArray(list.toString());
        return testList;
    }

    public JSONArray getAvailableTestList() {

        JSONArray finalTestList = new JSONArray();
        JSONArray getOldList = getTestList();

        for (Object singleTest : getOldList) {
            finalTestList.put(getSingleTestObject(singleTest));
        }
        return finalTestList;

    }

    public JSONObject getSingleTestObject(Object testObject) {

        JSONObject object = new JSONObject();

        String singleTestObject = testObject.toString();

        object.put("testID", UUID.randomUUID().toString());
        object.put("moduleName", getModuleName(testObject.toString()));
        object.put("final-test-status", getFinalTestResult(singleTestObject));
        object.put("platformName", getPlatForm());
        object.put("platformVersion", getPlatVersion());
        object.put("browser", getBrowser());
        object.put("browserVersion", getBrowserVersion());
        object.put("deviceName", getDeviceName());
        object.put("started-at", getTestStartedAt(testObject.toString()));
        object.put("finished-at", getFinishedAt());
        object.put("duration-ms", getFinishedAt());
        object.put("name", getTestName(singleTestObject));
        object.put("failureMessage", getFailureMessage());
        object.put("full-stacktrace", getStackTrace());
        object.put("screenshot", getScreenshot());


        object.put("methods", getMethodArray((JSONObject) testObject));

        return object;
    }

    /*For the Module name, we need to consider the folder name of the test or class name*/
    public String getModuleName(String object) {
        String moduleName = "Temp";

        String ClassName = JsonPath.parse(object).read("$.class.name");

        String[] nameSplitList = ClassName.split("\\.");

        String folderName = nameSplitList[nameSplitList.length-2];
        return folderName;
    }


    /**
     * @param object
     * @return final test result
     */
    public String getFinalTestResult(String object) {
        net.minidev.json.JSONArray list = JsonPath.parse(object).read("$.class.test-method[*].status");
        JSONArray testList = new JSONArray(list.toString());

        String finalTestResult = "PASS";

        boolean isFailAvailable = false;
        for (Object singleMethodResult : testList) {

            if (singleMethodResult.toString().equalsIgnoreCase("FAIL")) {
                isFailAvailable = true;
            }

        }

        if (isFailAvailable) {
            finalTestResult = "FAIL";
        }

        return finalTestResult;
    }

    public String getPlatForm() {
        return "Win1-";
    }

    public String getPlatVersion() {
        return "Win1-";
    }

    public String getBrowser() {
        return "Chrome";
    }

    public String getBrowserVersion() {
        return "104";
    }

    public String getDeviceName() {
        return "Android";
    }

    public String getTestStartedAt(String object) {


        String testStartedAt = JsonPath.parse(object).read("$.started-at");

        return "Android";
    }

    public String getTestFinishedAt(String object) {

        String testFinishedAt = JsonPath.parse(object).read("$.finished-at");


        return "Android";
    }

    public String getTestDuration(String object) {
        String testDuration = JsonPath.parse(object).read("$.duration-ms");
        return "Android";
    }

    public String getTestName(String object) {

        String testName = JsonPath.parse(object).read("$.name");

        return testName;
    }

    public String getFailureMessage() {
        return "Android";
    }

    public String getStackTrace() {
        return "Android";
    }

    public String getScreenshot() {
        return "Android";
    }


    public JSONArray getMethodArray(JSONObject singleTestObject) {
        JSONArray methodList = new JSONArray();


        return methodList;
    }


    public JSONObject getSingleMethodObject(Object testObject) {
        JSONObject methodObject = new JSONObject();

        methodObject.put("is-config", "");
        methodObject.put("name", "");
        methodObject.put("status", "");
        methodObject.put("started-at", "");
        methodObject.put("duration-ms", "");
        methodObject.put("finished-at", "");
        return methodObject;
    }

}
