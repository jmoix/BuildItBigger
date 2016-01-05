package com.udacity.gradle.builditbigger.paid;

import com.udacity.gradle.builditbigger.R;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jasonmoix on 1/5/16.
 */
public class MainFragmentPaid extends Fragment {

    private ProgressDialog mProgressDialog;

    public MainFragmentPaid() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setMessage("Fetching Joke...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        return root;
    }

    public void startSpinner(){
        mProgressDialog.show();
    }

    public void stopSpinner(){
        mProgressDialog.hide();
    }
}
