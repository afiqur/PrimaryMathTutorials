package com.example.piash.primarytutorials.MainClasses;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.piash.primarytutorials.AboutUs.AboutUsFragmentActivity;
import com.example.piash.primarytutorials.Classes.Class1;
import com.example.piash.primarytutorials.Classes.Class2;
import com.example.piash.primarytutorials.Classes.Class3;
import com.example.piash.primarytutorials.Classes.Class4;
import com.example.piash.primarytutorials.Classes.Class5;
import com.example.piash.primarytutorials.LoginActivity;
import com.example.piash.primarytutorials.R;

public class AllClassesActivity extends AppCompatActivity {
    private ImageButton btnOne, btnTwo, btnThree, btnFour, btnFive;
    private TextView textView0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
        textView0 = findViewById(R.id.textView0);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllClassesActivity.this, Class1.class);
                startActivity(intent);
            }
        });
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllClassesActivity.this, Class2.class);
                startActivity(intent);
            }
        });
        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllClassesActivity.this, Class3.class);
                startActivity(intent);
            }
        });
        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllClassesActivity.this, Class4.class);
                startActivity(intent);
            }
        });
        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllClassesActivity.this, Class5.class);
                startActivity(intent);
            }
        });

        textView0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AllClassesActivity.this, "Select a Book Below", Toast.LENGTH_SHORT).show();
            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return true;
    }

    public void backtoMain(MenuItem item) {
        Intent intent = new Intent(AllClassesActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void aboutUs(MenuItem item) {
        Intent intent = new Intent(AllClassesActivity.this, AboutUsFragmentActivity.class);
        startActivity(intent);
    }
}
