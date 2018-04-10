package com.example.piash.primarytutorials.AboutUs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.piash.primarytutorials.MainClasses.AllClassesActivity;
import com.example.piash.primarytutorials.R;

public class AboutUsFragmentActivity extends AppCompatActivity {

    private TextView contactus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        contactus = findViewById(R.id.contactus);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FragmentOne fragmentOne = new FragmentOne();
        ft.add(R.id.fragmentContainer, fragmentOne);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();

        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("mailto:" + "team@gmail.com"));
                startActivity(Intent.createChooser(i, "E-Mail"));
            }
        });

    }


    public void changeFragment(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.fragmentOne:
                fragment = new FragmentOne();
                break;
            case R.id.fragmentTwo:
                fragment = new FragmentTwo();
                break;
            case R.id.fragmentThree:
                fragment = new FragmentThree();
                break;
            case R.id.fragmentFour:
                fragment = new FragmentFour();
                break;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentContainer, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AboutUsFragmentActivity.this, AllClassesActivity.class);
        startActivity(intent);
    }
}
