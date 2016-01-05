package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ProgressDialog mProgressDialog;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setMessage("Fetching Joke...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);

        if(mAdView != null) {
            // Create an ad request. Check logcat output for the hashed device ID to
            // get test ads on a physical device. e.g.
            // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            mAdView.loadAd(adRequest);
        }

        return root;
    }

    public void startSpinner(){
        mProgressDialog.show();
    }

    public void stopSpinner(){
        mProgressDialog.hide();
    }
}
