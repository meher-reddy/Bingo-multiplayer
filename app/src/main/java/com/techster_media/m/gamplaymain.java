package com.techster_media.m;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;

import static com.techster_media.m.vars.userID;
import static com.techster_media.m.vars.whoami;

public class gamplaymain extends AppCompatActivity implements View.OnClickListener{

    DatabaseReference rootref,lastnumberopp,lastnumberself,whoseturn,movedoneself,movedoneopp,whowon,retryref,retryrefopp,readyref;
    FirebaseFirestore fStore;
    ProgressBar v1,v2,v3,v4,v5,h1,h2,h3,h4,h5,d1,d2;
    boolean selfturn=false;
    boolean V1=true,V2=true,V3=true,V4=true,V5=true,H1=true,H2=true,H3=true,H4=true,H5=true,D1=true,D2= true;
    int bingoval=0;
    int lastnum,gameval,temp=0,finishedval;
    int a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y;
    String opponent,movedone,whoamifinished,whoseturnstring;
    String userturn;
    boolean aaa,bbb,ccc,ddd,eee,fff,ggg,hhh,iii,jjj,kkk,lll,mmm,nnn,ooo,ppp,qqq,rrr,sss,ttt,uuu,vvv,www,xxx,yyy;
    Button[] buttons = new Button[25];
    TextView[] textviews = new TextView[25];
    TextView whoseturnshow,winnerbutton,counter;
    Button B,I,N,G,O;
    boolean counterbool;
    int won,lost;
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
public void winnner()
{
    Log.d("winner", vars.whoami);
    whowon.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            String whowonval = dataSnapshot.getValue().toString();
            if(whowonval == whoami)
            {




                for(int k = 0;k<=24;k++)
                {
                    buttons[k].setVisibility(View.INVISIBLE);
                    buttons[k].setClickable(false);
                    buttons[k].setText(null);
                    textviews[k].setVisibility(View.INVISIBLE);
                }





                B.setVisibility(View.INVISIBLE);
                I.setVisibility(View.INVISIBLE);
                N.setVisibility(View.INVISIBLE);
                G.setVisibility(View.INVISIBLE);
                O.setVisibility(View.INVISIBLE);

                h2.setVisibility(View.INVISIBLE);
                h3.setVisibility(View.INVISIBLE);
                h4.setVisibility(View.INVISIBLE);
                h5.setVisibility(View.INVISIBLE);
                v1.setVisibility(View.INVISIBLE);
                v2.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
                v4.setVisibility(View.INVISIBLE);
                v5.setVisibility(View.INVISIBLE);
                d1.setVisibility(View.INVISIBLE);
                d2.setVisibility(View.INVISIBLE);
                h1.setVisibility(View.INVISIBLE);
                whoseturnshow.setVisibility(View.INVISIBLE);
                winnerbutton.setText("YOU WON!!!");



                rootref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        won = Integer.parseInt(dataSnapshot.child("profiles/"+userID+"/wins").getValue().toString());
                        won++;
                        rootref.child("profiles/"+userID+"/wins").setValue(won);
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            System.out.print("error");
                        }
                        resetgame();
                        Intent i=new Intent(getBaseContext(),lostpage.class);
                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }



