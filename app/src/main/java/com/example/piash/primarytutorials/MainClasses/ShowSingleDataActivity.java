package com.example.piash.primarytutorials.MainClasses;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.piash.primarytutorials.Adapter.HttpParse;
import com.example.piash.primarytutorials.LoginActivity;
import com.example.piash.primarytutorials.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.example.piash.primarytutorials.Tutorial.Link;
import static com.example.piash.primarytutorials.Tutorial.YOUTUBE_API_KEY;

public class ShowSingleDataActivity extends YouTubeBaseActivity {

    private HttpParse httpParse = new HttpParse();
    ProgressDialog pDialog;
    AppCompatActivity a;

    String HttpURL = Link + "FilterTutorialData.php";

    String HttpUrlDeleteRecord = Link + "DeleteTutorial.php";

    private YouTubePlayerView youTubeView;

    YouTubePlayer.OnInitializedListener onInitializedListener;
    String ytLink;

    String finalResult;
    HashMap<String, String> hashMap = new HashMap<>();
    String ParseResult;
    HashMap<String, String> ResultHash = new HashMap<>();
    String FinalJSonObject;
    private TextView classTV, topicTV, urlTV, descriptionTV;
    String classT, topicT, urlT, descriptionT;
    private ImageButton editBtn, dltBtn, backBtn;
    String TempItem;
    ProgressDialog progressDialog2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_data);

        classTV = findViewById(R.id.classTV);
        topicTV = findViewById(R.id.topicTV);
        urlTV = findViewById(R.id.urlTV);
        descriptionTV = findViewById(R.id.descriptionTV);
        youTubeView = findViewById(R.id.youtube_view);

        descriptionTV.setMovementMethod(new ScrollingMovementMethod());

        editBtn = findViewById(R.id.buttonUpdate);
        dltBtn = findViewById(R.id.buttonDelete);
        backBtn = findViewById(R.id.backBtn);

        TempItem = getIntent().getStringExtra("ListViewValue");

        HttpWebCall(TempItem);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowSingleDataActivity.this, ShowAllTutorialActivity.class);
                startActivity(intent);
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ShowSingleDataActivity.this, UpdateTutorialActivity.class);

                intent.putExtra("tutorial_ID", TempItem);
                intent.putExtra("t_class", classT);
                intent.putExtra("t_topic", topicT);
                intent.putExtra("t_url", urlT);
                intent.putExtra("t_description", descriptionT);

                startActivity(intent);

                finish();

            }
        });

        dltBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ShowSingleDataActivity.this);
                alert.setTitle("Delete entry");
                alert.setMessage("Are you sure you want to delete?");
                alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        DeleteTutorial(TempItem);
                    }
                });
                alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // close dialog
                        dialog.cancel();
                    }
                });
                alert.show();


            }
        });

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadVideo(ytLink);

                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

    }

    public void DeleteTutorial(final String TutorialID) {

        class TutorialDeleteClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog2 = ProgressDialog.show(ShowSingleDataActivity.this, "Loading Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog2.dismiss();

                Toast.makeText(ShowSingleDataActivity.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

                finish();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("tutorial_ID", params[0]);

                finalResult = httpParse.postRequest(hashMap, HttpUrlDeleteRecord);

                return finalResult;
            }
        }

        TutorialDeleteClass tutorialDeleteClass = new TutorialDeleteClass();

        tutorialDeleteClass.execute(TutorialID);

        Intent intent = new Intent(ShowSingleDataActivity.this, ShowAllTutorialActivity.class);
        startActivity(intent);

    }


    public void HttpWebCall(final String PreviousListViewClickedItem) {

        class HttpWebCallFunction extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pDialog = ProgressDialog.show(ShowSingleDataActivity.this, "Loading Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                pDialog.dismiss();

                FinalJSonObject = httpResponseMsg;

                new GetHttpResponse(ShowSingleDataActivity.this).execute();

            }

            @Override
            protected String doInBackground(String... params) {

                ResultHash.put("tutorial_ID", params[0]);

                ParseResult = httpParse.postRequest(ResultHash, HttpURL);

                return ParseResult;
            }
        }

        HttpWebCallFunction httpWebCallFunction = new HttpWebCallFunction();

        httpWebCallFunction.execute(PreviousListViewClickedItem);
    }

    public void logoutDo(MenuItem item) {
        Intent intent = new Intent(ShowSingleDataActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    private class GetHttpResponse extends AsyncTask<Void, Void, Void> {
        public Context context;

        public GetHttpResponse(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                if (FinalJSonObject != null) {
                    JSONArray jsonArray = null;

                    try {
                        jsonArray = new JSONArray(FinalJSonObject);

                        JSONObject jsonObject;

                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject = jsonArray.getJSONObject(i);

                            classT = jsonObject.getString("t_class").toString();
                            topicT = jsonObject.getString("t_topic").toString();
                            urlT = jsonObject.getString("t_url").toString();
                            descriptionT = jsonObject.getString("t_description").toString();

                        }
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            classTV.setText(classT);
            topicTV.setText(topicT);
            urlTV.setText(urlT);
            descriptionTV.setText(descriptionT);
            ytLink = urlT;
            youTubeView.initialize(YOUTUBE_API_KEY, onInitializedListener);

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout2_menu, menu);
        return true;
    }



}
