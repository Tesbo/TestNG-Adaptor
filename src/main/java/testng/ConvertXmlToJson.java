package testng;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.FileReader;


public class ConvertXmlToJson {

    public static int INDENTATION = 4;

    public String readXmlFile(String file) {

        BufferedReader br = null;
        FileReader fr = null;
        StringBuffer testResultData = new StringBuffer();

        try {
            fr = new FileReader(file+"/testng-results.xml");
            br = new BufferedReader(fr);
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                testResultData.append(sCurrentLine + "\n");
            }
        } catch (Exception e) {
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

    public String convertXmlFileDataToJson(String xmlData){
        try {
            JSONObject jsonObj = XML.toJSONObject(xmlData);
            String json = jsonObj.toString(INDENTATION);

            return json;
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String file="E:/demo/DemoTNG/DemoTNG/DemoTNG/test-output";
        ConvertXmlToJson convertXmlToJson=new ConvertXmlToJson();
        convertXmlToJson.convertXmlFileDataToJson(convertXmlToJson.readXmlFile(file));
    }

}
