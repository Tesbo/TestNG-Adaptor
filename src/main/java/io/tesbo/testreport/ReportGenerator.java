package io.tesbo.testreport;

import org.json.JSONObject;

public class ReportGenerator {



    public void generatorReport()
    {

    }


    public static void main(String[] args) {

         ReportBuilder builder = new ReportBuilder();

        JSONObject a = builder.convertXmlToJSON(builder.readXmlFile("C:\\Users\\ViralPatel\\IdeaProjects\\testing\\test-output"));


        ReportDataConvertor convertor = new ReportDataConvertor(a);
        convertor.PrepareFinalReport();


    }



}
