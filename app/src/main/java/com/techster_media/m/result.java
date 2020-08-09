package com.techster_media.m;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

import static com.techster_media.m.vars.gamenum;
import static com.techster_media.m.vars.whoami;

public class result extends AppCompatActivity{

    Button replay;
    DatabaseReference retryref,retryrefopp;
    String retrybool,opponent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        replay = findViewById(R.id.replay);
        try{
            if(whoami.equals("user one"))
            {
                opponent = "user two";
            }
            else
            {
                opponent = "user one";
            }
        }
        catch (NullPointerException npe) {
            System.out.print(npe);
        }
        retryref = FirebaseDatabase.getInstance().getReference().getRef().child("game rooms/gameroom "+gamenum+"/"+whoami+"/retry");
        retryrefopp = FirebaseDatabase.getInstance().getReference().getRef().child("game rooms/gameroom "+gamenum+"/"+opponent+"/retry");
    }
    public void menu(View view)
    {
        Intent intent = new Intent(getBaseContext(), menu.class);
        startActivity(intent);
    }
    public void retry(View view)
    {
        retrymethod();
    }

    public void retrymethod()
    {
        retryref = FirebaseDatabase.getInstance().getReference().getRef().child("game rooms/gameroom "+gamenum+"/"+whoami+"/retry");
        retryref.setValue("true");
        retryrefopp.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                retrybool = dataSnapshot.getValue().toString();
                replay.setText(retrybool);
                if (retrybool.equals("true"))
                {
                    replay.setText("true");
                    Intent intent = new Intent(getBaseContext(), gameplaytest.class);
                    startActivity(intent);
                }
                else
                {
                    replay.setText("false");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    }
                    catch (InterruptedException e)
                    {
                        System.out.print("error");
                    }
                    retrymethod();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
}

}
