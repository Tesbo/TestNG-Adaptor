package io.tesbo.testreport;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class RequestBuilder {

    public static String serverURl = "http://report-man.appmatictech.com/";

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
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        Request request = new Request.Builder()
                .url("http://report-man.appmatictech.com/api/v1/build/update/" + buildId)
                .method("POST", body)
                .addHeader("x-identity-key", key)
                .addHeader("Content-Type", "application/json")
                .build();
        try {

            Response response = client.newCall(request).execute();
            JSONObject resultObject = new JSONObject(response.body().string());

            status = (Boolean) resultObject.get("is_error");


        } catch (IOException e) {
            e.printStackTrace();
        }

        return status;
    }


}
