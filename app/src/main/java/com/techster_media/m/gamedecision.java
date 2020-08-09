package com.techster_media.m;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.concurrent.TimeUnit;

import javax.annotation.Nullable;

import static com.techster_media.m.vars.roomcode;
import static com.techster_media.m.vars.userID;
import static com.techster_media.m.vars.username;
import static com.techster_media.m.vars.usernumber;

public class gamedecision extends AppCompatActivity {

    String tempuid, tempname;
    FirebaseFirestore fStore;
    public FirebaseAuth fAuth;
    DatabaseReference mainref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamedecision);
        fAuth = FirebaseAuth.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        mainref = FirebaseDatabase.getInstance().getReference();
        tempuid = vars.userID;
        fStore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists()) {
                    tempname = documentSnapshot.getString("fName");
                    username = tempname;
                } else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });

    }

    public void createroom(View view) {
        roomcode = tempname.charAt(0) + "" + tempuid.charAt(0) + "B" + tempuid.charAt(7) + tempname.charAt(2) + tempuid.charAt(13);
        roomcode = roomcode.toUpperCase();
        mainref.child("customrooms/" + roomcode + "/exp").setValue("false");
        mainref.child("customrooms/" + roomcode + "/user one/name").setValue(tempname);
        mainref.child("customrooms/" + roomcode + "/user one/id").setValue(userID);
        mainref.child("customrooms/" + roomcode + "/usern").setValue("user one");
        mainref.child("customrooms/" + roomcode + "/playing").setValue("false");
        usernumber = "user one";
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.out.print("error");
        }
        startActivity(new Intent(getApplicationContext(),waitingroom.class));
    }

    public void joinroom(View view)
    {
        startActivity(new Intent(getApplicationContext(),gamecodelayout.class));
    }


}
