package com.udacity.gradle.builditbigger.free;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import com.example.jasonmoix.jokeactivity.JokeActivity;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.tasks.EndpointsAsyncTask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * Created by jasonmoix on 1/5/16.
 */
public class MainActivityFree extends ActionBarActivity implements EndpointsAsyncTask.TaskToActivity {

    public final static String TAG = MainActivityFree.class.getSimpleName();

    InterstitialAd mInterstitialAd;
    String mJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate");

        Log.d(TAG, "Initializing Interstitial");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Log.d(TAG, "Interstitial Closed");
                requestNewInterstitial();
                Intent intent = new Intent(getBaseContext(), JokeActivity.class);
                intent.putExtra(JokeActivity.JOKE_EXTRA, mJoke);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getBaseContext().startActivity(intent);
            }

            @Override
            public void onAdLoaded() {
                Log.d(TAG, "Interstitial Loaded");
                Toast.makeText(getBaseContext(), "Ad loaded", Toast.LENGTH_SHORT).show();
            }
        });
        requestNewInterstitial();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void requestNewInterstitial(){
        Log.d(TAG, "Request New Interstitial");
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void showInterstitial(){
        Log.d(TAG, "Showing Interstitial");
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, JokeActivity.class);
            intent.putExtra(JokeActivity.JOKE_EXTRA, mJoke);
            this.startActivity(intent);
        }
    }

    public void tellJoke(View view){

        Log.d(TAG, "Starting Free Spinner");
        ((MainFragmentFree) getSupportFragmentManager().findFragmentById(R.id.free_fragment)).startSpinner();
        new EndpointsAsyncTask().execute(this);
    }

    public void jokeFetched(String joke){
        mJoke = joke;
        Log.d(TAG, "Stopping Free Spinner");
        ((MainFragmentFree)getSupportFragmentManager().findFragmentById(R.id.free_fragment)).stopSpinner();
        showInterstitial();
    }

}
