package com.hfad.aptitude;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Toast toast;
DatabaseReference dbr;
ConnectivityManager cm;
NetworkInfo ni;
LinearLayout ll;
LinearLayout.LayoutParams params;
Button button;
TextView textView1,tv3;
int total,i;
String[] q,a;
Intent intent;
LinearLayout.LayoutParams p;
String child;
Drawable drawable;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i = 0;
        toast = Toast.makeText(this,"Loading...",Toast.LENGTH_SHORT);
        actionBar = getSupportActionBar();
        drawable = getDrawable(R.drawable.cover_bg);
        intent = getIntent();
        child = intent.getStringExtra("child");
        dbr = FirebaseDatabase.getInstance().getReference();
        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        ll =  findViewById(R.id.ll_1);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);


        ValueEventListener  valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                total = dataSnapshot.child(child).child("t").getValue(Integer.class);

                q = new String[total];
                a = new String[total];

                for(i = 0; i<total; ++i) {
                    textView1 = new TextView(MainActivity.this);
                    button = new Button(MainActivity.this);

                    tv3 = new TextView(MainActivity.this);

                    q[i] = dataSnapshot.child(child).child(String.valueOf(i)).child("q").getValue(String.class);
                    a[i] = dataSnapshot.child(child).child(String.valueOf(i)).child("a").getValue(String.class);
                    textView1.setText(q[i]);
                    textView1.setTextSize(20);
                    textView1.setTextColor(Color.WHITE);
                    ll.addView(textView1, params);
                    button.setId(i);
                    button.setText("REVEAL");
                    p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 10);
                    button.setOnClickListener(MainActivity.this);
                    button.setBackgroundColor(Color.BLACK);
                    button.setTextColor(Color.WHITE);
                    tv3.setBackgroundColor(Color.BLACK);
                    ll.addView(button, params);
                    ll.addView(tv3,p);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        dbr.addValueEventListener(valueEventListener);




    }



        @Override
        public void onStart()
        {
            super.onStart();

            actionBar.setDisplayHomeAsUpEnabled(true);
            ni = cm.getActiveNetworkInfo();





            if(ni == null)
            {
                TextView tv = new TextView(this);
                tv.setText("Connect to Internet and Relaunch");
                tv.setGravity(Gravity.CENTER);
                tv.setTextColor(Color.WHITE);
                tv.setTextSize(20);
                ll.addView(tv,new LinearLayout.LayoutParams(-1,-1));
            }
else
                toast.show();





        }
    public void onClick(View v) {
        int id = v.getId();
        Button button = findViewById(id);
        button.setText(a[id]);

    }

}
