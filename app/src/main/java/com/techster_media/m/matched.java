package com.techster_media.m;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class matched extends AppCompatActivity {
    DatabaseReference rootref, gameroomref;
    String gamename, gamefullbool,tempUID,myUID,oppUID;
    TextView useronetext, usertwotext, vs;
    int k;
    void gamefulltrue() {
        gameroomref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                useronetext.setText(dataSnapshot.child("user one/name").getValue().toString());
                usertwotext.setText(dataSnapshot.child("user two/name").getValue().toString());
                myUID = dataSnapshot.child("user one/id").getValue().toString();
                myUID = dataSnapshot.child("user two/id").getValue().toString();
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.print("error");
                }
                Log.d("gamefulltrue", vars.whoami);
                Intent intent = new Intent(getBaseContext(), gameplaytest.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    void gamefullfalse() {
        gameroomref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                useronetext.setText(dataSnapshot.child("user one/name").getValue().toString());
                tempUID = dataSnapshot.child("user one/id").getValue().toString();
                usertwotext.setText("waiting...");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        System.out.print("error");
                    }
                Log.d("gamefullfalse", vars.whoami);
                    matching();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    void matching()
    {
         Intent intent = getIntent();
        k = vars.gamenum;
        gamename = "game rooms/gameroom " + k;
        Log.d("matching", vars.whoami);
        rootref = FirebaseDatabase.getInstance().getReference();
        gameroomref = FirebaseDatabase.getInstance().getReference().child(gamename);
        gameroomref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                gamefullbool = dataSnapshot.child("gamefull").getValue().toString();
                if (gamefullbool.equals("true")) {
                    gameroomref.child("user one/tempid").setValue(oppUID);
                    gameroomref.child("user two/tempid").setValue(myUID);
                    if(myUID == oppUID)
                    {
                        Intent intent = new Intent(getBaseContext(), playermatching.class);
                        startActivity(intent);
                    }
                    else  gamefulltrue();
                } else if (gamefullbool.equals("false")) {
                    gamefullfalse();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.d("matching1", vars.whoami);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matched);
        useronetext = findViewById(R.id.userone);
        usertwotext = findViewById(R.id.usertwo);
        Log.d("matchedfirst", vars.whoami);
        vs = findViewById(R.id.vs);
        matching();

    }
}
