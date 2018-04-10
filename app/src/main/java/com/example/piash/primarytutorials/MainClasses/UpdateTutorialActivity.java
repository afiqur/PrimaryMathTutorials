package com.example.piash.primarytutorials.MainClasses;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.piash.primarytutorials.Adapter.HttpParse;
import com.example.piash.primarytutorials.R;

import java.util.HashMap;

import static com.example.piash.primarytutorials.Tutorial.Link;

public class UpdateTutorialActivity extends AppCompatActivity {

    private String HttpURL = Link + "UpdateTutorial.php";
    ProgressDialog progressDialog;
    String finalResult;
    HashMap<String, String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    private TextView titleTV,tvSC;
    private EditText classET, topicET, urlET, descriptionET;
    private ImageButton updateBtn, showBtn;
    String tutorial_ID, classT, topicT, urlT, descriptionT;
    private RadioButton rrb1, rrb2, rrb3, rrb4, rrb5;
    private RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tutorial);

        classET = findViewById(R.id.classET);
        topicET = findViewById(R.id.topicET);
        urlET = findViewById(R.id.urlET);
        descriptionET = findViewById(R.id.descriptionET);

        updateBtn = findViewById(R.id.addBtn);
        showBtn = findViewById(R.id.buttonShow);
        titleTV = findViewById(R.id.titleTV);
        tvSC = findViewById(R.id.tvSC);

        rrb1 = findViewById(R.id.rb1);
        rrb2 = findViewById(R.id.rb2);
        rrb3 = findViewById(R.id.rb3);
        rrb4 = findViewById(R.id.rb4);
        rrb5 = findViewById(R.id.rb5);
        rg = findViewById(R.id.rg);

        titleTV.setText("Update Data");
        updateBtn.setImageResource(R.mipmap.update_icon);

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateTutorialActivity.this, ShowAllTutorialActivity.class);
                startActivity(intent);
            }
        });

        tutorial_ID = getIntent().getStringExtra("tutorial_ID");
        classT = getIntent().getStringExtra("t_class");
        topicT = getIntent().getStringExtra("t_topic");
        urlT = getIntent().getStringExtra("t_url");
        descriptionT = getIntent().getStringExtra("t_description");

        classET.setText(classT);
        topicET.setText(topicT);
        urlET.setText(urlT);
        descriptionET.setText(descriptionT);

        rg.setVisibility(View.INVISIBLE);
        tvSC.setVisibility(View.INVISIBLE);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                GetDataFromEditText();

                TutorialDataUpdate(tutorial_ID, classT, topicT, urlT, descriptionT);

                classET.setText(null);
                topicET.setText(null);
                urlET.setText(null);
                descriptionET.setText(null);

                Intent intent = new Intent(UpdateTutorialActivity.this, ShowAllTutorialActivity.class);
                startActivity(intent);

            }
        });


    }

    public void GetDataFromEditText() {

        classT = classET.getText().toString();
        topicT = topicET.getText().toString();
        urlT = urlET.getText().toString();
        descriptionT = descriptionET.getText().toString();

    }

    public void TutorialDataUpdate(final String T_ID, final String T_class, final String T_topic, final String T_url, final String T_description) {

        class ShowTutorialClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(UpdateTutorialActivity.this, "Loading Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(UpdateTutorialActivity.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("tutorial_ID", params[0]);

                hashMap.put("t_class", params[1]);

                hashMap.put("t_topic", params[2]);

                hashMap.put("t_url", params[3]);

                hashMap.put("t_description", params[4]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        ShowTutorialClass showTutorialClass = new ShowTutorialClass();

        showTutorialClass.execute(T_ID, T_class, T_topic, T_url, T_description);
    }
}
