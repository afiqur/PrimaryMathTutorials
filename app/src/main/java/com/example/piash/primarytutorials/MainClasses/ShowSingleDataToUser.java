package com.example.piash.primarytutorials.MainClasses;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;

import com.example.piash.primarytutorials.Adapter.HttpParse;
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

public class ShowSingleDataToUser extends YouTubeBaseActivity {

    AppCompatActivity a;
    //YouTubeBaseActivity ty;
    HttpParse httpParse = new HttpParse();
    ProgressDialog pDialog;


    private YouTubePlayerView youTubeView;

    YouTubePlayer.OnInitializedListener onInitializedListener;

    private String HttpURL = Link + "FilterTutorialData.php";

    String ytLink;
    HashMap<String, String> hashMap = new HashMap<>();
    String ParseResult;
    HashMap<String, String> ResultHash = new HashMap<>();
    String FinalJSonObject;
    private TextView topicTV, descriptionTV;
    String classT, topicT, urlT, descriptionT;
    Button backDelete;
    String TempItem;
    ProgressDialog progressDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_data_to_user);

        topicTV = findViewById(R.id.topicTV);
        descriptionTV = findViewById(R.id.descriptionTV);

        descriptionTV.setMovementMethod(new ScrollingMovementMethod());

        youTubeView = findViewById(R.id.youtube_view);

        TempItem = getIntent().getStringExtra("ListViewValue");

        HttpWebCall(TempItem);

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


       /* backDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowSingleDataToUser.this, Class5.class);
                startActivity(intent);
            }

        });*/


    }


    public void HttpWebCall(final String PreviousListViewClickedItem) {

        class HttpWebCallFunction extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pDialog = ProgressDialog.show(ShowSingleDataToUser.this, "Loading Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                pDialog.dismiss();

                FinalJSonObject = httpResponseMsg;

                new ShowSingleDataToUser.GetHttpResponse(ShowSingleDataToUser.this).execute();

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

            topicTV.setText(topicT);
            ytLink = urlT;
            descriptionTV.setText(descriptionT);
            youTubeView.initialize(YOUTUBE_API_KEY, onInitializedListener);

        }

    }
}

