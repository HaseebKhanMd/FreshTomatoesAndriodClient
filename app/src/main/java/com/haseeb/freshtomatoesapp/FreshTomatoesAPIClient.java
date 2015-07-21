package com.haseeb.freshtomatoesapp;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import android.util.Base64;
import android.util.Log;
/**
 * Created by Haseeb on 7/19/2015.
 */
public class FreshTomatoesAPIClient {

    /* TODO Remove hardcoded URL, Change Movies to GetAll, Post etc */
    /* Add Error Handling Logic */


    private AsyncHttpClient apiClient;
    private ApplicationConfiguration appConfig;
    public FreshTomatoesAPIClient() {
        this.apiClient = new AsyncHttpClient();
        appConfig = new ApplicationConfiguration();
    }



    private String getFullUrl(String relativePath) {
        return appConfig.getBaseUrl() + relativePath;
    }

    public void getAllMovies(JsonHttpResponseHandler handler) {
        String url = getFullUrl("Movies");
        Log.d("API URL",url);

        //RequestParams params = new RequestParams("apikey", API_KEY);
        RequestParams params = new RequestParams("", "");
        this.apiClient.addHeader("Authorization", "Basic " + Base64.encodeToString(appConfig.getCredentials().getBytes(),Base64.NO_WRAP));
        this.apiClient.get(url, params, handler);
    }

}
