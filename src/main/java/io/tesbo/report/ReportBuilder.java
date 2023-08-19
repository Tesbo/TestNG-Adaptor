package io.tesbo.report;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReportBuilder {
    public static int INDENTATION = 4;

    public String readXmlFile(String dir) {
        StringBuilder testResultData = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(dir, "testng-results.xml"))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                testResultData.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {

        }

        return testResultData.toString();
    }

    public JSONObject convertXmlToJSON(String xmlData) {
        JSONObject initialReport = null;
        try {
            JSONObject jsonObj = XML.toJSONObject(xmlData);
            String json = jsonObj.toString(INDENTATION);
            initialReport = new JSONObject(json);
        } catch (JSONException ex) {

        }
        return initialReport;
    }

    public JSONArray readJsonFile(String dir) {
        JSONArray ob = null;
        StringBuilder testResultData = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(dir, "Cucumber.json"))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                testResultData.append(sCurrentLine).append("\n");
            }
            ob = new JSONArray(testResultData.toString());
        } catch (IOException | JSONException ex) {

        }
        return ob;
    }
}
