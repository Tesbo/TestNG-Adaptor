package io.tesbo.selfadaptor;

import org.json.JSONArray;
import org.json.JSONObject;

public class TesboReportTest {


    public enum Status {
        PASS,
        FAIL,
        SKIP
    }
     JSONArray recordStep = new JSONArray();
    public String testName = "";
    public String moduleName = "";
    public String deviceName = "";
    public String platformVersion = "";
    public String browser = "";
    public String browserVersion = "";
    public String failureMessage = "";
    public String fullStacktrace = "";



    public void addStep(String message, String status) {
        JSONObject step = new JSONObject();
        step.put("step", message);
        step.put("status", status);
        recordStep.put(step);
    }




}
