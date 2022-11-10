package io.tesbo.testreport;

import org.json.JSONObject;

public class ReportGenerator {



    public void generatorReport()
    {

    }


    public static void main(String[] args) {

         ReportBuilder builder = new ReportBuilder();

        JSONObject a = builder.convertXmlToJSON(builder.readXmlFile("/Users/viralpatel/Viral/Nevvontesting/test-output"));


        System.out.println(a);
        ReportDataConvertor convertor = new ReportDataConvertor(a);
        convertor.PrepareFinalReport();


    }



}
