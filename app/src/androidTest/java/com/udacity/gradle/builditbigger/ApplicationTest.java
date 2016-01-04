package com.udacity.gradle.builditbigger;

import com.udacity.gradle.builditbigger.tasks.EndpointsAsyncTask;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.text.TextUtils;
import android.util.Log;

import java.util.concurrent.CountDownLatch;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    public final static String TAG = ApplicationTest.class.getSimpleName();

    CountDownLatch signal = null;

    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }

    public void testEndpointTask() throws InterruptedException{

        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
        endpointsAsyncTask.setListener(new EndpointsAsyncTask.ResultListener() {
            @Override
            public void onComplete(String result, Exception e) {
                checkData(result, e);
                signal.countDown();
            }
        }).execute(getContext());

    }

    public void checkData(String result, Exception error){
        assertNull(error);
    }


}