package io.tesbo.testreport;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReportBuilder {


    public static int INDENTATION = 4;

    public String readXmlFile(String dir) {

        BufferedReader br = null;
        FileReader fr = null;
        StringBuffer testResultData = new StringBuffer();

        try {
            fr = new FileReader(dir + "/testng-results.xml");
            br = new BufferedReader(fr);
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                testResultData.append(sCurrentLine + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

            e.printStackTrace();
        } finally {

            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return String.valueOf(testResultData);
    }


    public JSONObject convertXmlToJSON(String xmlData) {
        JSONObject intialReport = null;

        try {

            JSONObject jsonObj = XML.toJSONObject(xmlData);
            String json = jsonObj.toString(INDENTATION);
            intialReport = new JSONObject(json);

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return intialReport;
    }


}
