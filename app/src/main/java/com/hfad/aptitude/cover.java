package com.hfad.aptitude;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class cover extends AppCompatActivity {
Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);

        intent = new Intent(this,MainActivity.class);

    }

    public void clicked(View view)
    {
        int id = view.getId();
        String val=null;
        switch(id)
        {
            case R.id.average: val = "average"; break;
            case R.id.ages: val = "ages"; break;
            case R.id.r_p: val = "ratio & proportion"; break;
            case R.id.others: val = "others";

        }
        intent.putExtra("child",val);
        startActivity(intent);
    }
}
