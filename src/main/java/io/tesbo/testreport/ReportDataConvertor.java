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

        object.put("testID", UUID.randomUUID().toString());
        object.put("moduleName", getModuleName());
        object.put("final-test-status", getFinalTestResult());
        object.put("platformName", getPlatForm());
        object.put("platformVersion", getPlatVersion());
        object.put("browser", getBrowser());
        object.put("browserVersion", getBrowserVersion());
        object.put("deviceName", getDeviceName());
        object.put("started-at", getTestStartedAt());
        object.put("finished-at", getFinishedAt());
        object.put("duration-ms", getFinishedAt());
        object.put("name", getTestName());
        object.put("failureMessage", getFailureMessage());
        object.put("full-stacktrace", getStackTrace());
        object.put("screenshot", getScreenshot());


        object.put("methods", getMethodArray((JSONObject) testObject));

        return object;
    }


    /*For the Module name, we need to consider the folder name of the test or class name*/
    public String getModuleName() {
        String moduleName = "Temp";

        return moduleName;
    }

    public String getFinalTestResult() {
        return "pass";
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

    public String getTestStartedAt() {
        return "Android";
    }

    public String getTestFinishedAt() {
        return "Android";
    }

    public String getTestDuration() {
        return "Android";
    }

    public String getTestName() {
        return "Android";
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
