package com.example.jasonmoix.jokeactivity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jasonmoix on 1/4/16.
 */
public class JokeFragment extends Fragment {

    public static JokeFragment newInstance(Bundle extras){

        JokeFragment fragment = new JokeFragment();
        fragment.setArguments(extras);
        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_joke, container, false);

        String joke;

        if(getArguments() != null && getArguments().containsKey(JokeActivity.JOKE_EXTRA)){
            joke = getArguments().getString(JokeActivity.JOKE_EXTRA);
        }else{
            joke = getString(R.string.no_joke);
        }

        ((TextView)rootView.findViewById(R.id.joke)).setText(joke);

        return rootView;
    }

}
