package com.mahirshadid.vax;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class adminselect extends AppCompatActivity implements View.OnClickListener {

    CardView covid,others,ha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminselect);

        covid=findViewById(R.id.covid);
        others=findViewById(R.id.others);
        ha=findViewById(R.id.entryha);

        covid.setOnClickListener(this);
        others.setOnClickListener(this);
        ha.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.covid:
                Intent intent = new Intent(adminselect.this, updatedb.class);
                startActivity(intent);
                break;

            case R.id.others:
                intent = new Intent(adminselect.this, showdataofothervacs.class);
                startActivity(intent);
                break;

            case R.id.entryha:
                intent = new Intent(adminselect.this, adminhosembentry.class);
                startActivity(intent);
                break;
        }
    }
}