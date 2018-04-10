package com.example.piash.primarytutorials;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;

import com.example.piash.primarytutorials.MainClasses.AllClassesActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private static boolean splashLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView = findViewById(R.id.splash);

        if (!splashLoaded) {
            setContentView(R.layout.activity_main);
            long secondsDelayed = (long) 1;

            new Handler().postDelayed(new Runnable() {

                public void run() {
                    Intent intent = new Intent(MainActivity.this, AllClassesActivity.class);
                    startActivity(intent);
                    finish();
                }

            }, secondsDelayed * 1500);

            splashLoaded = true;
        } else {
            finish();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }


}
