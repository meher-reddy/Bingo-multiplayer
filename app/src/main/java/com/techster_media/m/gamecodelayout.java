package com.techster_media.m;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.techster_media.m.vars.roomcode;
import static com.techster_media.m.vars.userID;
import static com.techster_media.m.vars.username;
import static com.techster_media.m.vars.usernumber;

public class gamecodelayout extends AppCompatActivity {

    String tempuid, tempname;
    Button checkcode;
    String gamecodecheck,exp,usercheck;
    TextView entercode;
    EditText codeenter;
    DatabaseReference mainref,roomref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamecodelayout);
        checkcode = findViewById(R.id.checkcode);
        codeenter = findViewById(R.id.codeenter);
        entercode = findViewById(R.id.entercode);
        mainref = FirebaseDatabase.getInstance().getReference();
        tempname = username;
        tempuid = userID;
    }
    public void checkcode(View view)
    {
        gamecodecheck = codeenter.getText().toString();
        mainref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("customrooms/"+gamecodecheck))
                {
                    exp = dataSnapshot.child("customrooms/"+gamecodecheck+"/exp").toString();
                    if(exp == "true")
                    {
                        entercode.setText("Incorrect Code");
                    }
                    else
                    {
                        usercheck = dataSnapshot.child("customrooms/"+gamecodecheck+"/usern").getValue().toString();
                        roomcode = gamecodecheck;
                        roomref = mainref.child("customrooms/"+roomcode);
                        switch (usercheck) {
                            case "user one":
                                usertwomethod();
                                break;
                            case "user two":
                                userthreemethod();
                                break;
                            case "user three":
                                userfourmethod();
                                break;
                            case "user four":
                                userfivemethod();
                                break;
                            default:
                                Toast.makeText(gamecodelayout.this, "failed", Toast.LENGTH_SHORT).show();

                        }

                    }
                }
                else
                {
                    entercode.setText("Incorrect Code");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void usertwomethod()
    {
        roomref.child("user two/name").setValue(tempname);
        roomref.child("user two/id").setValue(tempuid);
        roomref.child("user two/ready").setValue("false");
        roomref.child("usern").setValue("user two");
        usernumber = "user two";
        startActivity(new Intent(getApplicationContext(),otherswaitingroom.class));
    }
    public void userthreemethod()
    {
        roomref.child("user three/name").setValue(tempname);
        roomref.child("user three/id").setValue(tempuid);
        roomref.child("user three/ready").setValue("false");
        roomref.child("usern").setValue("user three");
        usernumber = "user three";
        startActivity(new Intent(getApplicationContext(),otherswaitingroom.class));
    }
    public void userfourmethod()
    {
        roomref.child("user four/name").setValue(tempname);
        roomref.child("user four/id").setValue(tempuid);
        roomref.child("user four/ready").setValue("false");
        roomref.child("usern").setValue("user four");
        usernumber = "user four";
        startActivity(new Intent(getApplicationContext(),otherswaitingroom.class));
    }
    public void userfivemethod()
    {
        roomref.child("user five/name").setValue(tempname);
        roomref.child("user five/id").setValue(tempuid);
        roomref.child("user five/ready").setValue("false");
        roomref.child("usern").setValue("full");
        usernumber = "user five";
        startActivity(new Intent(getApplicationContext(),otherswaitingroom.class));
    }


}
