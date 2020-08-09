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

public class waitingroom extends AppCompatActivity {

    DatabaseReference rootref,usern,usertworeadyref,userthreereadyref,userfourreadyref,userfivereadyref;
    String usernumber,usertwoname,usertwoready,userthreeready,userfourready,userfiveready,userthreename,userfourname,userfivename;
    Button[] player = new Button[6];
    ProgressBar[] playerbar = new ProgressBar[6];
    ImageView[] playermark = new ImageView[6];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waitingroom);
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
        rootref = FirebaseDatabase.getInstance().getReference().child("customrooms");
        usern = rootref.child(roomcode+"/usern");
        usern.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usernumber = dataSnapshot.getValue().toString();
                if(usernumber == "user two")
                {
                    usertwomethod();
                }
                if(usernumber == "user three")
                {
                    userthreemethod();
                }
                if(usernumber == "user four")
                {
                    userfourmethod();
                }
                if(usernumber == "five")
                {
                    userfivemethod();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
    //////////////////////////////////////user count == user ready count//////////////////////////////////////////////
    public void start(View view)
    {
        if(usertwoready == "true")
        {
            if(userthreeready == "true")
            {
                if(userfourready == "true")
                {
                    if(userfiveready == "true")
                    {
                        rootref.child(roomcode+"/playing").setValue("true");
                        startActivity(new Intent(getApplicationContext(),crgameplaytest.class));
                    }
                }
            }
        }
        else
        {
            Toast.makeText(waitingroom.this, "All Users Are Not Ready!", Toast.LENGTH_SHORT).show();
        }
    }







    public void usertwomethod()
    {
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usertwoname = dataSnapshot.child("user two/name").toString();
                player[2].setText(usertwoname);
                playerbar[2].setVisibility(View.VISIBLE);
                playerbar[2].setProgress(65);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        usertworeadyref = rootref.child(roomcode+"/user two/ready");
        usertworeadyref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usertwoready = dataSnapshot.getValue().toString();
                if(usertwoready == "true")
                {
                    playermark[2].setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }







    public void userthreemethod()
    {
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userthreename = dataSnapshot.child("user three/name").toString();
                player[3].setText(userthreename);
                playerbar[3].setVisibility(View.VISIBLE);
                playerbar[3].setProgress(65);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        userthreereadyref = rootref.child(roomcode+"/user three/ready");
        userthreereadyref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userthreeready = dataSnapshot.getValue().toString();
                if(userthreeready == "true")
                {
                    playermark[3].setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }





    public void userfourmethod()
    {
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userfourname = dataSnapshot.child("user four/name").toString();
                player[4].setText(userfourname);
                playerbar[4].setVisibility(View.VISIBLE);
                playerbar[4].setProgress(65);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        userfourreadyref = rootref.child(roomcode+"/user four/ready");
        userfourreadyref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userfourready = dataSnapshot.getValue().toString();
                if(userfourready == "true")
                {
                    playermark[4].setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }





    public void userfivemethod()
    {
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userfivename = dataSnapshot.child("user five/name").toString();
                player[5].setText(userfivename);
                playerbar[5].setVisibility(View.VISIBLE);
                playerbar[5].setProgress(65);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        userfivereadyref = rootref.child(roomcode+"/user five/ready");
        userfivereadyref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userfiveready = dataSnapshot.getValue().toString();
                if(userfiveready == "true")
                {
                    playermark[5].setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }






















}
