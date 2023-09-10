package io.tesbo.selfadaptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.tesbo.report.RequestBuilder;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class TestReportEngine {


    public static String buildID = "";

    public void startReportTracking(String authKey) {
        RequestBuilder builder = new RequestBuilder();
        buildID = builder.createBuild(authKey);

    }


    String suiteName = "";
    String moduleName = "";
    String testName = "";
    String testStatus = "";
    JSONArray testSteps = new JSONArray();

    String screenshot = "";
    String deviceName = "";

    String startedAt = "";
    String finishedAt = "";
    String stacktrace = "";
    String browser = "";
    String platform = "";
    String suiteStartTime = "";
    String suiteEndTime = "";

    JSONArray methods = new JSONArray();

    public void recordTest() {

        String testBody = "";

        JSONArray methods = new JSONArray();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("{{PROJECT_URL}}api/v1/build/update/a945e66a-c4a3-41e0-b58e-3c54f2c61ce5")
                .method("POST", body)
                .addHeader("x-identity-key", "{{X-IDENTITY-KEY}} ")
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
