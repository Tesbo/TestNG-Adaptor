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

    public JSONObject PrepareFinalReport() {
        JSONObject report = new JSONObject();
        JSONObject suite = new JSONObject();
        suite.put("started-at", getStartedAt());
        suite.put("name", getSuiteName());
        suite.put("finished-at", getFinishedAt());
        suite.put("duration-ms", getDuration());
        suite.put("tests", getAvailableTestList());
        report.put("Suite", suite);

        return report;
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
        object.put("moduleName", getModuleName(singleTestObject));
        object.put("final-test-status", getFinalTestResult(singleTestObject));
        object.put("platformName", getPlatForm());
        object.put("platformVersion", getPlatVersion());
        object.put("browser", getBrowser());
        object.put("browserVersion", getBrowserVersion());
        object.put("deviceName", getDeviceName());
        object.put("started-at", getTestStartedAt(singleTestObject));
        object.put("finished-at", getTestFinishedAt(singleTestObject));
        object.put("duration-ms", getTestDuration(singleTestObject));
        object.put("name", getTestName(singleTestObject));
        object.put("failureMessage", getFailureMessage(singleTestObject));
        object.put("full-stacktrace", getStackTrace(singleTestObject));
        object.put("screenshot", getScreenshot());


        object.put("methods", getMethodArray(testObject.toString()));

        return object;
    }


    public String getModuleName(String object) {


        String ClassName = JsonPath.parse(object).read("$.class.name");

        String[] nameSplitList = ClassName.split("\\.");

        String folderName = nameSplitList[nameSplitList.length - 2];
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

    public int getTestDuration(String object) {
        int testDuration = JsonPath.parse(object).read("$.duration-ms");
        return testDuration;
    }

    public String getTestName(String object) {

        String testName = JsonPath.parse(object).read("$.name");

        return testName;
    }


    public String getFailureMessage(String testObject) {

        String fullStackTrace = "";

        if (getFinalTestResult(testObject).equalsIgnoreCase("FAIL")) {

            net.minidev.json.JSONArray list = JsonPath.parse(testObject).read("$.class.test-method");


            JSONArray methodList = new JSONArray(list);


            for (Object singleMethodResult : methodList) {
                try {
                    fullStackTrace = JsonPath.parse(singleMethodResult.toString()).read("$.exception.message");

                    if (!fullStackTrace.equalsIgnoreCase("")) {
                        break;
                    }
                } catch (Exception e) {

                }
            }


        }


        return fullStackTrace;


    }

    public String getStackTrace(String testObject) {

        String fullStackTrace = "";

        if (getFinalTestResult(testObject).equalsIgnoreCase("FAIL")) {

            net.minidev.json.JSONArray list = JsonPath.parse(testObject).read("$.class.test-method");


            JSONArray methodList = new JSONArray(list);


            for (Object singleMethodResult : methodList) {
                try {
                    fullStackTrace = JsonPath.parse(singleMethodResult.toString()).read("$.exception.full-stacktrace");

                    if (!fullStackTrace.equalsIgnoreCase("")) {
                        break;
                    }
                } catch (Exception e) {

                }
            }


        }


        return fullStackTrace;
    }

    public String getScreenshot() {
        return "Android";
    }


    public JSONArray getMethodArray(String singleTestObject) {

        net.minidev.json.JSONArray list = JsonPath.parse(singleTestObject).read("$.class.test-method");
        JSONArray intialMethodList = new JSONArray(list.toString());

        JSONArray finalMethod = new JSONArray();
        for (Object singleMethodObject : intialMethodList) {
            finalMethod.put(getSingleMethodObject(singleMethodObject));
        }
        return finalMethod;
    }


    public JSONObject getSingleMethodObject(Object singleMethodObject) {
        JSONObject methodObject = new JSONObject();

        methodObject.put("is-config", getIsConfig(singleMethodObject.toString()));
        methodObject.put("name", getMethodName(singleMethodObject.toString()));
        methodObject.put("status", getMethodStatus(singleMethodObject.toString()));
        methodObject.put("started-at", getMethodStartedAt(singleMethodObject.toString()));
        methodObject.put("duration-ms", getDurationAt(singleMethodObject.toString()));
        methodObject.put("finished-at", getMethodFinished(singleMethodObject.toString()));
        methodObject.put("steps", getSteps(singleMethodObject.toString()));

        return methodObject;
    }

    public JSONArray getSteps(String methodObject) {
        try {
            net.minidev.json.JSONArray list = JsonPath.parse(methodObject).read("$.reporter-output.line");
            JSONArray intialStepList = new JSONArray(list.toString());

            for (Object singleSteps : intialStepList) {

                JSONObject step = new JSONObject();

                step.put("step", singleSteps.toString());
                step.put("status", "PASS");

            }
        } catch (Exception e) {
        }

        return new JSONArray();
    }

    public boolean getIsConfig(String methodObject) {

        boolean bool = false;
        try {
            boolean isConfig = JsonPath.parse(methodObject).read("$.is-config");
            bool = isConfig;

        } catch (Exception e) {

        }
        return bool;
    }

    public String getMethodName(String methodObject) {

        String methodName = JsonPath.parse(methodObject).read("$.name");
        return methodName;
    }

    public String getMethodStatus(String methodObject) {
        String methodStatus = JsonPath.parse(methodObject).read("$.status");
        return methodStatus;
    }

    public String getMethodStartedAt(String methodObject) {
        String methodStartedAt = JsonPath.parse(methodObject).read("$.started-at");
        return methodStartedAt;
    }

    public String getMethodFinished(String methodObject) {
        String methodFinishedAt = JsonPath.parse(methodObject).read("$.finished-at");
        return methodFinishedAt;
    }

    public int getDurationAt(String methodObject) {
        int methodDurationms = JsonPath.parse(methodObject).read("$.duration-ms");
        return methodDurationms;
    }


}
