package io.tesbo.testreport;

import com.beust.jcommander.JCommander;
import com.diogonunes.jcolor.Attribute;
import org.json.JSONObject;

import static com.diogonunes.jcolor.Ansi.colorize;

public class ReportGenerator {
    ReportArgument argument = new ReportArgument();
    JCommander jc = new JCommander(argument);
    ReportBuilder builder = new ReportBuilder();
    RequestBuilder requestBuilder = new RequestBuilder();

    public void generatorReport(String key, String dirPath) {

        System.out.println(colorize("Creating a build", Attribute.BLUE_TEXT()));
        String buildKey = requestBuilder.createBuild(key);

        System.out.println(colorize("Fetching your test report..!!!", Attribute.BLUE_TEXT()));

        JSONObject a = builder.convertXmlToJSON(builder.readXmlFile(dirPath));

        System.out.println(a);
        System.out.println(colorize("Now Understanding it's meaning...", Attribute.BLUE_TEXT()));

        ReportDataConvertor convertor = new ReportDataConvertor(a);
        JSONObject report = convertor.PrepareFinalReport();

        System.out.println(colorize("Now Sending your test details to our haven..!!!", Attribute.BLUE_TEXT()));

        Boolean result = requestBuilder.updateResult(key, buildKey, report);
        if (!result) {
            System.out.println(colorize("Your Reports are reached the Tesbo World Now", Attribute.BLUE_TEXT()));
        } else {
            System.out.println(colorize("Something Wrong.!!! Test are not reached at Destination", Attribute.BLUE_TEXT()));
        }

    }


    public static void main(String[] args) {
        System.out.println(colorize("Welcome to the Tesbo World..!!!", Attribute.BLUE_TEXT()));

        System.out.println(colorize("Please Wait a Moment, We are checking details", Attribute.BLUE_TEXT()));
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.jc.parse(args);
        reportGenerator.generatorReport(reportGenerator.argument.buildKey, reportGenerator.argument.testDir);

    }


}
