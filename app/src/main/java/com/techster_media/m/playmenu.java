package com.techster_media.m;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class playmenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playmenu);


    }
    public void online(View view)
    {
        Intent i=new Intent(getBaseContext(),playermatching.class);
        startActivity(i);
    }
    public void friends(View view)
    {
        Intent i=new Intent(getBaseContext(),gamedecision.class);
        startActivity(i);
    }
}
