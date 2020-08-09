package com.techster_media.m;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.techster_media.m.vars.roomcode;
import static com.techster_media.m.vars.username;
import static com.techster_media.m.vars.usernumber;

public class otherswaitingroom extends AppCompatActivity {

    DatabaseReference mainref,playref;
    Button[] player = new Button[6];
    ProgressBar[] playerbar = new ProgressBar[6];
    ImageView[] playermark = new ImageView[6];
    String playval;
    int usernum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otherswaitingroom);
        player[1] = findViewById(R.id.player1);
        player[2] = findViewById(R.id.player2);
        player[3] = findViewById(R.id.player3);
        player[4] = findViewById(R.id.player4);
        player[5] = findViewById(R.id.player5);
        playerbar[1] = findViewById(R.id.playerbar1);
        playerbar[2] = findViewById(R.id.playerbar2);
        playerbar[3] = findViewById(R.id.playerbar3);
        playerbar[4] = findViewById(R.id.playerbar4);
        playerbar[5] = findViewById(R.id.playerbar5);
        playermark[1] = findViewById(R.id.playermark1);
        playermark[2] = findViewById(R.id.playermark2);
        playermark[3] = findViewById(R.id.playermark3);
        playermark[4] = findViewById(R.id.playermark4);
        playermark[5] = findViewById(R.id.playermark5);



        mainref = FirebaseDatabase.getInstance().getReference();
        mainref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                player[1].setText(dataSnapshot.child("customrooms/"+roomcode+"/user one/name").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        if(usernumber == "user two")
        {
            player[2].setText(username);
            playerbar[2].setVisibility(View.VISIBLE);
            playerbar[2].setProgress(70);
            usernum = 2;
        }
        else if(usernumber == "user three")
        {
            player[3].setText(username);
            playerbar[3].setVisibility(View.VISIBLE);
            playerbar[3].setProgress(70);
            usernum = 3;
        }
        else if(usernumber == "user four")
        {
            player[4].setText(username);
            playerbar[4].setVisibility(View.VISIBLE);
            playerbar[4].setProgress(70);
            usernum = 4;
        }
        else if(usernumber == "user five")
        {
            player[5].setText(username);
            playerbar[5].setVisibility(View.VISIBLE);
            playerbar[5].setProgress(70);
            usernum = 5;
        }
        playref = mainref.child("customrooms/"+roomcode+"/playing");
        playref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                playval = dataSnapshot.getValue().toString();
                switch (playval) {
                    case "true":
                        startActivity(new Intent(getApplicationContext(), crgameplaytest.class));
                        break;
                    default:Toast.makeText(otherswaitingroom.this, "failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void ready(View view)
    {
        mainref.child("customrooms/"+roomcode+"/"+usernumber+"/ready").setValue("true");
        playerbar[usernum].setVisibility(View.INVISIBLE);
        playerbar[usernum].setProgress(0);
        playermark[usernum].setVisibility(View.VISIBLE);
    }

}
