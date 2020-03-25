package testng;

import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SendDataOnDB {

    TestngWatcher testngWatcher=new TestngWatcher();
    public void createBuild(){

    }

    public void createTest(String file)
    {
        if(testngWatcher.checkFileChanged(file)){
            String teatData;
            ConvertXmlToJson convertXmlToJson=new ConvertXmlToJson();
            teatData = convertXmlToJson.convertXmlFileDataToJson(convertXmlToJson.readXmlFile(file));

            System.out.println("=============================================================================================");
            System.out.println(teatData);
            System.out.println("=============================================================================================");
        }
    }

    public static void main(String[] args) throws NoSuchFieldException {
        String file="E:/demo/DemoTNG/DemoTNG/DemoTNG/test-output";
        if(!Files.exists(Paths.get(file))){
            throw new NoSuchFieldException("Directory is not found on following path: "+file);
        }
        SendDataOnDB sendDataOnDB=new SendDataOnDB();
        while (true) {
            sendDataOnDB.createTest(file);
        }
    }

}
