package com.example.piash.primarytutorials.MainClasses;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.piash.primarytutorials.Adapter.HttpParse;
import com.example.piash.primarytutorials.R;

import java.util.HashMap;

import static com.example.piash.primarytutorials.Tutorial.Link;

public class AddTutorialActivity extends AppCompatActivity {

    private EditText classET, topicET, urlET, descriptionET;
    private ImageButton addBtn, showBtn;
    private String classT, topicT, urlT, descriptionT;
    Boolean CheckEditText;
    private ProgressDialog progressDialog;
    private String finalResult;
    HashMap<String, String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String HttpURL = Link + "TutorialAdd.php";
    private RadioButton rrb1, rrb2, rrb3, rrb4, rrb5;
    RadioGroup rg;
    String a = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tutorial);

        classET = findViewById(R.id.classET);
        topicET = findViewById(R.id.topicET);
        urlET = findViewById(R.id.urlET);
        descriptionET = findViewById(R.id.descriptionET);
        addBtn = findViewById(R.id.addBtn);
        showBtn = findViewById(R.id.buttonShow);

        classET.setVisibility(View.INVISIBLE);

        rrb1 = findViewById(R.id.rb1);
        rrb2 = findViewById(R.id.rb2);
        rrb3 = findViewById(R.id.rb3);
        rrb4 = findViewById(R.id.rb4);
        rrb5 = findViewById(R.id.rb5);
        rg = findViewById(R.id.rg);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int selectedId = rg.getCheckedRadioButtonId();

                if (rrb1.isChecked()) {

                    CheckEditTextIsEmptyOrNot();

                    if (CheckEditText) {
                        a = String.valueOf(rrb1.getText());
                        TutorialAdd(a, topicT, urlT, descriptionT);
                        rrb1.setChecked(false);
                        topicET.setText(null);
                        urlET.setText(null);
                        descriptionET.setText(null);

                    } else {

                        Toast.makeText(AddTutorialActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                    }
                } else if (rrb2.isChecked()) {
                    //a = String.valueOf(rb1.getText());
                    CheckEditTextIsEmptyOrNot();

                    if (CheckEditText) {
                        a = String.valueOf(rrb2.getText());
                        TutorialAdd(a, topicT, urlT, descriptionT);
                        rrb2.setChecked(false);
                        topicET.setText(null);
                        urlET.setText(null);
                        descriptionET.setText(null);

                    } else {

                        Toast.makeText(AddTutorialActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                    }
                } else if (rrb3.isChecked()) {

                    CheckEditTextIsEmptyOrNot();

                    if (CheckEditText) {
                        a = String.valueOf(rrb3.getText());
                        TutorialAdd(a, topicT, urlT, descriptionT);
                        rrb3.setChecked(false);
                        topicET.setText(null);
                        urlET.setText(null);
                        descriptionET.setText(null);

                    } else {

                        Toast.makeText(AddTutorialActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                    }
                } else if (rrb4.isChecked()) {

                    CheckEditTextIsEmptyOrNot();

                    if (CheckEditText) {
                        a = String.valueOf(rrb4.getText());
                        TutorialAdd(a, topicT, urlT, descriptionT);
                        rrb4.setChecked(false);
                        topicET.setText(null);
                        urlET.setText(null);
                        descriptionET.setText(null);

                    } else {

                        Toast.makeText(AddTutorialActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                    }
                } else if (rrb5.isChecked()) {

                    CheckEditTextIsEmptyOrNot();

                    if (CheckEditText) {
                        a = String.valueOf(rrb5.getText());
                        TutorialAdd(a, topicT, urlT, descriptionT);
                        rrb5.setChecked(false);
                        topicET.setText(null);
                        urlET.setText(null);
                        descriptionET.setText(null);

                    } else {

                        Toast.makeText(AddTutorialActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                    }
                } else {
                    Toast.makeText(AddTutorialActivity.this, "Select Class First", Toast.LENGTH_SHORT).show();

                }
            }
        });

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(), ShowAllTutorialActivity.class);

                startActivity(intent);

            }
        });
    }


    public void TutorialAdd(final String T_class, final String T_topic, final String T_url, final String T_description) {

        class TutorialAddClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(AddTutorialActivity.this, "Loading Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(AddTutorialActivity.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("t_class", params[0]);

                hashMap.put("t_topic", params[1]);

                hashMap.put("t_url", params[2]);

                hashMap.put("t_description", params[3]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        TutorialAddClass tutorialAddClass = new TutorialAddClass();

        tutorialAddClass.execute(T_class, T_topic, T_url, T_description);
    }


    public void CheckEditTextIsEmptyOrNot() {

        classT = String.valueOf(a);
        topicT = topicET.getText().toString();
        urlT = urlET.getText().toString();
        descriptionT = descriptionET.getText().toString();

        if (TextUtils.isEmpty(classT) || TextUtils.isEmpty(topicT) || TextUtils.isEmpty(urlT) || TextUtils.isEmpty(descriptionT)) {

            CheckEditText = false;

        } else {

            CheckEditText = true;
        }
    }


}
