package io.tesbo.report;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RequestBuilder {

    public static String serverURl = "https://reports.tesbo.io/";

    public String createBuild(String key) {
        String buildId = "";

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(serverURl + "api/v1/build/create")
                .method("POST", body)
                .addHeader("x-identity-key", key)
                .build();
        try {

            Response response = client.newCall(request).execute();
            JSONObject object = new JSONObject(response.body().string());
            buildId = ((JSONObject) (object.get("data"))).get("build_id").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buildId;
    }

    public Boolean updateResult(String key, String buildId, JSONObject requestBody) {
        Boolean status = null;

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);

        client = builder.build();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        System.out.println(serverURl+"v1/build/update/" + buildId);
        Request request = new Request.Builder()
                .url(serverURl+"api/v1/build/update/" + buildId)
                .method("POST", body)
                .addHeader("x-identity-key", key)
                .addHeader("Content-Type", "application/json")
                .build();
        try {

            Response response = client.newCall(request).execute();
              String a = response.body().string();

            JSONObject resultObject = new JSONObject(a);


            status = (Boolean) resultObject.get("is_error");


        } catch (IOException e) {
            e.printStackTrace();
        }

        return status;
    }


}
