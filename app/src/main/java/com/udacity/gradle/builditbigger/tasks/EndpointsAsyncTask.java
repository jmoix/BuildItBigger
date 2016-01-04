package com.udacity.gradle.builditbigger.tasks;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import com.example.jasonmoix.jokeactivity.JokeActivity;
import com.example.jasonmoix.myapplication.backend.myApi.MyApi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import java.io.IOException;

/**
 * Source: https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
 */

public class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private ResultListener mResultListener;
    private Exception mError;

    public interface TaskToActivity{
        void jokeFetched(String joke);
    }

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();

        }

        context = params[0];

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public EndpointsAsyncTask setListener(ResultListener resultListener){
        this.mResultListener = resultListener;
        return this;
    }

    @Override
    protected void onPostExecute(String result) {
        if(mResultListener != null) mResultListener.onComplete(result, mError);
        ((TaskToActivity)context).jokeFetched(result);
    }

    @Override
    protected void onCancelled() {
        if(mResultListener != null) {
            mError = new InterruptedException("AsyncTask Cancelled");
            mResultListener.onComplete(null, mError);
        }
    }

    public static interface ResultListener{
        void onComplete(String result, Exception e);
    }
}
