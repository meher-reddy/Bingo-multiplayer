package com.techster_media.m;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

import static com.techster_media.m.vars.userID;


public class playermatching extends AppCompatActivity {


////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////VARIABLES/////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
    public DatabaseReference ivalueref,gamecreatebool,roomcreationref,gamefullvalref;
    public DatabaseReference gamejoiningsub;
    public FirebaseAuth fAuth;
    public FirebaseUser user;
    FirebaseFirestore fStore;
    private int i;
    public String roomcreate,gameroomstring,gamefullstring;
    String fullName,oppUID,myUID;
    long uone,utwo;
    String uones,utwos;
    TextView matching;
    boolean match=true;

////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////END/////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////Room Creation Method/////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
    void roomcreation()
    {
        ivalueref = FirebaseDatabase.getInstance().getReference().child("i");
        ivalueref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                i = Integer.valueOf(dataSnapshot.getValue().toString());

                gamejoiningsub = FirebaseDatabase.getInstance().getReference().child("roomcreate");
                gamejoiningsub.setValue("false");
                gameroomstring = "game rooms/gameroom "+i;
                gamefullstring = gameroomstring+"/gamefull";
                roomcreationref = FirebaseDatabase.getInstance().getReference().child(gameroomstring);
                roomcreationref.child("user one/time").setValue(ServerValue.TIMESTAMP);
                roomcreationref.child("game id").setValue(userID);
                roomcreationref.child("game number").setValue(i);
                roomcreationref.child("user one/id").setValue(userID);
                roomcreationref.child("gamefull").setValue("false");
                roomcreationref.child("user one/last number").setValue(0);
                roomcreationref.child("user one/name").setValue(fullName);
                roomcreationref.child("user one/ready").setValue("false");
                vars.whoami="user one";
                vars.gamenum = i;
                match=false;
                Log.d("roomcreation", vars.whoami);
                Intent intent = new Intent(getBaseContext(), matched.class);
                startActivity(intent);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////END////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////Room Joining Method///////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
    void roomjoining()
    {


        ivalueref = FirebaseDatabase.getInstance().getReference().child("i");
        ivalueref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                gamejoiningsub = FirebaseDatabase.getInstance().getReference().child("roomcreate");
                gamejoiningsub.setValue("true");
                i = Integer.valueOf(dataSnapshot.getValue().toString());
                    gameroomstring = "game rooms/gameroom " + i;
                    gamefullstring = gameroomstring + "/gamefull";
                    roomcreationref = FirebaseDatabase.getInstance().getReference().child(gameroomstring);
                roomcreationref.child("user two/time").setValue(ServerValue.TIMESTAMP);
                roomcreationref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            utwos= dataSnapshot.child("user two/time").getValue().toString();
                            utwo = Long.parseLong(utwos);
                            uones = dataSnapshot.child("user one/time").getValue().toString();
                            uone = Long.parseLong(uones);
                            oppUID = dataSnapshot.child("user one/id").getValue().toString();
                            if((utwo-uone)>200000) {
                                i++;
                                ivalueref.setValue(i);
                                roomcreation();
                            }
                           else if(oppUID != userID)
                            {
                                roomcreationref.child("user two/id").setValue(userID);
                                roomcreationref.child("gamefull").setValue("true");
                                roomcreationref.child("user two/last number").setValue(0);
                                roomcreationref.child("user two/name").setValue(fullName);
                                roomcreationref.child("user two/ready").setValue("false");
                                i++;
                                gamefullvalref = FirebaseDatabase.getInstance().getReference().child("i");
                                gamefullvalref.setValue(i);
                                vars.gamenum = i -= 1;
                                vars.whoami = "user two";
                                match=false;
                                Log.d("room joining", vars.whoami);
                                Intent intent = new Intent(getBaseContext(), matched.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////END//////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playermatching);
        matching = findViewById(R.id.loading);
        //...........................................//
        //........for reading username and id........//
        //...........................................//
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();
        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    fullName = documentSnapshot.getString("fName");
                }else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });
        //*******************************************//
        //*******************************************//
        //*******************************************//
        gamecreatebool = FirebaseDatabase.getInstance().getReference().child("roomcreate");
        gamecreatebool.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                roomcreate = dataSnapshot.getValue().toString();
                if(roomcreate.equals("true"))
                {
                    gamecreatebool.setValue("false");
                    roomcreation();
                }
                else if(roomcreate.equals("false"))
                {
                    gamecreatebool.setValue("true");
                    roomjoining();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}