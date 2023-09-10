package io.tesbo.selfadaptor;

import io.tesbo.report.RequestBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.google.common.base.Throwables.getStackTraceAsString;

public class TestSuite {


    public String testSuiteName = "";
    public String startedAt = "";
    public String finishedAt = "";
    public String duration = "";
    public static String buildId = "";
    public static String accessKey = "";


    RequestBuilder builder = new RequestBuilder();

    public void createSuite(String accessKey) {


        buildId = builder.createBuild(accessKey);
        TestSuite.accessKey = accessKey;
    }


    public void addTestStartTime() {

    }


    public void saveTest(TesboReportTest test, String testStatus) {
        JSONObject suite = new JSONObject();
        JSONArray testArray = new JSONArray();

        JSONObject testObject = new JSONObject();

        JSONArray methodArray = new JSONArray();

        JSONObject methodObject = new JSONObject();


        testObject.put("name", test.testName);
        testObject.put("testID", UUID.randomUUID().toString());
        testObject.put("moduleName", test.moduleName);
        testObject.put("screenshot", "");
        testObject.put("final-test-status", testStatus);
        testObject.put("deviceName", test.deviceName);
        testObject.put("finished-at", "2021-08-12T12:12:12");
        testObject.put("platformVersion", test.platformVersion);
        testObject.put("browser", test.browser);
        testObject.put("browserVersion", test.browserVersion);
        testObject.put("started-at", "2021-08-12T12:12:12");
        testObject.put("failureMessage", test.failureMessage);
        testObject.put("duration-ms", 1231234124);
        testObject.put("full-stacktrace", test.fullStacktrace);


        methodObject.put("is-config", false);
        methodObject.put("started-at", "2021-08-12T12:12:12");
        methodObject.put("finished-at", "2021-08-12T12:12:12");
        methodObject.put("status", testStatus);
        methodObject.put("duration-ms", 1);
        methodObject.put("name", test.testName);
        methodObject.put("steps", test.recordStep);

        methodArray.put(methodObject);

        testObject.put("methods", methodArray);


        testArray.put(testObject);

        suite.put("tests", testArray);
        suite.put("started-at", "2021-08-12T12:12:12");
        suite.put("finished-at", "2021-08-12T12:12:12");
        suite.put("name", testSuiteName);
        suite.put("duration-ms", 1);


        JSONObject finalSuite = new JSONObject();

        finalSuite.put("Suite", suite);


        System.out.println(finalSuite.toString());
        builder.updateResult(TestSuite.accessKey, buildId, finalSuite);

    }



    public void recordTestFailure(TesboReportTest test, AssertionError e) {
        test.failureMessage = e.getMessage();
        System.out.println("Failure Message : " + e.getMessage());
        test.fullStacktrace = getStackTraceAsString(e);
        saveTest(test, TesboReportTest.Status.FAIL.toString());
    }

}



