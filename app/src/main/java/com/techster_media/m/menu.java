package com.techster_media.m;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class menu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void change(View view)
    {
        Intent i=new Intent(getBaseContext(),profile.class);
        startActivity(i);
    }
    public void playmenu(View view)
    {
        Intent i=new Intent(getBaseContext(),playmenu.class);
        startActivity(i);
    }


}
