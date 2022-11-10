package io.tesbo.testreport;

import org.json.JSONArray;
import org.json.JSONObject;

public class ReportDataConvertor {


    JSONObject reportData;

    ReportDataConvertor(JSONObject reportData) {

        this.reportData = reportData;

    }



    public void PrepareFinalReport()
    {
        JSONObject report = new JSONObject();

        JSONObject suite = new JSONObject();



        suite.put("started-at","");
        suite.put("name","");
        suite.put("finished-at","");
        suite.put("duration-ms","");


        JSONArray testArray = new JSONArray();
        suite.put("tests",testArray);











        report.put("suite", suite);


        System.out.println(report);

    }


}
