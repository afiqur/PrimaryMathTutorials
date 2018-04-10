package com.example.piash.primarytutorials.Classes;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.piash.primarytutorials.MainClasses.AllClassesActivity;
import com.example.piash.primarytutorials.Adapter.HttpServicesClass;
import com.example.piash.primarytutorials.Adapter.ListAdapterClass;
import com.example.piash.primarytutorials.R;
import com.example.piash.primarytutorials.MainClasses.ShowSingleDataToUser;
import com.example.piash.primarytutorials.Tutorial;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.piash.primarytutorials.Tutorial.Link;

public class Class1 extends AppCompatActivity {

    private ListView tutorialListView;
    ProgressBar progressBar;
    private String HttpUrl = Link + "Class1Data.php";
    private List<String> IdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_class);

        tutorialListView = findViewById(R.id.listview2);

        progressBar = findViewById(R.id.progressBar1);

        new GetHttpResponse(Class1.this).execute();

        tutorialListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub

                Intent intent = new Intent(Class1.this, ShowSingleDataToUser.class);

                intent.putExtra("ListViewValue", IdList.get(position).toString());

                startActivity(intent);

            }
        });


    }


    private class GetHttpResponse extends AsyncTask<Void, Void, Void> {
        public Context context;

        String JSonResult;

        List<Tutorial> tutirialList;

        public GetHttpResponse(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpServicesClass httpServicesClass = new HttpServicesClass(HttpUrl);
            try {
                httpServicesClass.ExecutePostRequest();

                if (httpServicesClass.getResponseCode() == 200) {
                    JSonResult = httpServicesClass.getResponse();

                    if (JSonResult != null) {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(JSonResult);

                            JSONObject jsonObject;

                            Tutorial tutorial;

                            tutirialList = new ArrayList<Tutorial>();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                tutorial = new Tutorial();

                                jsonObject = jsonArray.getJSONObject(i);

                                IdList.add(jsonObject.getString("tutorial_ID").toString());

                                tutorial.TutorialTopic = jsonObject.getString("t_topic").toString();

                                tutorial.TutorialClasses = jsonObject.getString("t_class").toString();

                                tutirialList.add(tutorial);

                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {
                    Toast.makeText(context, httpServicesClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {

            try{
                progressBar.setVisibility(View.GONE);

                tutorialListView.setVisibility(View.VISIBLE);

                ListAdapterClass adapter = new ListAdapterClass(tutirialList, context);

                tutorialListView.setAdapter(adapter);

            } catch (Exception e){
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "No Data Avaiable", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Class1.this, AllClassesActivity.class);
                startActivity(intent);

            }
        }
    }

}
