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
        object.put("full-stacktrace", getStackTrace(testObject.toString()));
        object.put("screenshot", getScreenshot());


        object.put("methods", getMethodArray(testObject.toString()));

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

        return testStartedAt;
    }

    public String getTestFinishedAt(String object) {

        String testFinishedAt = JsonPath.parse(object).read("$.finished-at");


        return testFinishedAt;
    }

    public String getTestDuration(String object) {
        String testDuration = JsonPath.parse(object).read("$.duration-ms");
        return testDuration;
    }

    public String getTestName(String object) {

        String testName = JsonPath.parse(object).read("$.name");

        return testName;
    }

    public String getFailureMessage() {
        return "Android";
    }

    public String getStackTrace(String testObject) {

String fullStackTrace = "";
      if(getFinalTestResult(testObject).equalsIgnoreCase("FAIL"));
        {
             JSONArray methodList = getMethodArray(testObject);


            net.minidev.json.JSONArray array =            JsonPath.parse(methodList.toString()).read("$.[*].exception.full-stacktrace");

            JSONArray finalStackTracs = new JSONArray(array.toString());
           fullStackTrace = finalStackTracs.get(0).toString();


        }


        return fullStackTrace;
    }

    public String getScreenshot() {
        return "Android";
    }


    public JSONArray getMethodArray(String singleTestObject) {

        net.minidev.json.JSONArray list = JsonPath.parse(singleTestObject).read("$.class.test-method");

        JSONArray methodList = new JSONArray(list.toString());
        return methodList;
    }


    public JSONObject getSingleMethodObject(Object testObject) {
        JSONObject methodObject = new JSONObject();

        methodObject.put("is-config", getIsConfig(methodObject.toString()));
        methodObject.put("name", getMethodName(methodObject.toString()));
        methodObject.put("status", getMethodStatus(methodObject.toString()));
        methodObject.put("started-at", getMethodStartedAt(methodObject.toString()));
        methodObject.put("duration-ms", getDurationAt(methodObject.toString()));
        methodObject.put("finished-at", getMethodFinished(methodObject.toString()));
        return methodObject;
    }

    public boolean getIsConfig(String methodObject)
    {
        return false;
    }

    public String getMethodName(String methodObject)
    {
        return "";
    }

    public String getMethodStatus(String methodObject)
    {
        return "";
    }

    public String getMethodStartedAt(String methodObject)
    {
        return "";
    }

    public String getMethodFinished(String methodObject)
    {
        return "";
    }

    public String getDurationAt(String methodObject)
    {
        return "";
    }



}
