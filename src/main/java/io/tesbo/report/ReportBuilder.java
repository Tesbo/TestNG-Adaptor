package io.tesbo.report;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

//    public JSONObject readJsonFile(String dir){
//        JSONParser parser = new JSONParser();
//        JSONObject jo = new JSONObject();
//        JSONArray ob = new JSONArray();
//        try{
//            Object obj = parser.parse(new FileReader(dir+"/myCucumber.json"));
//            ob = (JSONArray) obj;
//            jo.put("testng-results" , ob);
//        } catch (FileNotFoundException ex) {
//            throw new RuntimeException(ex);
//        } catch (IOException ex) {
//            throw new RuntimeException(ex);
//        } catch (ParseException ex) {
//            throw new RuntimeException(ex);
//        }
//        return jo;
//    }

    public JSONArray readJsonFile(String dir){
        JSONArray ob = null;
        try{
            BufferedReader br = null;
            FileReader fr = new FileReader(dir+"/myCucumber.json");

            br = new BufferedReader(fr);
            String sCurrentLine;
            StringBuffer testResultData = new StringBuffer();

            while ((sCurrentLine = br.readLine()) != null) {

                testResultData.append(sCurrentLine + "\n");
            }

            ob = new JSONArray(testResultData.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
             }
        return ob;
    }

}
