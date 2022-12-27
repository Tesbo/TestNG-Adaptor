package io.tesbo.report;


import com.diogonunes.jcolor.Attribute;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.UUID;

import static com.diogonunes.jcolor.Ansi.colorize;

public class CucumberDataConverter {

    JSONArray reportData;

//    CucumberDataConverter(JSONArray reportData) {
//        this.reportData = reportData;
//    }

    public CucumberDataConverter(JSONArray a) {
        this.reportData = a;
    }

    public JSONObject PrepareFinalReport()  {
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


    public void SingleReportMode(String key, String buildKey)  {


        JSONArray TestList = getAvailableTestList();
        RequestBuilder requestBuilder = new RequestBuilder();

        System.out.println(colorize("Total " + TestList.length() + " Test Found", Attribute.BLUE_TEXT()));

        System.out.println(colorize("Sending them to our heaven", Attribute.BLUE_TEXT()));

        for (int i = 0; i < TestList.length(); i++) {
            System.out.print(colorize(".", Attribute.MAGENTA_TEXT(), Attribute.BLUE_BACK()));
            if (i % 60 == 0) {
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


    public void batchModeReport(String key, String buildKey)  {

        JSONArray TestList = getAvailableTestList();
        System.out.println("TestList Size Before" + TestList.length());
        /*
         * Getting the list of the methods from the test
         * */
//        int length = TestList.length();
//        JSONArray finalTestList = new JSONArray();
//
//        for (int i = 0; i < length; i++) {
//
//            JSONArray methodList = (JSONArray) ((JSONObject) TestList.get(i)).get("methods");
//
//            for (Object a : methodList) {
//
//                JSONObject singleMethod = (JSONObject) a;
//                JSONArray tempArray = new JSONArray();
//                if ((boolean) singleMethod.get("is-config")) {
//                } else {
//                    tempArray.put(singleMethod);
//                    System.out.println("tempArray Method Size " + tempArray.length());
//
//                    finalTestList.put(createTestFromMethodObject(TestList.get(i), tempArray));
//                }
//
//            }
//
//        }
//        System.out.println(finalTestList);
//
//        System.out.println("TestList Size After" + finalTestList.length());
//
//
//        RequestBuilder requestBuilder = new RequestBuilder();
//
//        System.out.println(colorize("Total " + finalTestList.length() + " Test Found", Attribute.BLUE_TEXT()));
//
//        System.out.println(colorize("Sending them to our heaven", Attribute.BLUE_TEXT()));
//
//        for (int i = 0; i < finalTestList.length(); i++) {
//            System.out.print(colorize(".", Attribute.MAGENTA_TEXT(), Attribute.BLUE_BACK()));
//            if (i % 60 == 0) {
//                System.out.println("");
//            }
//
//            JSONArray tempTestList = new JSONArray();
//            tempTestList.put(finalTestList.get(i));
//
//            JSONObject report = new JSONObject();
//
//            JSONObject suite = new JSONObject();
//
//            suite.put("started-at", getStartedAt());
//            suite.put("name", getSuiteName());
//
//            suite.put("duration-ms", getDuration());
//            suite.put("tests", tempTestList);
//            suite.put("finished-at", getFinishedAt());
//            report.put("Suite", suite);
//
//            System.out.println("\n\n\nFinal"+report);

//            Boolean result = requestBuilder.updateResult(key, buildKey, report);

//            if (!result) {
//            } else {
//                System.out.println(colorize("Something Wrong.!!! Test has missed the Train to Tesbo World", Attribute.RED_TEXT()));
//            }
//        }


        //calculate Batch Size(How Many Batch)
        //Prepare the single batch
        //Send Them to server


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

    public JSONArray getTestList()  {
        JSONObject obj = (JSONObject) reportData.get(0);
       // JSONObject cucumberElementList = (JSONObject) obj.get("elements");
        JSONArray allTestList = obj.getJSONArray("elements");
        return allTestList;
    }

    public JSONArray getStepList(JSONObject singleTestObject)  {
        JSONArray allStepsList = (JSONArray) singleTestObject.get("steps");
        return allStepsList;
    }
    public JSONArray getAvailableTestList()  {

        JSONArray finalTestList = new JSONArray();
        JSONArray getOldList = getTestList();
        for (Object test : getOldList) {
            JSONObject singleTestOb = getSingleTestObject(test);
//            if (singleTestOb.length() >= 0) {
//                finalTestList.put(singleTestOb);
//            }
        }
        return finalTestList;

    }

    public String getCucumberStepSingleTestName(String obj,int i){
        String cucumberStepName;
        JSONObject object = JsonPath.parse(obj).read("$.*.name");
//        for(int j=0; i<=object.length();i++){
//
//        }
//        if(i==0){
//            cucumberStepName = JsonPath.parse(obj).read("$.[0].name");
//        }else{
//            cucumberStepName = (String) JsonPath.parse(obj).read("$."+i+".name");
//        }
//        cucumberStepName = (String) object.get(i);
        System.out.println("\nOBJECT\n"+object);
        return null;
    }

    public String getCucumberStepTestName(String obj,int i){
        net.minidev.json.JSONArray cucumberStepName;
        cucumberStepName = JsonPath.parse(obj).read("$.[*].name");
        String stepName = null;
        try {
            stepName = (String) cucumberStepName.get(i);
        }catch (Exception e){
            System.out.println("No Name Present");
        }
        return stepName;
    }

    public String getCucumberStepTestDuration(String obj,int i){
        net.minidev.json.JSONArray cucumberStepName;
        cucumberStepName = JsonPath.parse(obj).read("$.[*].result.duration");
        String StepTestDuration = null;
        try {
            StepTestDuration = String.valueOf(cucumberStepName.get(i));
        }catch (Exception e){
            System.out.println("Duration is missing for this Index Object");
        }
        return StepTestDuration;
    }
    public String getCucumberStepTestStatus(String obj,int i){
        net.minidev.json.JSONArray cucumberStepTestStatus;
        cucumberStepTestStatus = JsonPath.parse(obj).read("$.[*].result.status");
        String StepTestStatus = null;
        try {
            StepTestStatus = (String) cucumberStepTestStatus.get(i);
        }catch (Exception e){
            System.out.println("Status is Missing for this Index Object");
        }
        //Will Remove If Not Required
//        if(i==0) {
//            cucumberStepTestStatus = JsonPath.parse(obj).read("$.[0].result.status");
//        }else{
//            cucumberStepTestStatus = JsonPath.parse(obj).read("$."+i+".result.status");
//        }
        return StepTestStatus;
    }

    public JSONArray getCucumberTestObject(JSONArray object) {
        JSONObject obj = new JSONObject();
        for(int i=0; i < object.length(); i++){
            obj.append("name",getCucumberStepTestName(String.valueOf(object),i));
            obj.append("duration",getCucumberStepTestDuration(String.valueOf(object),i));
            obj.append("status",getCucumberStepTestStatus(String.valueOf(object),i));
        }
        net.minidev.json.JSONArray name = JsonPath.parse(String.valueOf(obj)).read("$.name");
        net.minidev.json.JSONArray duration = JsonPath.parse(String.valueOf(obj)).read("$.duration");
        net.minidev.json.JSONArray status = JsonPath.parse(String.valueOf(obj)).read("$.status");
        JSONObject finalObject = new JSONObject();
        JSONArray CucumberTestObject = new JSONArray();
//        JSONObject CucumberTestObject = new JSONObject();
        for(int j=0;j < name.size(); j++){
            finalObject.put("name",name.get(j));
            finalObject.put("duration",String.valueOf(duration.get(j)));
            finalObject.put("status",status.get(j));
            System.out.println("FinalObject "+finalObject);
            CucumberTestObject.put(finalObject);
            System.out.println("CucumberTestObject "+CucumberTestObject);
//            System.out.println("CucumberTestObject "+CucumberTestObject);
//            CucumberTestObject.append(String.valueOf(j),finalObject);
//            CucumberTestObject.(String.valueOf(j),finalObject);
//            if(j==(name.size()-1)) {
//                CucumberTestObject.put(finalObject);
//            }
        }
        System.out.println(finalObject);
        return CucumberTestObject;
    }

    public JSONObject getSingleTestObject(Object testObject) {
        JSONObject object = new JSONObject();
        JSONObject singleTestObject =(JSONObject) testObject;
        JSONArray stepsJsonObject = getStepList(singleTestObject);
//        System.out.println(singleTestObject);
//        JSONArray methods = getMethodArray(testObject.toString());
        if (singleTestObject.length() > 0) {
            object.put("testID", UUID.randomUUID().toString());
            object.put("moduleName", getModuleName(singleTestObject.toString()));
//            object.put("final-test-status", getFinalTestResult(singleTestObject.toString()));
//            object.put("platformName", getPlatForm());
//            object.put("platformVersion", getPlatVersion());
//            object.put("browser", getBrowser());
//            object.put("browserVersion", getBrowserVersion());
//            object.put("deviceName", getDeviceName());
            object.put("started-at", getTestStartedAt(singleTestObject.toString()));
            object.put("results",getCucumberTestObject(stepsJsonObject));
//            object.put("finished-at", getTestFinishedAt(singleTestObject.toString()));
//            object.put("duration-ms", getTestDuration(singleTestObject.toString()));
            object.put("name", getTestName(singleTestObject.toString()));
//            object.put("failureMessage", getFailureMessage(singleTestObject.toString()));
//            object.put("full-stacktrace", getStackTrace(singleTestObject.toString()));
//            object.put("screenshot", getScreenshot());
//            object.put("methods", methods);
        }
//        System.out.println(object);
        return object;
    }

    /**
     * This method will create a separate test from the methods, this will be used when the data provider run the test
     *
     * @param testObject
     * @return
     */
    public JSONObject createTestFromMethodObject(Object testObject, JSONArray methods) {
        JSONObject object = new JSONObject();
        String singleTestObject = testObject.toString();
        System.out.println("Single Test Object" + singleTestObject);

        object.put("testID", UUID.randomUUID().toString());
        object.put("moduleName", getModuleName(singleTestObject));
        object.put("final-test-status", getTestResultForSingleMethods(methods));
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
        object.put("methods", methods);

        return object;
    }


    public String getModuleName(String object) {
        String folderName = "default";
        try {

            String ClassName = JsonPath.parse(object).read("$.id");
            String[] nameSplitList = ClassName.split("\\.");

            folderName = nameSplitList[nameSplitList.length - 2];
        } catch (Exception e) {


        }
        return folderName;
    }


    public String getTestResultForSingleMethods(JSONArray array) {

        String finalTestResult = "SKIPPED";
        try {
            net.minidev.json.JSONArray list = JsonPath.parse(array.toString()).read("$.[*].status");

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
            e.printStackTrace();
        }
        return finalTestResult;

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
            e.printStackTrace();
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
        String testStartedAt = JsonPath.parse(object).read("$.start_timestamp");
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

    public String getScreenshot() {
        return "Android";
    }

    public JSONArray getMethodArray(String singleTestObject) {
        JSONArray finalMethod = new JSONArray();

        try {
            net.minidev.json.JSONArray list = JsonPath.parse(singleTestObject).read("$.*");
            JSONArray intialMethodList = new JSONArray(list.toString());

            for (Object singleMethodObject : intialMethodList) {
                finalMethod.put(getSingleMethodObject(singleMethodObject));
            }
        } catch (Exception e) {

//            System.out.println("Single Test Object" + singleTestObject);
            LinkedHashMap  list =  JsonPath.parse(singleTestObject).read("$.*");

            try {
                System.out.println("linked" +list.toString());
                JSONArray intialMethodList = new JSONArray(list);
                System.out.println("JSON ARRAY" + intialMethodList);

                for (Object singleMethodObject : intialMethodList) {
                    finalMethod.put(getSingleMethodObject(singleMethodObject));
                }
            }catch (Exception e2)
            {

                JSONObject intialMethodObject = new JSONObject(list);

                JSONArray intialMethodList = new JSONArray();
                intialMethodList.put(intialMethodObject);

                for (Object singleMethodObject : intialMethodList) {
                    finalMethod.put(getSingleMethodObject(singleMethodObject));
                }

            }


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
        methodObject.put("data-provider", getDataProvider(singleMethodObject.toString()));


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
            e.printStackTrace();
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

    public boolean getDataProvider(String methodObject) {

        boolean bool = false;
        try {
            String isConfig = JsonPath.parse(methodObject).read("$.data-provider");
            bool = true;

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
