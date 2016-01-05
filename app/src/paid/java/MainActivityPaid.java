package com.udacity.gradle.builditbigger.paid;

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

/**
 * Created by jasonmoix on 1/5/16.
 */
public class MainActivityPaid extends ActionBarActivity implements EndpointsAsyncTask.TaskToActivity {

    public final static String TAG = MainActivityPaid.class.getSimpleName();

    String mJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void tellJoke(View view){
        ((com.udacity.gradle.builditbigger.paid.MainFragmentPaid) getSupportFragmentManager().findFragmentById(R.id.paid_fragment)).startSpinner();
        new EndpointsAsyncTask().execute(this);
    }

    public void jokeFetched(String joke){

        Log.d(TAG, "Stopping Paid Spinner");
        ((com.udacity.gradle.builditbigger.paid.MainFragmentPaid) getSupportFragmentManager().findFragmentById(R.id.paid_fragment)).stopSpinner();
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_EXTRA, joke);
        this.startActivity(intent);

    }

}
