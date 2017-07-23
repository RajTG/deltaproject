package com.example.android.footystat;

/**
 * Created by Raj on 21/07/2017.
 */

public class fixturehelp {

    private String date,home,away;

    public fixturehelp(String date,String home,String away){
        this.setDate(date);
        this.setHome(home);
        this.setAway(away);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }
}
