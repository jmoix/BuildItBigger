package com.udacity.gradle.builditbigger;

import com.example.jasonmoix.jokeactivity.JokeActivity;
import com.udacity.gradle.builditbigger.tasks.EndpointsAsyncTask;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity implements EndpointsAsyncTask.TaskToActivity {

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
        ((MainActivityFragment)getSupportFragmentManager().findFragmentById(R.id.fragment)).startSpinner();
        new EndpointsAsyncTask().execute(this);
    }

    public void jokeFetched(String joke){
        ((MainActivityFragment)getSupportFragmentManager().findFragmentById(R.id.fragment)).stopSpinner();
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_EXTRA, joke);
        this.startActivity(intent);
    }

}
