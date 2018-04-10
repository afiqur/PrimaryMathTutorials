package com.example.piash.primarytutorials.AboutUs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.piash.primarytutorials.R;


public class FragmentOne extends Fragment {


    public FragmentOne() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_one, container, false);

    }
}
