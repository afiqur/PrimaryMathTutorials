package com.example.piash.primarytutorials;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.piash.primarytutorials.MainClasses.AllClassesActivity;
import com.example.piash.primarytutorials.MainClasses.ShowAllTutorialActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usenameET, passwordET;
    private ImageButton loginBtn;
    private TextView tv;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usenameET = findViewById(R.id.enterusername);
        passwordET = findViewById(R.id.enterpassword);
        loginBtn = findViewById(R.id.doLogin);
        tv = findViewById(R.id.NAME);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
            }
        });

    }

    /*@Override
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
    }*/

    private void login() {

        String username = usenameET.getText().toString().trim();
        String pass = passwordET.getText().toString().trim();

        progressDialog.show();

        if (username.equals("admin") && pass.equals("1234")) {
            progressDialog.dismiss();
            Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, ShowAllTutorialActivity.class);
            startActivity(intent);
            usenameET.setText(null);
            passwordET.setText(null);
            finish();
        } else {
            Toast.makeText(this, "Invalid Username or Password...", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout2_menu, menu);
        return true;
    }

    public void logoutDo(MenuItem item) {
        Intent intent = new Intent(LoginActivity.this, AllClassesActivity.class);
        startActivity(intent);
    }
}
