package com.example.android.footystat;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raj on 21/07/2017.
 */

public class fixadapter extends ArrayAdapter {

    List list = new ArrayList();
    FixtureHolder fixhold;
    public fixadapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position)
    {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View rowz;
        rowz = convertView;
        if(rowz==null){
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
            rowz = inflater.inflate(R.layout.row,parent,false);
            fixhold = new FixtureHolder();
            fixhold.date= (TextView) rowz.findViewById(R.id.date);
            fixhold.hometeam= (TextView) rowz.findViewById(R.id.hometeam);
            fixhold.awayteam= (TextView) rowz.findViewById(R.id.awayteam);
            rowz.setTag(fixhold);
        }
        else{
            fixhold = (FixtureHolder)rowz.getTag();
        }

        fixturehelp fixturehelpn = (fixturehelp)this.getItem(position);
        fixhold.date.setText(fixturehelpn.getDate());
        fixhold.hometeam.setText(fixturehelpn.getHome());
        fixhold.awayteam.setText(fixturehelpn.getAway());

        return rowz;
    }

    static class FixtureHolder{

        TextView date,hometeam,awayteam;

    }
}
