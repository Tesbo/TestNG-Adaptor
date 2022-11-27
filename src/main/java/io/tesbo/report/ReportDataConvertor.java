package io.tesbo.report;


import com.diogonunes.jcolor.Attribute;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.UUID;

import static com.diogonunes.jcolor.Ansi.colorize;

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
//        suite.put("tests", getAvailableTestList());
        report.put("Suite", suite);


        return report;
    }


    public void SingleReportMode(String key, String buildKey, String platform, String browser, String browserVer, String platformVer, String deviceName) {


        JSONArray TestList = getAvailableTestList(platform, browser, browserVer, platformVer, deviceName);
        RequestBuilder requestBuilder = new RequestBuilder();

        System.out.println(colorize("Total " + TestList.length() + " Test Found", Attribute.BLUE_TEXT()));

        System.out.println(colorize("Sending them to our heaven", Attribute.BLUE_TEXT()));

        for (int i = 0; i < TestList.length(); i++) {
            System.out.print(colorize(".", Attribute.MAGENTA_TEXT(), Attribute.BLUE_BACK()));
           if(i%60 == 0 )
           {
               System.out.println("");
    }

            JSONArray tempTestList = new JSONArray();
            tempTestList.put(TestList.get(i));

            JSONObject report = new JSONObject();
            JSONObject suite = new JSONObject();

            suite.put("started-at", getStartedAt());
            suite.put("name", getSuiteName());

            suite.put("duration-ms", getDuration());
            suite.put("tests", tempTestList);
            suite.put("finished-at", getFinishedAt());
            report.put("Suite", suite);

            Boolean result = requestBuilder.updateResult(key, buildKey, report);

            if (!result) {
            } else {
                System.out.println(colorize("Something Wrong.!!! Test has missed the Train to Tesbo World", Attribute.RED_TEXT()));
            }
        }


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

    public JSONArray getAvailableTestList(String platform, String browser, String browserVer, String platformVer, String deviceName) {

        JSONArray finalTestList = new JSONArray();
        JSONArray getOldList = getTestList();

        for (Object singleTest : getOldList) {

            JSONObject singleTestOb = getSingleTestObject(singleTest, platform, browser, browserVer, platformVer, deviceName);
            if (singleTestOb.length() > 0) {
                finalTestList.put(singleTestOb);
            }
        }
        return finalTestList;

    }

    public JSONObject getSingleTestObject(Object testObject, String platform, String browser, String browserVer, String platformVer, String deviceName) {
        JSONObject object = new JSONObject();
        String singleTestObject = testObject.toString();

        JSONArray methods = getMethodArray(testObject.toString());
        if (methods.length() > 2) {
            object.put("testID", UUID.randomUUID().toString());
            object.put("moduleName", getModuleName(singleTestObject));
            object.put("final-test-status", getFinalTestResult(singleTestObject));
            object.put("platformName", getPlatForm(platform));
            object.put("platformVersion", getPlatVersion(platformVer));
            object.put("browser", getBrowser(browser));
            object.put("browserVersion", getBrowserVersion(browserVer));
            object.put("deviceName", getDeviceName(deviceName));
            object.put("started-at", getTestStartedAt(singleTestObject));
            object.put("finished-at", getTestFinishedAt(singleTestObject));
            object.put("duration-ms", getTestDuration(singleTestObject));
            object.put("name", getTestName(singleTestObject));
            object.put("failureMessage", getFailureMessage(singleTestObject));
            object.put("full-stacktrace", getStackTrace(singleTestObject));
            object.put("screenshot", getScreenshot(deviceName));
            object.put("methods", methods);
        }
        return object;
    }


    public String getModuleName(String object) {
        String folderName = "default";
        try {

            String ClassName = JsonPath.parse(object).read("$.class.name");
            String[] nameSplitList = ClassName.split("\\.");

            folderName = nameSplitList[nameSplitList.length - 2];
        } catch (Exception e) {

        }
        return folderName;
    }


    /**
     * @param object
     * @return final test result
     */
    public String getFinalTestResult(String object) {
        String finalTestResult = "SKIPPED";
        try {
            net.minidev.json.JSONArray list = JsonPath.parse(object).read("$.class.test-method[*].status");

            JSONArray testList = new JSONArray(list.toString());

            finalTestResult = "PASS";

            boolean isFailAvailable = false;
            for (Object singleMethodResult : testList) {

                if (singleMethodResult.toString().equalsIgnoreCase("FAIL")) {
                    isFailAvailable = true;
                }

            }

            if (isFailAvailable) {
                finalTestResult = "FAIL";
            }
        } catch (Exception e) {

        }
        return finalTestResult;
    }

    public String getPlatForm(String platform) {
        if(platform == null) {
            return "Win10";
        }else{
            return platform;
        }
    }

    public String getPlatVersion(String platformVer) {
        if(platformVer == null) {
            return "Win1-";
        }else{
            return platformVer;
        }
    }

    public String getBrowser(String browser) {
        if(browser == null) {
            return "Chrome";
        }else{
            return browser;
        }
    }

    public String getBrowserVersion(String browserVer) {
        if(browserVer == null) {
            return "104";
        }else{
            return browserVer;
        }
    }

    public String getDeviceName(String deviceName) {
        if(deviceName == null){
            return "Android";
        }else {
            return deviceName;
        }
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

        String failureMessage = "Failed to get the Stack Trace";

        try {
            if (getFinalTestResult(testObject).equalsIgnoreCase("FAIL")) {

                net.minidev.json.JSONArray list = JsonPath.parse(testObject).read("$.class.test-method");


                JSONArray methodList = new JSONArray(list);


                for (Object singleMethodResult : methodList) {
                    try {
                        failureMessage = JsonPath.parse(singleMethodResult.toString()).read("$.exception.message");

                        if (!failureMessage.equalsIgnoreCase("")) {
                            break;
                        }
                    } catch (Exception e) {

                    }
                }


            }
        } catch (Exception e) {

        }

        return failureMessage;
    }

    public String getStackTrace(String testObject) {

        String fullStackTrace = "Failed to get Stack Trace";

        try {
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
        } catch (Exception e) {

        }

        return fullStackTrace;
    }

    public String getScreenshot(String deviceName) {
        if(deviceName == null){
            return "Android";
        }else {
            return deviceName;
        }
    }


    public JSONArray getMethodArray(String singleTestObject) {
        JSONArray finalMethod = new JSONArray();

        try {
            net.minidev.json.JSONArray list = JsonPath.parse(singleTestObject).read("$.class.test-method");
            JSONArray intialMethodList = new JSONArray(list.toString());

            for (Object singleMethodObject : intialMethodList) {
                finalMethod.put(getSingleMethodObject(singleMethodObject));
            }
        } catch (Exception e) {

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
        JSONArray stepArray = new JSONArray();
        try {
            net.minidev.json.JSONArray list = JsonPath.parse(methodObject).read("$.reporter-output.line");
            JSONArray intialStepList = new JSONArray(list.toString());

            for (Object singleSteps : intialStepList) {

                JSONObject step = new JSONObject();
                step.put("step", singleSteps.toString());
                step.put("status", "PASS");
                stepArray.put(step);

            }
        } catch (Exception e) {

        }

        return stepArray;
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
