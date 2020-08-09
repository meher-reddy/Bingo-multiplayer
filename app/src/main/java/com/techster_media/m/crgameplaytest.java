package com.techster_media.m;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.techster_media.m.vars.roomcode;
import static com.techster_media.m.vars.usernumber;

public class crgameplaytest extends AppCompatActivity implements View.OnClickListener{

    int a,b,c,d,e,f,g,h,ii,j,kk,l,m,n,o,p,q,r,s,t,u,vv,w,x,y;
    DatabaseReference mainref,gameref;
    Button start;
    String[] userready = new String[6];
    Button[] buttons = new Button[25];
    TextView[] textviews = new TextView[25];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crgameplaytest);
        start = findViewById(R.id.ready);
        mainref = FirebaseDatabase.getInstance().getReference();
        gameref = mainref.child("customrooms/"+roomcode);
        for (int i = 0; i < 25; i++) {
            String buttonID = "button_" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resID);
            buttons[i].setOnClickListener(this);

        }
        for (int i = 0; i < 25; i++) {
            String textViewID = "textView_" + i;
            int resID = getResources().getIdentifier(textViewID, "id", getPackageName());
            textviews[i] = findViewById(resID);
        }

        gameref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userready[1] = dataSnapshot.child("user one/ready").toString();
                userready[2] = dataSnapshot.child("user two/ready").toString();
                userready[3] = dataSnapshot.child("user three/ready").toString();
                userready[4] = dataSnapshot.child("user four/ready").toString();
                userready[5] = dataSnapshot.child("user five/ready").toString();
                if(userready[1].equals("true"))
                    if(userready[2].equals("true"))
                        if(userready[3].equals("true"))
                            if(userready[4].equals("true"))
                                if(userready[5].equals("true")) {


                                    a = Integer.parseInt(buttons[0].getText().toString());
                                    b = Integer.parseInt(buttons[1].getText().toString());
                                    c = Integer.parseInt(buttons[2].getText().toString());
                                    d = Integer.parseInt(buttons[3].getText().toString());
                                    e = Integer.parseInt(buttons[4].getText().toString());
                                    f = Integer.parseInt(buttons[5].getText().toString());
                                    g = Integer.parseInt(buttons[6].getText().toString());
                                    h = Integer.parseInt(buttons[7].getText().toString());
                                    ii = Integer.parseInt(buttons[8].getText().toString());
                                    j = Integer.parseInt(buttons[9].getText().toString());
                                    kk = Integer.parseInt(buttons[10].getText().toString());
                                    l = Integer.parseInt(buttons[11].getText().toString());
                                    m = Integer.parseInt(buttons[12].getText().toString());
                                    n = Integer.parseInt(buttons[13].getText().toString());
                                    o = Integer.parseInt(buttons[14].getText().toString());
                                    p = Integer.parseInt(buttons[15].getText().toString());
                                    q = Integer.parseInt(buttons[16].getText().toString());
                                    r = Integer.parseInt(buttons[17].getText().toString());
                                    s = Integer.parseInt(buttons[18].getText().toString());
                                    t = Integer.parseInt(buttons[19].getText().toString());
                                    u = Integer.parseInt(buttons[20].getText().toString());
                                    vv = Integer.parseInt(buttons[21].getText().toString());
                                    w = Integer.parseInt(buttons[22].getText().toString());
                                    x = Integer.parseInt(buttons[23].getText().toString());
                                    y = Integer.parseInt(buttons[24].getText().toString());
                                    Intent intent = new Intent(getBaseContext(), crgameplaymain.class);
                                    intent.putExtra("a", a);
                                    intent.putExtra("b", b);
                                    intent.putExtra("c", c);
                                    intent.putExtra("d", d);
                                    intent.putExtra("e", e);
                                    intent.putExtra("f", f);
                                    intent.putExtra("g", g);
                                    intent.putExtra("h", h);
                                    intent.putExtra("i", ii);
                                    intent.putExtra("j", j);
                                    intent.putExtra("k", kk);
                                    intent.putExtra("l", l);
                                    intent.putExtra("m", m);
                                    intent.putExtra("n", n);
                                    intent.putExtra("o", o);
                                    intent.putExtra("p", p);
                                    intent.putExtra("q", q);
                                    intent.putExtra("r", r);
                                    intent.putExtra("s", s);
                                    intent.putExtra("t", t);
                                    intent.putExtra("u", u);
                                    intent.putExtra("v", vv);
                                    intent.putExtra("w", w);
                                    intent.putExtra("x", x);
                                    intent.putExtra("y", y);
                                    startActivity(intent);
                                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
    @Override
    public void onClick(View v)
    {

        int i=0;
        String k = Integer.toString(i);
        if(i<=25)
        {
            if (!((Button) v).getText().toString().equals(""))
                return;

            else if(i<=24){
                ((Button) v).setText(k);
            }
            else{
                ((Button) v).setText(k);
                start.setVisibility(View.VISIBLE);
                start.setEnabled(true);
            }
        }
        else if(i>24)
        {
            start.setVisibility(View.VISIBLE);
            start.setEnabled(true);
        }

        i++;

    }



    public void start(View view)
    {
        gameref.child(usernumber+"/ready").setValue("true");
    }
}
