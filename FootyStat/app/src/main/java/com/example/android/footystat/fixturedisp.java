package com.example.android.footystat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class fixturedisp extends AppCompatActivity {
    String json_strn;
    JSONObject jsonObject;
    JSONArray jsonArray;
    fixadapter fixadaptern;
    ListView fixlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixturedisp);

        fixadaptern = new fixadapter(this,R.layout.row);
        fixlist = (ListView)findViewById(R.id.fixlist);
        fixlist.setAdapter(fixadaptern);


        json_strn = getIntent().getExtras().getString("jsondata");
        try{
            jsonObject = new JSONObject(json_strn);
            jsonArray = new JSONArray(json_strn);
            int count = 0;
            String date,homet,awayt;
            while(count < jsonArray.length()){
                JSONObject jo = jsonArray.getJSONObject(count);

                date = jo.getString("date");
                homet = jo.getString("homeTeamName");
                awayt = jo.getString("awayTeamName");

                fixturehelp fixturehelpn = new fixturehelp(date,homet,awayt);
                fixadaptern.add(fixturehelpn);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
