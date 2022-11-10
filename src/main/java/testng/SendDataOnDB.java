package testng;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import okhttp3.*;


import java.io.IOException;
import java.nio.file.*;


public class SendDataOnDB {
/*
    TestngWatcher testngWatcher=new TestngWatcher();
    public static String URL="http://report.tesbo.io:443";
    //public static String URL="http://localhost:7000";
    public static String buildID;

    @Parameter(names={"--userKey", "-u"})
    public static String userKey;
    @Parameter(names={"--projectKey", "-p"})
    public static String projectKey;
    @Parameter(names={"--reportDirectory", "-d"})
    public static String reportDirectory;
    @Parameter(names={"--buildName", "-b"})
    public static String buildName;


    public void setArgument(String[] args) throws Exception {
        SendDataOnDB setCommandLineArgument=new SendDataOnDB();
        JCommander.newBuilder()
                .addObject(setCommandLineArgument)
                .build()
                .parse(args);
        argumentsValidation();
    }

    public void argumentsValidation() throws Exception {
        if(userKey == null | projectKey == null | projectKey == null){
            throw new Exception("Please add all required [userKey, projectKey, projectKey] arguments!!!");
        }
    }




    public void createBuild() throws Exception {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n\t\"buildName\": \""+buildName+"\",\n\t\"projectID\" : \""+projectKey+"\",\n\t\"userID\" : \""+userKey+"\"\n}");
        Request request = new Request.Builder()
                .url(URL+"/createBuild/TestNG")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();

            JSONParser parser = new JSONParser();
            JSONObject object = null;
            try {

                object = (JSONObject) parser.parse(response.body().string());
                if(object.get("errors")!= null){
                    throw new Exception(object.get("message").toString());
                }
                buildID = (object.get("buildID")).toString();
                System.out.println("buildID: "+ buildID);
                response.close();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addDataInDataBase(String file) throws Exception {

        if(testngWatcher.checkFileChanged(file)){
            String teatData;
            ConvertXmlToJson convertXmlToJson=new ConvertXmlToJson();
            teatData = convertXmlToJson.convertXmlFileDataToJson(convertXmlToJson.readXmlFile(file));
            JSONParser parser = new JSONParser();
            JSONObject testngData = null;

            testngData = (JSONObject) parser.parse(teatData);


            createBuild();
            createTests(new JSONObject(testngData));
            updateBuild();
            System.out.println("=============================================================================================");
            System.out.println(testngData);
            System.out.println("=============================================================================================");
        }
    }

    public void createTests(JSONObject testObject) {


        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n\t\"buildKey\": \""+buildID+"\",\n\t\"userKey\" : \""+userKey+"\",\n\t\"projectKey\":\""+projectKey+"\",\n\t\"testsList\":"+testObject+",\n\t\"projectType\":\"TestNG\",\n\t\"buildName\":\""+buildName+"\"\n\t\n}");

            Request request = new Request.Builder()
                    .url(URL+"/createTestNGTests")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBuild() {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n\t\"buildKey\": \""+buildID+"\"\n}");
        Request request = new Request.Builder()
                .url(URL+"/updateBuild")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            response.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arguments) throws Exception {
        SendDataOnDB sendDataOnDB=new SendDataOnDB();

        sendDataOnDB.setArgument(arguments);

        if(!Files.exists(Paths.get(reportDirectory))){
            throw new NoSuchFieldException("Directory is not found on following path: "+reportDirectory);
        }

        while (true) {
            sendDataOnDB.addDataInDataBase(reportDirectory);
        }
    }
*/
}