            else
            {
                for(int k = 0;k<=24;k++)
                {
                    buttons[k].setVisibility(View.INVISIBLE);
                    textviews[k].setVisibility(View.INVISIBLE);
                    buttons[k].setClickable(false);
                    buttons[k].setText(null);
                }
                B.setVisibility(View.INVISIBLE);
                I.setVisibility(View.INVISIBLE);
                N.setVisibility(View.INVISIBLE);
                G.setVisibility(View.INVISIBLE);
                O.setVisibility(View.INVISIBLE);

                h2.setVisibility(View.INVISIBLE);
                h3.setVisibility(View.INVISIBLE);
                h4.setVisibility(View.INVISIBLE);
                h5.setVisibility(View.INVISIBLE);
                v1.setVisibility(View.INVISIBLE);
                v2.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
                v4.setVisibility(View.INVISIBLE);
                v5.setVisibility(View.INVISIBLE);
                d1.setVisibility(View.INVISIBLE);
                d2.setVisibility(View.INVISIBLE);
                h1.setVisibility(View.INVISIBLE);
                whoseturnshow.setVisibility(View.INVISIBLE);
                winnerbutton.setVisibility(View.VISIBLE);
                winnerbutton.setText("YOU LOST!!!");


                rootref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        lost = Integer.parseInt(dataSnapshot.child("profiles/"+userID+"/lose").getValue().toString());
                        lost++;
                        rootref.child("profiles/"+userID+"/wins").setValue(lost);
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            System.out.print("error");
                        }
                        resetgame();
                        Intent iiii=new Intent(getBaseContext(),lostpage.class);
                        startActivity(iiii);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
}
public void whoseturncheck()
{
    Log.d("whoseturncheck", vars.whoami);
        whoseturn.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userturn = dataSnapshot.getValue().toString();
                whoseturnstring = userturn;
                    if (userturn.equals(whoami)) {
                        whoseturnshow.setText("your turn");
                        selfturn = true;
                    } else if(userturn.equals("finished")){
                        whoseturnshow.setText(userturn);
                        winnner();
                    }
                    else
                    {
                        whoseturnshow.setText("not your turn");
                        movedone();
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
}

public void movedone()
{
    movedoneopp.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            movedone = dataSnapshot.getValue().toString();
            if(movedone.equals("true"))
            {
                getnumber();
            }
            else if(movedone.equals("finished")) {
                whoseturnshow.setText(userturn);
                winnner();
            }
            else
            {
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    }   catch (InterruptedException e) {
                        System.out.print("error");
                    }
                    movedone();
                    temp +=1;

            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
}


public void getnumber()
{
    if(movedone.equals("finished")) {
    whoseturnshow.setText(userturn);
    winnner();
}
    lastnumberopp = FirebaseDatabase.getInstance().getReference().child("game rooms/gameroom "+gameval+"/"+opponent+"/last number");
    lastnumberopp.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            lastnum = Integer.valueOf(dataSnapshot.getValue().toString());
                if(lastnum == a) {
                    if(userturn.equals("finished")) winnner();
                    textviews[0].setText("X");
                    buttons[0].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    aaa=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == b) {
                    if(userturn.equals("finished")) winnner();
                    textviews[1].setText("X");
                    buttons[1].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    bbb=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == c) {
                    if(userturn.equals("finished")) winnner();
                    textviews[2].setText("X");
                    buttons[2].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    ccc=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == d) {
                    if(userturn.equals("finished")) winnner();
                    textviews[3].setText("X");
                    buttons[3].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    ddd=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == e) {
                    if(userturn.equals("finished")) winnner();
                    textviews[4].setText("X");
                    buttons[4].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    eee=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == f) {
                    if(userturn.equals("finished")) winnner();
                    textviews[5].setText("X");
                    buttons[5].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    fff=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == g) {
                    if(userturn.equals("finished")) winnner();
                    textviews[6].setText("X");
                    buttons[6].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    ggg=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == h) {
                    if(userturn.equals("finished")) winnner();
                    textviews[7].setText("X");
                    buttons[7].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    hhh=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == i) {
                    if(userturn.equals("finished")) winnner();
                    textviews[8].setText("X");
                    buttons[8].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    iii=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == j) {
                    if(userturn.equals("finished")) winnner();
                    textviews[9].setText("X");
                    buttons[9].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    jjj=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == k) {
                    if(userturn.equals("finished")) winnner();
                    textviews[10].setText("X");
                    buttons[10].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    kkk=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == l) {
                    if(userturn.equals("finished")) winnner();
                    textviews[11].setText("X");
                    buttons[11].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    lll=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == m) {
                    if(userturn.equals("finished")) winnner();
                    textviews[12].setText("X");
                    buttons[12].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    mmm=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == n) {
                    if(userturn.equals("finished")) winnner();
                    textviews[13].setText("X");
                    buttons[13].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    nnn=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == o) {
                    if(userturn.equals("finished")) winnner();
                    textviews[14].setText("X");
                    buttons[14].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    ooo=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == p) {
                    if(userturn.equals("finished")) winnner();
                    textviews[15].setText("X");
                    buttons[15].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    ppp=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == q) {
                    if(userturn.equals("finished")) winnner();
                    textviews[16].setText("X");
                    buttons[16].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    qqq=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == r) {
                    if(userturn.equals("finished")) winnner();
                    textviews[17].setText("X");
                    buttons[17].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    rrr=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == s) {
                    if(userturn.equals("finished")) winnner();
                    textviews[18].setText("X");
                    buttons[18].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    sss=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == t) {
                    if(userturn.equals("finished")) winnner();
                    textviews[19].setText("X");
                    buttons[19].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    ttt=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == u) {
                    if(userturn.equals("finished")) winnner();
                    textviews[20].setText("X");
                    buttons[20].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    uuu=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == v) {
                    if(userturn.equals("finished")) winnner();
                    textviews[21].setText("X");
                    buttons[21].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    vvv=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == w) {
                    if(userturn.equals("finished")) winnner();
                    textviews[22].setText("X");
                    buttons[22].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    www=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == x) {
                    if(userturn.equals("finished")) winnner();
                    textviews[23].setText("X");
                    buttons[23].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    xxx=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }
                else if(lastnum == y) {
                    if(userturn.equals("finished")) winnner();
                    textviews[24].setText("X");
                    buttons[24].setClickable(false);
                    movedoneopp.setValue("false");
                    selfturn = true;
                    yyy=true;
                    if(bingoval>=5) whoseturn.setValue("finished");
                    linecheck();
                }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
}

public void linecheck()
{
    if(H1)
    if(aaa && bbb && ccc && ddd && eee)
    {
        h1.setVisibility(View.VISIBLE);
        h1.setProgress(100);
        bingoval+=1;
        H1=false;
    }
    if(H2)
    if(fff && ggg && hhh && iii && jjj)
    {
        h2.setVisibility(View.VISIBLE);
        h2.setProgress(100);
        bingoval+=1;
        H2=false;
    }
    if(H3)
    if(kkk && lll && mmm &&nnn && ooo )
    {
        h3.setVisibility(View.VISIBLE);
        h3.setProgress(100);
        bingoval+=1;
        H3=false;
    }
    if(H4)
    if(ppp && qqq && rrr && sss && ttt)
    {
        h4.setVisibility(View.VISIBLE);
        h4.setProgress(100);
        bingoval+=1;
        H4 = false;
    }
    if(H5)
    if(uuu && vvv && www && xxx && yyy)
    {
        h5.setVisibility(View.VISIBLE);
        h5.setProgress(100);
        bingoval+=1;
        H5 = false;
    }
    if(V1)
    if(aaa && fff && kkk && ppp && uuu)
    {
        v1.setVisibility(View.VISIBLE);
        v1.setProgress(100);
        bingoval+=1;
        V1 = false;
    }
    if(V2)
    if(bbb && ggg && lll && qqq && vvv)
    {
        v2.setVisibility(View.VISIBLE);
        v2.setProgress(100);
        bingoval+=1;
        V2 = false;
    }
    if(V3)
    if(ccc && hhh && mmm && rrr && www)
    {
        v3.setVisibility(View.VISIBLE);
        v3.setProgress(100);
        bingoval+=1;
        V3 = false;
    }
    if(V4)
    if(ddd && iii && nnn && sss && xxx)
    {
        v4.setVisibility(View.VISIBLE);
        v4.setProgress(100);
        bingoval+=1;
        V4 = false;
    }
    if(V5)
    if(eee && jjj && ooo && ttt && yyy)
    {
        v5.setVisibility(View.VISIBLE);
        v5.setProgress(100);
        bingoval+=1;
        V5 = false;
    }
    if(D1)
    if(aaa && ggg && mmm && sss && yyy)
    {
        d1.setVisibility(View.VISIBLE);
        d1.setProgress(100);
        bingoval+=1;
        D1 = false;
    }
    if(D2)
    if(eee && iii && mmm && qqq && uuu)
    {
        d2.setVisibility(View.VISIBLE);
        d2.setProgress(100);
        bingoval+=1;
        D2 = false;
    }
    if(bingoval==1)
        B.setText("B");
    if(bingoval==2) {
        B.setText("B");
        I.setText("I");
    }
    if(bingoval==3) {
        B.setText("B");
        I.setText("I");
        N.setText("N");
    }
    if(bingoval==4) {
        B.setText("B");
        I.setText("I");
        N.setText("N");
        G.setText("g");
    }
    if(bingoval>=5) {
        B.setText("B");
        I.setText("I");
        N.setText("N");
        G.setText("G");
        O.setText("O");
        whowon.setValue(whoami);
        movedoneself.setValue("finished");
        movedoneopp.setValue("finished");
    }
    whoseturncheck();
}
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamplaymain);
        whoseturnshow = findViewById(R.id.whoseturnshow);
        winnerbutton = findViewById(R.id.userwon);
        counter = findViewById(R.id.counter);
        fStore = FirebaseFirestore.getInstance();
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

        v1 = findViewById(R.id.v1);
        v2 = findViewById(R.id.v2);
        v3 = findViewById(R.id.v3);
        v4 = findViewById(R.id.v4);
        v5 = findViewById(R.id.v5);
        h1 = findViewById(R.id.h1);
        h2 = findViewById(R.id.h2);
        h3 = findViewById(R.id.h3);
        h4 = findViewById(R.id.h4);
        h5 = findViewById(R.id.h5);
        d1 = findViewById(R.id.d1);
        d2 = findViewById(R.id.d2);
        B = findViewById(R.id.B);
        I = findViewById(R.id.I);
        N = findViewById(R.id.N);
        G = findViewById(R.id.G);
        O = findViewById(R.id.O);
        B.setClickable(false);
        I.setClickable(false);
        N.setClickable(false);
        G.setClickable(false);
        B.setClickable(false);


        Intent intent = getIntent();
        gameval = vars.gamenum;
        a = intent.getIntExtra("a",0);
        b = intent.getIntExtra("b",0);
        c = intent.getIntExtra("c",0);
        d = intent.getIntExtra("d",0);
        e = intent.getIntExtra("e",0);
        f = intent.getIntExtra("f",0);
        g = intent.getIntExtra("g",0);
        h = intent.getIntExtra("h",0);
        i = intent.getIntExtra("i",0);
        j = intent.getIntExtra("j",0);
        k = intent.getIntExtra("k",0);
        l = intent.getIntExtra("l",0);
        m = intent.getIntExtra("m",0);
        n = intent.getIntExtra("n",0);
        o = intent.getIntExtra("o",0);
        p = intent.getIntExtra("p",0);
        q = intent.getIntExtra("q",0);
        r = intent.getIntExtra("r",0);
        s = intent.getIntExtra("s",0);
        t = intent.getIntExtra("t",0);
        u = intent.getIntExtra("u",0);
        v = intent.getIntExtra("v",0);
        w = intent.getIntExtra("w",0);
        x = intent.getIntExtra("x",0);
        y = intent.getIntExtra("y",0);
        buttons[0].setText(String.valueOf(a));
        buttons[1].setText(String.valueOf(b));
        buttons[2].setText(String.valueOf(c));
        buttons[3].setText(String.valueOf(d));
        buttons[4].setText(String.valueOf(e));
        buttons[5].setText(String.valueOf(f));
        buttons[6].setText(String.valueOf(g));
        buttons[7].setText(String.valueOf(h));
        buttons[8].setText(String.valueOf(i));
        buttons[9].setText(String.valueOf(j));
        buttons[10].setText(String.valueOf(k));
        buttons[11].setText(String.valueOf(l));
        buttons[12].setText(String.valueOf(m));
        buttons[13].setText(String.valueOf(n));
        buttons[14].setText(String.valueOf(o));
        buttons[15].setText(String.valueOf(p));
        buttons[16].setText(String.valueOf(q));
        buttons[17].setText(String.valueOf(r));
        buttons[18].setText(String.valueOf(s));
        buttons[19].setText(String.valueOf(t));
        buttons[20].setText(String.valueOf(u));
        buttons[21].setText(String.valueOf(v));
        buttons[22].setText(String.valueOf(w));
        buttons[23].setText(String.valueOf(x));
        buttons[24].setText(String.valueOf(y));
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////


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
        rootref = FirebaseDatabase.getInstance().getReference();
        retryref = FirebaseDatabase.getInstance().getReference().getRef().child("game rooms/gameroom "+gameval+"/"+whoami+"/retry");
        readyref = FirebaseDatabase.getInstance().getReference().getRef().child("game rooms/gameroom "+gameval+"/"+whoami+"/ready");
        retryrefopp = FirebaseDatabase.getInstance().getReference().getRef().child("game rooms/gameroom "+gameval+"/"+opponent+"/retry");
        lastnumberopp = FirebaseDatabase.getInstance().getReference().child("game rooms/gameroom "+gameval+"/"+opponent+"/last number");
        lastnumberself = FirebaseDatabase.getInstance().getReference().child("game rooms/gameroom "+gameval+"/"+whoami+"/last number");
        whoseturn = FirebaseDatabase.getInstance().getReference().child("game rooms/gameroom "+gameval+"/whoseturn");
        movedoneopp = FirebaseDatabase.getInstance().getReference().child("game rooms/gameroom "+gameval+"/"+opponent+"/moveval");
        movedoneself = FirebaseDatabase.getInstance().getReference().child("game rooms/gameroom "+gameval+"/"+whoami+"/moveval");
        movedoneself.setValue("false");
        retryref.setValue("false");
        whowon = FirebaseDatabase.getInstance().getReference().child("game rooms/gameroom "+gameval+"/whowon");
        whowon.setValue("false");
        whoamifinished = whoami;
        finishedval=gameval;
        Log.d("gameplaymain end", vars.whoami);
        whoseturncheck();


    }


    @Override
    public void onClick(View view) {
    if(selfturn)
    {
        counterbool = true;
        switch (view.getId()) {
            case R.id.button_0:  if(userturn.equals("finished")) winnner(); textviews[0].setText("X");  buttons[0].setClickable(false);  lastnumberself.setValue(a); aaa =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_1:  if(userturn.equals("finished")) winnner(); textviews[1].setText("X");  buttons[1].setClickable(false);  lastnumberself.setValue(b); bbb =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_2:  if(userturn.equals("finished")) winnner(); textviews[2].setText("X");  buttons[2].setClickable(false);  lastnumberself.setValue(c); ccc =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_3:  if(userturn.equals("finished")) winnner(); textviews[3].setText("X");  buttons[3].setClickable(false);  lastnumberself.setValue(d); ddd =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_4:  if(userturn.equals("finished")) winnner(); textviews[4].setText("X");  buttons[4].setClickable(false);  lastnumberself.setValue(e); eee =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_5:  if(userturn.equals("finished")) winnner(); textviews[5].setText("X");  buttons[5].setClickable(false);  lastnumberself.setValue(f); fff =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_6:  if(userturn.equals("finished")) winnner(); textviews[6].setText("X");  buttons[6].setClickable(false);  lastnumberself.setValue(g); ggg =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_7:  if(userturn.equals("finished")) winnner(); textviews[7].setText("X");  buttons[7].setClickable(false);  lastnumberself.setValue(h); hhh =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_8:  if(userturn.equals("finished")) winnner(); textviews[8].setText("X");  buttons[8].setClickable(false);  lastnumberself.setValue(i); iii =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_9:  if(userturn.equals("finished")) winnner(); textviews[9].setText("X");  buttons[9].setClickable(false);  lastnumberself.setValue(j); jjj =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_10: if(userturn.equals("finished")) winnner(); textviews[10].setText("X"); buttons[10].setClickable(false); lastnumberself.setValue(k); kkk =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_11: if(userturn.equals("finished")) winnner(); textviews[11].setText("X"); buttons[11].setClickable(false); lastnumberself.setValue(l); lll =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_12: if(userturn.equals("finished")) winnner(); textviews[12].setText("X"); buttons[12].setClickable(false); lastnumberself.setValue(m); mmm =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_13: if(userturn.equals("finished")) winnner(); textviews[13].setText("X"); buttons[13].setClickable(false); lastnumberself.setValue(n); nnn =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_14: if(userturn.equals("finished")) winnner(); textviews[14].setText("X"); buttons[14].setClickable(false); lastnumberself.setValue(o); ooo =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_15: if(userturn.equals("finished")) winnner(); textviews[15].setText("X"); buttons[15].setClickable(false); lastnumberself.setValue(p); ppp =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_16: if(userturn.equals("finished")) winnner(); textviews[16].setText("X"); buttons[16].setClickable(false); lastnumberself.setValue(q); qqq =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_17: if(userturn.equals("finished")) winnner(); textviews[17].setText("X"); buttons[17].setClickable(false); lastnumberself.setValue(r); rrr =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_18: if(userturn.equals("finished")) winnner(); textviews[18].setText("X"); buttons[18].setClickable(false); lastnumberself.setValue(s); sss =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_19: if(userturn.equals("finished")) winnner(); textviews[19].setText("X"); buttons[19].setClickable(false); lastnumberself.setValue(t); ttt =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_20: if(userturn.equals("finished")) winnner(); textviews[20].setText("X"); buttons[20].setClickable(false); lastnumberself.setValue(u); uuu =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_21: if(userturn.equals("finished")) winnner(); textviews[21].setText("X"); buttons[21].setClickable(false); lastnumberself.setValue(v); vvv =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_22: if(userturn.equals("finished")) winnner(); textviews[22].setText("X"); buttons[22].setClickable(false); lastnumberself.setValue(w); www =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_23: if(userturn.equals("finished")) winnner(); textviews[23].setText("X"); buttons[23].setClickable(false); lastnumberself.setValue(x); xxx =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            case R.id.button_24: if(userturn.equals("finished")) winnner(); textviews[24].setText("X"); buttons[24].setClickable(false); lastnumberself.setValue(y); yyy =true;  if(bingoval>=5) whoseturn.setValue("finished"); else whoseturn.setValue(opponent); movedoneself.setValue("true"); selfturn = false; counterbool=false; linecheck(); break;
            }
        }
        else
        {
            if (!((Button) view).getText().toString().equals(""))
                return;
        }
    }

    public void resetgame()
    {
        whoseturn.setValue("user two");
        movedoneself.setValue("false");
        lastnumberself.setValue(0);
        h1.setProgress(0);
        h2.setProgress(0);
        h3.setProgress(0);
        h4.setProgress(0);
        h5.setProgress(0);
        v1.setProgress(0);
        v2.setProgress(0);
        v3.setProgress(0);
        v4.setProgress(0);
        v5.setProgress(0);
        d1.setProgress(0);
        d2.setProgress(0);
        readyref.setValue("false");
    }
}