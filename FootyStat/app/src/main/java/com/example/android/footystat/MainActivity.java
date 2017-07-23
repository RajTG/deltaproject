package com.example.android.footystat;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.android.footystat.R.styleable.View;

public class MainActivity extends AppCompatActivity {


    String json_str,json_strn;
    AutoCompleteTextView clubname;
    String club;
    public String fixtureurl;
    Button fixbtn,teams,go;
    static String[] plclubs = new String[] {"Arsenal FC","AFC Bournemouth","Tottenham Hotspur FC","Aston Villa FC",
    "Everton FC","Leicester City FC","Sunderland AFC","Norwich City FC","Crystal Palace FC","Chelsea FC","Swansea City FC",
    "Southampton FC","West Ham United FC","Liverpool FC","West Bromwich Albion FC","Manchester City FC","Stoke City FC",
    "Manchester United FC","Watford FC","Newcastle United FC"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clubname = (AutoCompleteTextView) findViewById(R.id.clubname);
        TextView team2 = (TextView)findViewById(R.id.team2);
        final AutoCompleteTextView clubname = (AutoCompleteTextView) findViewById(R.id.clubname);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, plclubs);
        clubname.setAdapter(adapter);

        go = (Button)findViewById(R.id.go);
        fixbtn = (Button)findViewById(R.id.fixbtn);
        teams = (Button)findViewById(R.id.teams);

        go.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                club = clubname.getText().toString();
                fixbtn.setVisibility(android.view.View.VISIBLE);
                teams.setVisibility(android.view.View.VISIBLE);
                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });





    }
    public void getJSON(View view) {

        new BackgroundTask().execute();


    }
    class BackgroundTask extends AsyncTask<Void,Void,Void>
    {
        String jsonurl,jsonurln;
        String json_str,json_strn;
        //TextView team2 = (TextView)findViewById(R.id.team2);



        @Override
        protected void onPreExecute() {
            jsonurl="http://api.football-data.org/v1/competitions/398/teams";
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url = new URL(jsonurl);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                String authcode = "46c60421d75d420197077a8622efec23";
                connection.setRequestProperty("X-Auth-Token",authcode);
                InputStream instream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
                while(reader.readLine() != null){
                    json_str = reader.readLine();
                }
                JSONArray jsonArray = new JSONArray(json_str);
                for(int i=0;i< jsonArray.length();i++){
                    JSONObject c = jsonArray.getJSONObject(i);
                    String teamnamecheck = c.getString("name");
                    if(club.equals(teamnamecheck)){
                        fixtureurl = c.getString("fixtures");
                    }
                }
                URL urln = new URL(fixtureurl);
                HttpURLConnection connectionn = (HttpURLConnection)urln.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("X-Auth-Token",authcode);
                InputStream instreamn = connectionn.getInputStream();
                BufferedReader readern = new BufferedReader(new InputStreamReader(instreamn));
                while(readern.readLine() != null){
                    json_strn = readern.readLine();
                }




            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    public void parseJSON(View view){

        getJSON(view);
        if(json_strn == null){
            Toast.makeText(this,"nope ", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent passurl = new Intent(this,fixturedisp.class);
            passurl.putExtra("jsondata",fixtureurl);
            startActivity(passurl);
        }

    }


}
